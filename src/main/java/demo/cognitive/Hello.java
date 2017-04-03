package demo.cognitive;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import demo.cognitive.utils.ConfigUtils;

public class Hello {

	private String url;
	
	private String key;
	
	public Hello() {
		url = ConfigUtils.get(ConfigUtils.CS_API_URL);
		key = ConfigUtils.get(ConfigUtils.CS_API_KEY);
	}
	public static void main(String[] args) {
		String image = "http://b89.photo.store.qq.com/psb?/40855d2c-b18f-4345-a5c4-e91215af837b/kvLCbK0CMMvqga55Xxg3gQfXE**n9s.50O*o2equ28o!/b/dA8pIDXVVQAA&bo=VQIgAwAAAAABB1Q!&rf=viewer_4";
		new Hello().cog(image );
	}
	public void cog(String image) {
		HttpClient httpclient = HttpClients.createDefault();

        try
        {
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

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
	}
}
