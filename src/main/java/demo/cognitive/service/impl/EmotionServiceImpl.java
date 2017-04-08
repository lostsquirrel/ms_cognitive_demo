package demo.cognitive.service.impl;

import demo.cognitive.service.EmotionService;
import demo.cognitive.utils.EmotionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by lisong on 2017/4/7.
 */
@Component
public class EmotionServiceImpl implements EmotionService{

    @Override
    @Cacheable(value = "emotions", key = "#key")
    public Object check(String key, String url) {
        return EmotionUtils.cog(url);
    }
}
