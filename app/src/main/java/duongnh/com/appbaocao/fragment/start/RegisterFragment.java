package duongnh.com.appbaocao.fragment.start;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.StartActivity;
import duongnh.com.appbaocao.common.Utils;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.database.TaiKhoanDataBase;
import duongnh.com.appbaocao.model.TaiKhoan;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Admin on 4/19/2018.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText edtTenDN, edtMK, edtLMK, edtHoTen, edtTuoi;
    private ImageView ivAvatar;
    private String path="";
    private Button btnDangKy;
    private TextView tvDangNhap;
    private StartActivity main;
    private ProgressBar progressBar;
    private TaiKhoanDataBase db;
    private TaiKhoan tk;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        main = (StartActivity) getActivity();
        //initView
        edtTenDN = view.findViewById(R.id.edt_ten_dang_nhap);
        edtMK = view.findViewById(R.id.edt_mat_khau);
        edtLMK = view.findViewById(R.id.edt_lai_mat_khau);
        btnDangKy = view.findViewById(R.id.btn_dang_ky);
        tvDangNhap = view.findViewById(R.id.tv_dang_nhap);
        progressBar =view.findViewById(R.id.pro_register);
        edtHoTen = view.findViewById(R.id.edt_ho_ten);
        edtTuoi = view.findViewById(R.id.edt_tuoi);
        ivAvatar = view.findViewById(R.id.iv_avatar);

        //binData
        db = new TaiKhoanDataBase(main);

        //initEvent
        btnDangKy.setOnClickListener(this);
        tvDangNhap.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dang_ky:
                if(!setupError()){
                    return;
                }
                if(db.check_user(edtTenDN.getText().toString())){
                    db.addTaiKhoan(tk);
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main.showFragment(main.getRegisterFragment(), main.getLoginFragment());
                        }
                    }, 2000);
                    Toast.makeText(main, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main, "Tài khoản đã tồn tại !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.tv_dang_nhap:
                main.showFragment(main.getRegisterFragment(), main.getLoginFragment());
                break;
            case R.id.iv_avatar:
                Button btnCamera, btnGallery;
                final Dialog dialog = new Dialog(main);
                dialog.setTitle("Avatar");
                dialog.setContentView(R.layout.dialog_camera);

                btnCamera = dialog.findViewById(R.id.btn_camera);
                btnGallery = dialog.findViewById(R.id.btn_gallery);
                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, Value.CAMERA);
                        dialog.dismiss();
                    }
                });
                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, Value.GALLERY);
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
        }
    }
    private boolean setupError() {
        if (edtTenDN.getText().toString().isEmpty()) {
            edtTenDN.setError("Must not empty!");
            return false;
        }
        if (edtMK.getText().toString().isEmpty()) {
            edtMK.setError("Must not empty !");
            return false;
        }

        if (!edtLMK.getText().toString().equals(edtMK.getText().toString())) {
            edtLMK.setError("Password not match!");
            return false;
        }
        if (!edtHoTen.getText().toString().equals(edtHoTen.getText().toString())) {
            edtHoTen.setError("Password not match!");
            return false;
        }
        if (!edtTuoi.getText().toString().equals(edtTuoi.getText().toString())) {
            edtTuoi.setError("Password not match!");
            return false;
        }
        int uuid = new Random().nextInt((9999)+100);
        String id = "D"+uuid;

        tk = new TaiKhoan(id,edtTenDN.getText().toString(), edtMK.getText().toString(),
                edtHoTen.getText().toString(), edtTuoi.getText().toString(), imageViewToByte(ivAvatar));

        Log.e("EE",tk.getId().toString());
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            /*If result for REQUEST_CAMERA*/
            if (requestCode == Value.CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes); //quality image
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivAvatar.setImageBitmap(thumbnail);
                //path=destination.getPath();


            } else if (requestCode == Value.GALLERY) { /*If result for Gallery*/

                if (resultCode == RESULT_OK) {
//                    Uri uri = data.getData();
//                    String[] projection = {MediaStore.Images.Media.DATA};
//
//                    Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(projection[0]);
//                    String selectedImagePath = cursor.getString(columnIndex);
//                    cursor.close();
//
//                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
//
//
//                    ivAvatar.setImageBitmap(bitmap);
                    Uri uri = data.getData();

                    try {
                        InputStream inputStream = main.getContentResolver().openInputStream(uri);

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        ivAvatar.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //path=selectedImagePath;


                }
            }
            Toast.makeText(main, "path:"+path, Toast.LENGTH_SHORT).show();
        }
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
