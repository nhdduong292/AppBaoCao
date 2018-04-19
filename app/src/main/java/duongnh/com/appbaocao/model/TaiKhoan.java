package duongnh.com.appbaocao.model;

/**
 * Created by Admin on 4/19/2018.
 */

public class TaiKhoan {
    private int id;
    private String tenDN, matKhau;

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
