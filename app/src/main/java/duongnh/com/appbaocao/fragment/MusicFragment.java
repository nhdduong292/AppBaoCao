package duongnh.com.appbaocao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;

/**
 * Created by Admin on 4/20/2018.
 */

public class MusicFragment extends Fragment implements View.OnClickListener {
    private ImageView ivBack;
    private MainActivity main;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment, container, false);
        //initView
        main = (MainActivity) getActivity();
        ivBack = view.findViewById(R.id.iv_back_white);

        //binData
        //initEvent
        ivBack.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_white:
                main.showFragment(main.getMusicFragment(), main.getDanhMucFragment());
                break;
        }
    }
}
