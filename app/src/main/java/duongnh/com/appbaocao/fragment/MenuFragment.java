package duongnh.com.appbaocao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;

/**
 * Created by Admin on 4/20/2018.
 */

public class MenuFragment extends Fragment implements View.OnClickListener {
    private ImageView ivClose;
    private MainActivity main;
    private LinearLayout llHome, llNote, llGame, llMusic, llCall, llLog_out;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        main = (MainActivity) getActivity();
        //initView
        ivClose = view.findViewById(R.id.iv_close);
        llHome = view.findViewById(R.id.ll_trang_chu);
        llNote = view.findViewById(R.id.ll_note);
        llGame = view.findViewById(R.id.ll_game);
        llMusic = view.findViewById(R.id.ll_music);
        llCall = view.findViewById(R.id.ll_call_sms);
        llLog_out = view.findViewById(R.id.ll_log_out);

        //binData
        //initEvent
        ivClose.setOnClickListener(this);
        llHome.setOnClickListener(this);
        llCall.setOnClickListener(this);
        llMusic.setOnClickListener(this);
        llNote.setOnClickListener(this);
        llLog_out.setOnClickListener(this);
        llGame.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                main.showFragment(main.getMenuFragment(), main.getDanhMucFragment());
                break;
            case R.id.ll_trang_chu:
                main.showFragment(main.getMenuFragment(), main.getDanhMucFragment());
                break;
            case R.id.ll_note:
                main.showFragment(main.getMenuFragment(), main.getNoteFragment());
                break;
            case R.id.ll_game:
                main.showFragment(main.getMenuFragment(), main.getGameFragment());
                break;
            case R.id.ll_music:
                main.showFragment(main.getMenuFragment(), main.getMusicFragment());
                break;
            case R.id.ll_call_sms:

                break;
            case R.id.ll_log_out:
                break;
        }
    }
}
