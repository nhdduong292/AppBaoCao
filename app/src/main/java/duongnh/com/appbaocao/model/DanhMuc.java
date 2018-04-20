package duongnh.com.appbaocao.model;

/**
 * Created by Admin on 4/20/2018.
 */

public class DanhMuc {
    private int id;
    private String name;
    private int picture, color;

    public DanhMuc(String name, int picture, int color) {
        this.name = name;
        this.picture = picture;
        this.color = color;
    }

    public DanhMuc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
