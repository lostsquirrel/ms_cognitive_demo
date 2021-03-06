package demo.cognitive.app;

import demo.cognitive.utils.EmotionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"demo.cognitive"})
@EnableConfigurationProperties({CSApi.class})
@EnableAutoConfiguration
@EnableCaching
public class Application {
	public static void main(String[] args)
	{
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		CSApi api = ctx.getBean(CSApi.class);
		EmotionUtils.init(api.getKey(), api.getUrl());
	}
}

