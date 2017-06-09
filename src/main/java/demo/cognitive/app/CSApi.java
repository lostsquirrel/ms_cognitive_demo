package demo.cognitive.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lisong on 2017/4/8.
 */
@ConfigurationProperties(prefix = "cs.api", ignoreInvalidFields=true)
public class CSApi {

    private String url;

    private String key;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
