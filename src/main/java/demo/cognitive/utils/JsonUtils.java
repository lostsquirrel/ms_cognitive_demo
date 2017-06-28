package demo.cognitive.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import demo.cognitive.domain.Emotions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisong on 2017/4/8.
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    private static TypeFactory typeFactory = TypeFactory.defaultInstance();

    public static List<Emotions> getEmotionList(String json) {
        List<Emotions> list =
                new ArrayList<>();
        try {
            list = mapper.readValue(json, typeFactory.constructCollectionType(List.class, Emotions.class));
        } catch (IOException e) {
        }

        return list;
    }
}
