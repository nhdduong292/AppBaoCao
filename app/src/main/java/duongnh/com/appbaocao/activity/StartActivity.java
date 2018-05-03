package duongnh.com.appbaocao.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.fragment.LoginFragment;
import duongnh.com.appbaocao.fragment.RegisterFragment;
import duongnh.com.appbaocao.fragment.SplashFragment;

public class StartActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Fragment fragmentTemp = null;
    private SplashFragment splashFragment = new SplashFragment();
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        //initView
        frameLayout = findViewById(R.id.frame_start);

        //binData
        if (Build.VERSION.SDK_INT < 23) {
            //Do not need to check the permission
        }else {
            if (checkAndRequestPermissions()){
                //If you have already permitted the permission
            }
        }
        //initEvent
        initFragment();
        if(Value.START_FRAGMENT == Value.LOGIN_FRAGMENT){
            showFragment(fragmentTemp, loginFragment);
        }
    }

    private void initFragment() {
        fragmentTemp = splashFragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_start, splashFragment);
        transaction.add(R.id.frame_start, loginFragment);
        transaction.add(R.id.frame_start, registerFragment);
        transaction.hide(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();
    }
    public void showFragment(Fragment hide, Fragment show){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(hide);
        transaction.show(show);
        fragmentTemp = show;
        transaction.commit();
    }

    public Fragment getFragmentTemp() {
        return fragmentTemp;
    }

    public SplashFragment getSplashFragment() {
        return splashFragment;
    }

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public RegisterFragment getRegisterFragment() {
        return registerFragment;
    }
    private boolean checkAndRequestPermissions() {
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int callPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int groupStorePermission = ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE);


        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (callPhonePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (groupStorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission_group.STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            return false;
        }

        return true;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Permission Granted Successfully. Write working code here.
                } else {
                    //You did not accept the request can not use the functionality.
                }
                break;
            default:
        }
    }
}
