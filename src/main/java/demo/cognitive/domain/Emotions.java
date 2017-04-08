package demo.cognitive.domain;

import java.io.Serializable;

/**
 * Created by lisong on 2017/4/8.
 */
public class Emotions implements Serializable {

    private FaceRect faceRectangle;

    private EmotionScores scores;

    private String result;

    public FaceRect getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(FaceRect faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public EmotionScores getScores() {
        return scores;
    }

    public void setScores(EmotionScores scores) {
        this.scores = scores;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
