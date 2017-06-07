package demo.cognitive.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import demo.cognitive.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by lisong on 2017/6/7.
 *  调用七牛对象存储
 */
@Component
public class StorageServiceImpl implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Value("${qiniu.access.key}")
    private String accessKey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucket;

    //构造一个带指定Zone对象的配置类
    private Configuration cfg = new Configuration(Zone.zone2());
    //...其他参数参考类注释
    private UploadManager uploadManager = new UploadManager(cfg);

    @Override
    public String store(MultipartFile file) throws IOException {
        String oname = file.getOriginalFilename();
        log.debug(oname);
        String ext = oname.substring(oname.lastIndexOf(".") + 1);
        log.debug(ext);
        String key =  UUID.randomUUID().toString().replace("-", "");
        Response response = uploadManager.put(file.getBytes(), String.format("%s.%s", key, ext), getToken());
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        return putRet.key;
    }

    private String getToken() {
        log.debug("{}, {}", accessKey, secretKey);
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
}
