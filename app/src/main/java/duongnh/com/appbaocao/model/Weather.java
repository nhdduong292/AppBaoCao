package duongnh.com.appbaocao.model;

public class Weather {
    private String ngay, trangThai, image, minTemp, maxTemp;

    public Weather(String ngay, String trangThai, String image, String minTemp, String maxTemp) {
        this.ngay = ngay;
        this.trangThai = trangThai;
        this.image = image;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }
}
