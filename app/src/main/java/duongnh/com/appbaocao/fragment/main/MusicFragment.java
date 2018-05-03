package duongnh.com.appbaocao.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.activity.MusicActivity;

/**
 * Created by Admin on 4/20/2018.
 */

public class MusicFragment extends Fragment implements View.OnClickListener {
    private ImageView ivBack;
    private MainActivity main;
    private TextView tvMusicOnline, tvMusicOff;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment, container, false);
        //initView
        main = (MainActivity) getActivity();
        ivBack = view.findViewById(R.id.iv_back_white);
        tvMusicOnline = view.findViewById(R.id.tv_music_online);
tvMusicOff = view.findViewById(R.id.tv_music_tu_may);

        //binData
        //initEvent
        ivBack.setOnClickListener(this);
        tvMusicOnline.setOnClickListener(this);
        tvMusicOff.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_white:
                main.showFragment(main.getMusicFragment(), main.getDanhMucFragment());
                break;
            case R.id.tv_music_online:
                String appName = "Zing MP3";
                String packageName = "com.zing.mp3";
                openApp(main, appName, packageName);
                break;
            case R.id.tv_music_tu_may:
                Intent intent = new Intent(main, MusicActivity.class);
                startActivity(intent);
                break;
        }
    }

    public static void openApp(Context context, String appName, String packageName) {
        if (isAppInstalled(context, packageName))
            if (isAppEnabled(context, packageName))
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
            else
                Toast.makeText(context, appName + " app is not enabled.", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, appName + " app is not installed.", Toast.LENGTH_SHORT).show();
    }
    private static boolean isAppInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return false;
    }

    private static boolean isAppEnabled(Context context, String packageName) {
        boolean appStatus = false;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(packageName, 0);
            if (ai != null) {
                appStatus = ai.enabled;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appStatus;
    }
}
