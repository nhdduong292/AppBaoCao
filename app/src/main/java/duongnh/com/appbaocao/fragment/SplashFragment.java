package duongnh.com.appbaocao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.StartActivity;

/**
 * Created by Admin on 4/19/2018.
 */

public class SplashFragment extends Fragment implements View.OnClickListener {
    private TextView tvSkip;
    private StartActivity main;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_fragment, container, false);
        main = (StartActivity) getActivity();
        //initView
        tvSkip = view.findViewById(R.id.tv_skip);
        //binData
        //initEvent
        tvSkip.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_skip:
                main.showFragment(main.getSplashFragment(), main.getLoginFragment());
                break;
        }
    }
}
