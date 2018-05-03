package duongnh.com.appbaocao.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.GameActivity;
import duongnh.com.appbaocao.activity.MainActivity;

/**
 * Created by Admin on 4/20/2018.
 */

public class GameFragment extends Fragment implements View.OnClickListener {
    private ImageView ivBack;
    private MainActivity main;
    private TextView tvGameOX;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);
        main = (MainActivity) getActivity();
        //initView
        ivBack = view.findViewById(R.id.iv_back_white);
        tvGameOX = view.findViewById(R.id.tv_game_ox);

        //binData
        //initEvent
        ivBack.setOnClickListener(this);
        tvGameOX.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_white:
                main.showFragment(main.getGameFragment(), main.getDanhMucFragment());
                break;
            case R.id.tv_game_ox:
                intent = new Intent(main, GameActivity.class);
                startActivity(intent);
                break;
        }
    }
}
