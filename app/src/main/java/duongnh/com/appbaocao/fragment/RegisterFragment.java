package duongnh.com.appbaocao.fragment;

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

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText edtTenDN, edtMK, edtLMK;
    private Button btnDangKy;
    private TextView tvDangNhap;
    private StartActivity main;
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
        //binData
        //initEvent
        btnDangKy.setOnClickListener(this);
        tvDangNhap.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dang_ky:
                break;
            case R.id.tv_dang_nhap:
                break;
        }
    }
}
