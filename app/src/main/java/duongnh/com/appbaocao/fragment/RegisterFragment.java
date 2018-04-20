package duongnh.com.appbaocao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.StartActivity;
import duongnh.com.appbaocao.database.TaiKhoanDataBase;
import duongnh.com.appbaocao.model.TaiKhoan;

/**
 * Created by Admin on 4/19/2018.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText edtTenDN, edtMK, edtLMK;
    private Button btnDangKy;
    private TextView tvDangNhap;
    private StartActivity main;
    private ProgressBar progressBar;
    private TaiKhoanDataBase db;
    private TaiKhoan tk;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        main = (StartActivity) getActivity();
        //initView
        edtTenDN = view.findViewById(R.id.edt_ten_dang_nhap);
        edtMK = view.findViewById(R.id.edt_mat_khau);
        edtLMK = view.findViewById(R.id.edt_lai_mat_khau);
        btnDangKy = view.findViewById(R.id.btn_dang_ky);
        tvDangNhap = view.findViewById(R.id.tv_dang_nhap);
        progressBar =view.findViewById(R.id.pro_register);

        //binData
        db = new TaiKhoanDataBase(main);

        //initEvent
        btnDangKy.setOnClickListener(this);
        tvDangNhap.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dang_ky:
                if(!setupError()){
                    return;
                }
                if(db.check_user(edtTenDN.getText().toString())){
                    db.addTaiKhoan(tk);
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main.showFragment(main.getRegisterFragment(), main.getLoginFragment());
                        }
                    }, 2000);
//                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(main, "Account exist !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.tv_dang_nhap:
                main.showFragment(main.getRegisterFragment(), main.getLoginFragment());
                break;
        }
    }
    private boolean setupError() {
        if (edtTenDN.getText().toString().isEmpty()) {
            edtTenDN.setError("Must not empty!");
            return false;
        }
        if (edtMK.getText().toString().isEmpty()) {
            edtMK.setError("Must not empty !");
            return false;
        }

        if (!edtLMK.getText().toString().equals(edtMK.getText().toString())) {
            edtLMK.setError("Password not match!");
            return false;
        }
        tk = new TaiKhoan(edtTenDN.getText().toString(), edtMK.getText().toString());
        return true;
    }
}
