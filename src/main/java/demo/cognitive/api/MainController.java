package demo.cognitive.api;

import demo.cognitive.service.EmotionService;
import demo.cognitive.utils.Base64Convertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lisong on 2017/4/7.
 */
@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private EmotionService emotionService;


    @RequestMapping("/emotion")
    @ResponseBody
    Object emotion(@RequestParam("url") String url) {
        String key = Base64Convertor.toString36Base(url.hashCode());
        Object check = emotionService.check(key, url);
        return check;
    }
}
