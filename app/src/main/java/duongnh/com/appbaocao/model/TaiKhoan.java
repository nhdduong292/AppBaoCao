package duongnh.com.appbaocao.model;

/**
 * Created by Admin on 4/19/2018.
 */

public class TaiKhoan {
    private String id;
    private String tenDN, matKhau;
    private String ten, tuoi;
    private byte[] image;

    public TaiKhoan() {
    }

    public TaiKhoan(String id, String tenDN, String matKhau) {
        this.id = id;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public TaiKhoan(String tenDN, String matKhau) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public TaiKhoan(String tenDN, String matKhau, String ten, String tuoi, byte[] image) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.ten = ten;
        this.tuoi = tuoi;
        this.image = image;
    }

    public TaiKhoan(String id, String tenDN, String matKhau, String ten, String tuoi, byte[] image) {
        this.id = id;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.ten = ten;
        this.tuoi = tuoi;
        this.image = image;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public byte[] getAvatar() {
        return image;
    }

    public void setAvatar(byte[] avatar) {
        this.image = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
