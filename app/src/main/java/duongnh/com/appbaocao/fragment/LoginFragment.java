package duongnh.com.appbaocao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.StartActivity;

/**
 * Created by Admin on 4/19/2018.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private TextView tvDangKy;
    private Button btnDangNhap;
    private EditText edtTenDN, edtMK;
    private StartActivity main;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        main = (StartActivity) getActivity();
        //initView
        edtTenDN = view.findViewById(R.id.edt_ten_dang_nhap);
        edtMK = view.findViewById(R.id.edt_mat_khau);
        btnDangNhap = view.findViewById(R.id.btn_dang_nhap);
        tvDangKy = view.findViewById(R.id.tv_dang_ky);
        //binData
        //initEvent
        btnDangNhap.setOnClickListener(this);
        tvDangKy.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dang_ky:
                main.showFragment(main.getLoginFragment(), main.getRegisterFragment());
                break;
            case R.id.btn_dang_nhap:
                break;
        }
    }
}
