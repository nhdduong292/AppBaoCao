package duongnh.com.appbaocao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

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
        //initEvent
        initFragment();
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

    public SplashFragment getSplashFragment() {
        return splashFragment;
    }

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public RegisterFragment getRegisterFragment() {
        return registerFragment;
    }
}
