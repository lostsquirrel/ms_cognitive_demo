package demo.cognitive.utils;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.cognitive.domain.EmotionScores;
import demo.cognitive.domain.Emotions;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmotionUtils {

    private static final Logger log = LoggerFactory.getLogger(EmotionUtils.class);

	private static String url;

	private static String key;

	private Map<String, List<String>> emotionRange = new HashMap<>();

	public static void init(String key, String url) {
		EmotionUtils.key = key;
		EmotionUtils.url = url;
	}

	public static List<Emotions> cog(String image) {
		String resp = requestApi(image);
		List<Emotions> data = JsonUtils.getEmotionList(resp);
		data.forEach((Emotions emotion) -> {
			StringBuilder sb = new StringBuilder();
			EmotionScores scores = emotion.getScores();
			calLevel(scores.getAnger(), "愤怒", sb);
			calLevel(scores.getContempt(), "蔑视", sb);
			calLevel(scores.getDisgust(), "恶心", sb);
			calLevel(scores.getFear(), "害怕", sb);
			calLevel(scores.getHappiness(), "愉快", sb);
			calLevel(scores.getNeutral(), "扑克脸", sb);
			calLevel(scores.getSadness(), "哀伤", sb);
			calLevel(scores.getSurprise(), "惊讶", sb);
			emotion.setResult(sb.toString());
		});
		return data;
	}

	private static void calLevel(double score, String suffix, StringBuilder sb) {
		int i = new Double(score * 100).intValue();
		if (i > 0) {
			sb.append(String.format("[%d级%s]", i, suffix));
		}

	}
	public static String requestApi(String image) {
		HttpClient httpclient = HttpClients.createDefault();
		String resp = "";
		try {
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", key);

			// Request body
			String body = String.format("{ \"url\": \"%s\" }", image);
			StringEntity reqEntity = new StringEntity(body);
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				resp = EntityUtils.toString(entity);
				log.debug("{}", resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
