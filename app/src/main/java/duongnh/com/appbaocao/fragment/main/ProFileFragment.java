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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.common.Value;

import static android.app.Activity.RESULT_OK;

public class ProFileFragment extends Fragment implements View.OnClickListener {
    private MainActivity main;
    private ImageView ivBack, ivAvatar;
    private EditText edtTenDN, edtMK, edtFullName, edtTuoi;
    private Button btnUpdate;
    private String path;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        main = (MainActivity) getActivity();
        //initView
        ivBack = view.findViewById(R.id.iv_back_white);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        edtTenDN = view.findViewById(R.id.edt_ten_dang_nhap);
        edtMK = view.findViewById(R.id.edt_mat_khau);
        edtFullName = view.findViewById(R.id.edt_ten);
        edtTuoi = view.findViewById(R.id.edt_tuoi);
        btnUpdate = view.findViewById(R.id.btn_update);

        //binData
        //initEvent
        btnUpdate.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_avatar:
                AlertDialog.Builder dialog = new AlertDialog.Builder(main);
                dialog.setTitle("Avatar");

                dialog.setMessage("Bạn muốn chọn ?");
                dialog.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, Value.CAMERA);
                    }
                });
                dialog.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, Value.GALLERY);

                    }
                });
                dialog.show();
                break;
            case R.id.iv_back_white:
                main.showFragment(main.getProFileFragment(), main.getMenuFragment());
                break;
            case R.id.btn_update:
                break;
        }

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
                path=destination.getPath();

            } else if (requestCode == Value.GALLERY) { /*If result for Gallery*/

                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String selectedImagePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
                    Drawable drawable = new BitmapDrawable(bitmap);

                    ivAvatar.setImageDrawable(drawable);
                    path=selectedImagePath;

                }
            }

        }
    }
}
