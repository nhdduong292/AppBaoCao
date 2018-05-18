package duongnh.com.appbaocao.fragment.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.activity.StartActivity;
import duongnh.com.appbaocao.common.Utils;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.database.TaiKhoanDataBase;
import duongnh.com.appbaocao.model.TaiKhoan;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Admin on 4/20/2018.
 */

public class MenuFragment extends Fragment implements View.OnClickListener {
    private ImageView ivClose,ivAvatar;
    private MainActivity main;
    private TextView tvten,tvTuoi,tvGioiTinh;
    private LinearLayout llHome, llNote, llGame, llMusic, llCall, llLog_out, llProfile;
    private TaiKhoanDataBase db;
    private TaiKhoan tk;

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
        llProfile = view.findViewById(R.id.ll_profile);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvten = view.findViewById(R.id.tv_ten);
        tvTuoi = view.findViewById(R.id.tv_tuoi);
        tvGioiTinh = view.findViewById(R.id.tv_gioitinh);

        //binData
        db = new TaiKhoanDataBase(main);
        tk = db.getTaiKhoanFull(Utils.getUser(main,Value.USER));
        tvten.setText(tk.getTen());
        tvTuoi.setText("Tuổi: "+tk.getTuoi());
        Toast.makeText(main, "avatar: "+tk.getAvatar(), Toast.LENGTH_SHORT).show();
        Picasso.with(main).load(tk.getAvatar()).into(ivAvatar);

        //initEvent
        ivClose.setOnClickListener(this);
        llHome.setOnClickListener(this);
        llCall.setOnClickListener(this);
        llMusic.setOnClickListener(this);
        llNote.setOnClickListener(this);
        llLog_out.setOnClickListener(this);
        llGame.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
        llProfile.setOnClickListener(this);
        return view;
    }

    public void loadData() {

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
                main.showFragment(main.getMenuFragment(), main.getCallSmsFragment());
                break;
            case R.id.ll_log_out:
                Value.START_FRAGMENT = Value.LOGIN_FRAGMENT;
                Intent intent = new Intent(main, StartActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_avatar:


                break;
            case R.id.ll_profile:
                main.showFragment(main.getMenuFragment(), main.getProFileFragment());
                break;
        }
    }

}
