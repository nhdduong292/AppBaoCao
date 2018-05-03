package duongnh.com.appbaocao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.fragment.main.CallSmsFragment;
import duongnh.com.appbaocao.fragment.main.DanhMucFragment;
import duongnh.com.appbaocao.fragment.main.GameFragment;
import duongnh.com.appbaocao.fragment.main.MenuFragment;
import duongnh.com.appbaocao.fragment.main.MusicFragment;
import duongnh.com.appbaocao.fragment.main.NoteFragment;
import duongnh.com.appbaocao.fragment.main.ProFileFragment;

/**
 * Created by Admin on 4/19/2018.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout frameMain;
    private Fragment temp;

    private DanhMucFragment danhMucFragment = new DanhMucFragment();
    private GameFragment gameFragment = new GameFragment();
    private NoteFragment noteFragment = new NoteFragment();
    private MusicFragment musicFragment = new MusicFragment();
    private MenuFragment menuFragment = new MenuFragment();
    private CallSmsFragment callSmsFragment = new CallSmsFragment();
    private ProFileFragment proFileFragment = new ProFileFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //initView
        frameMain = findViewById(R.id.frame_main);

        //binData
        //initEvent

        initFragment();
        checkFragment();
    }

    private void checkFragment() {
        if(Value.MAIN_FRAGMENT == Value.GAME_FRAGMENT){
            showFragment(temp,gameFragment);
        }else if(Value.MAIN_FRAGMENT == Value.NOTE_FRAGMENT){
            showFragment(temp,noteFragment);
        }else if(Value.MAIN_FRAGMENT == Value.MUSIC_FRAGMENT){
            showFragment(temp,musicFragment);
        }else if(Value.MAIN_FRAGMENT == Value.CALL_FRAGMENT){
            showFragment(temp,callSmsFragment);
        }
    }

    private void initFragment() {
        temp = danhMucFragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, danhMucFragment);
        transaction.add(R.id.frame_main, gameFragment);
        transaction.add(R.id.frame_main, musicFragment);
        transaction.add(R.id.frame_main, noteFragment);
        transaction.add(R.id.frame_main, menuFragment);
        transaction.add(R.id.frame_main, callSmsFragment);
        transaction.add(R.id.frame_main, proFileFragment);
        transaction.hide(proFileFragment);
        transaction.hide(callSmsFragment);
        transaction.hide(gameFragment);
        transaction.hide(menuFragment);
        transaction.hide(musicFragment);
        transaction.hide(noteFragment);
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

    public DanhMucFragment getDanhMucFragment() {
        return danhMucFragment;
    }

    public GameFragment getGameFragment() {
        return gameFragment;
    }

    public NoteFragment getNoteFragment() {
        return noteFragment;
    }

    public MusicFragment getMusicFragment() {
        return musicFragment;
    }

    public MenuFragment getMenuFragment() {
        return menuFragment;
    }

    public Fragment getTemp() {
        return temp;
    }

    public CallSmsFragment getCallSmsFragment() {
        return callSmsFragment;
    }

    public ProFileFragment getProFileFragment() {
        return proFileFragment;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {

    }
}
