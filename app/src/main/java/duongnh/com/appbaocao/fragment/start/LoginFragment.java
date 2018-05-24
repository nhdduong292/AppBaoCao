package duongnh.com.appbaocao.fragment.start;

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



import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.activity.StartActivity;
import duongnh.com.appbaocao.common.Utils;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.database.TaiKhoanDataBase;

/**
 * Created by Admin on 4/19/2018.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private TextView tvDangKy;
    private Button btnDangNhap;
//    private LoginButton loginButton;
//    private CallbackManager callbackManager;
    private EditText edtTenDN, edtMK;
    private StartActivity main;
    private Intent intent;
    private TaiKhoanDataBase db;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        main = (StartActivity) getActivity();
        //initView
        edtTenDN = view.findViewById(R.id.edt_ten_dang_nhap);
        edtMK = view.findViewById(R.id.edt_mat_khau);
        progressBar = view.findViewById(R.id.progreess_login);
        btnDangNhap = view.findViewById(R.id.btn_dang_nhap);
        tvDangKy = view.findViewById(R.id.tv_dang_ky);
//        loginButton = view.findViewById(R.id.login_button);
        //binData
        db = new TaiKhoanDataBase(main);

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
                setError();
                if (db.login(edtTenDN.getText().toString(), edtMK.getText().toString())) {
//                    Utils.setSharePreValue(main, Value.STATUS_LOGIN, Value.LOGIN_TRUE);
                    Utils.setUser(main, Value.USER,edtTenDN.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(main, MainActivity.class));
                        }
                    }, 2000);
//                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(main, "Wrong username or password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }
    public void setError(){
        if(edtTenDN.getText().toString().isEmpty()){
            edtTenDN.setError("Wrong Info!");
            return;
        }
        if (edtMK.getText().toString().isEmpty()){
            edtMK.setError("Wrong Info!");
            return;
        }
    }


}
