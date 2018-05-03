package duongnh.com.appbaocao.fragment.start;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.activity.StartActivity;
import duongnh.com.appbaocao.common.Utils;
import duongnh.com.appbaocao.common.Value;

/**
 * Created by Admin on 4/19/2018.
 */

public class SplashFragment extends Fragment implements View.OnClickListener {
    private TextView tvSkip;
    private StartActivity main;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_fragment, container, false);
        main = (StartActivity) getActivity();
        //initView
        tvSkip = view.findViewById(R.id.tv_skip);
        progressBar = view.findViewById(R.id.progreess);
        //binData
        //initEvent
        tvSkip.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_skip:
                new SplashTimer().execute();
                break;
        }
    }
    private class SplashTimer extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long millisPerProgress = 3000 / 100;
            int progress = 0;
            try {
                while (progress <= 80) {
                    progress++;
                    publishProgress(progress);
                    Thread.sleep(millisPerProgress);
                }

                while (progress <= 100) {
                    progress++;
                    publishProgress(progress);
                    Thread.sleep(millisPerProgress);
                }
            } catch (InterruptedException ignored) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!Value.LOGIN_TRUE.equals(Utils.getSharePreValue(main, Value.STATUS_LOGIN))) {
                main.showFragment(main.getSplashFragment(), main.getLoginFragment());
            } else {
                startActivity(new Intent(main, MainActivity.class));
            }

        }
    }
}
