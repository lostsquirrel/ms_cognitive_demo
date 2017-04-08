package demo.cognitive.domain;

import java.io.Serializable;

/**
 * Created by lisong on 2017/4/8.
 */
public class FaceRect implements Serializable {

    private int top;

    private int left;

    private int width;

    private int height;

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getHeight() {
        return height;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
