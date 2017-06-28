package demo.cognitive.api;

import demo.cognitive.service.EmotionService;
import demo.cognitive.service.StorageService;
import demo.cognitive.utils.Base64Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lisong on 2017/6/7.
 *
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    private EmotionService emotionService;

    @Value("${qiniu.base.url}")
    private String baseUrl;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/")
    @ResponseBody
    public Object handleFileUpload(@RequestParam("file") MultipartFile file) {

        Map<String, Object> res = new HashMap<>();
        try {
            String filename = storageService.store(file);
            String url = String.format("http://%s/%s", baseUrl, filename);
            String key = Base64Convertor.toString36Base(url.hashCode());

            res.put("data", emotionService.check(key, url));
            res.put("url", url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }


}
