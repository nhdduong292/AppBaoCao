package duongnh.com.appbaocao.model;

/**
 * Created by Admin on 4/19/2018.
 */

public class TaiKhoan {
    private int id;
    private String tenDN, matKhau;
    private String ten, tuoi, avatar;

    public TaiKhoan() {
    }

    public TaiKhoan(int id, String tenDN, String matKhau) {
        this.id = id;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public TaiKhoan(String tenDN, String matKhau) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public TaiKhoan(String tenDN, String matKhau, String ten, String tuoi, String avatar) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.ten = ten;
        this.tuoi = tuoi;
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
