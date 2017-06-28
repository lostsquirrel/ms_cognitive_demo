package demo.cognitive.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by lisong on 2017/6/7.
 */
public interface StorageService {
    String store(MultipartFile file) throws IOException;
}
