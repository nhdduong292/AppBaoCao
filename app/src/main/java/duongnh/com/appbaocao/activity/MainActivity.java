package duongnh.com.appbaocao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.fragment.DanhMucFragment;

/**
 * Created by Admin on 4/19/2018.
 */

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameMain;
    private Fragment temp;
    private DanhMucFragment danhMucFragment = new DanhMucFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //initView
        frameMain = findViewById(R.id.frame_main);
        //binData
        //initEvent
        initFragment();
    }
    private void initFragment() {
        temp = danhMucFragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, danhMucFragment);

        transaction.commit();
    }
    public void showFragment(Fragment hide, Fragment show){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(hide);
        transaction.show(show);
        temp = show;
        transaction.commit();
    }
}
