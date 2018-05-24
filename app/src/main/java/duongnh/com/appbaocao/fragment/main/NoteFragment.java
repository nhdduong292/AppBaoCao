package duongnh.com.appbaocao.fragment.main;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.adapter.NoteAdapter;
import duongnh.com.appbaocao.database.NoteDataBase;
import duongnh.com.appbaocao.model.Note;

/**
 * Created by Admin on 4/20/2018.
 */

public class NoteFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private ImageView ivBack, ivCalendar;
    private MainActivity main;
    private Button btnAdd;
    private ListView listNote;
    private NoteAdapter adapter;
    private List<Note> arrNote;
    private NoteDataBase noteDB;
    private Note n;

    //dialog
    private ImageView ivClose;
    private EditText edtName, edtContent;
    private TextView tvCalendar, tvAdd;
    private LinearLayout llCalender;
    private Note s;
    Calendar cal;
    Date date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_fragment, container, false);
        //initView
        main = (MainActivity) getActivity();
        ivBack = view.findViewById(R.id.iv_back_white);
        btnAdd = view.findViewById(R.id.btn_add_note);
        listNote = view.findViewById(R.id.list_note);
        tvCalendar = view.findViewById(R.id.tv_calendar);
        llCalender = view.findViewById(R.id.ll_calendar);

        //binData
        arrNote = new ArrayList<>();
        noteDB = new NoteDataBase(main);
        arrNote = noteDB.getAllNote();
        adapter = new NoteAdapter(main, R.layout.item_note, arrNote);
        listNote.setAdapter(adapter);
        cal=Calendar.getInstance();

        //initEvent
        ivBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        listNote.setOnItemLongClickListener(this);
        listNote.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_white:
                main.showFragment(main.getNoteFragment(), main.getDanhMucFragment());
                break;
            case R.id.btn_add_note:
                showDialogAdd();
                break;
        }
    }

    private void showDialogAdd() {
        final Dialog dialog = new Dialog(main, R.style.Theme_AppCompat);
        dialog.setContentView(R.layout.dialog_add_note);
        ivClose = dialog.findViewById(R.id.iv_close_dialog);
        edtName = dialog.findViewById(R.id.edt_name);
        edtContent = dialog.findViewById(R.id.edt_content);
        tvCalendar = dialog.findViewById(R.id.tv_calendar);
        llCalender = dialog.findViewById(R.id.ll_calendar);
        tvAdd = dialog.findViewById(R.id.tv_add);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        llCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] ngay = new int[1];
                final int[] thang = new int[1];
                final int[] nam = new int[1];
                DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                    //Sự kiện khi click vào nút Done trên Dialog
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set text cho textView
                        ngay[0] = day;
                        thang[0] = month;
                        nam[0] = year;
                        tvCalendar.setText(day + "/" + (month +1) + "/" + year);
                        //Lưu vết lại ngày mới cập nhật
                        cal.set(year, month, day);
                        date = cal.getTime();
                    }
                };
                //Hiển thị ra Dialog
                DatePickerDialog pic=new DatePickerDialog(
                        main,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen,
                        callback, nam[0], thang[0], ngay[0]);
                pic.setTitle("Chọn ngày hoàn thành");
                pic.show();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().isEmpty() && edtContent.getText().toString().isEmpty()) {
                    Toast.makeText(main, "Vui lòng nhập thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    n = new Note(edtName.getText().toString(), edtContent.getText().toString(), tvCalendar.getText().toString());
                    noteDB.addNote(n);
                    arrNote.clear();
                    arrNote.addAll(noteDB.getAllNote());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        ImageView iv = view.findViewById(R.id.iv_delete);
        iv.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageView iv = view.findViewById(R.id.iv_delete);
        s = arrNote.get(position);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int KQ = noteDB.deleteNote(s.getId());
                if (KQ > 0) {
                    arrNote.clear();
                    arrNote.addAll(noteDB.getAllNote());
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
