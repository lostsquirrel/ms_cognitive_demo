package demo.cognitive.utils;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EmotionUtils {
	
	private static String url;

	private static String key;

	public static void init(String key, String url) {
		EmotionUtils.key = key;
		EmotionUtils.url = url;
	}
	
	public static String cog(String image) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
