package duongnh.com.appbaocao.fragment.main;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.adapter.ContactsAdapter;
import duongnh.com.appbaocao.model.Contacts;

/**
 * Created by Admin on 4/27/2018.
 */

public class CallSmsFragment extends Fragment implements View.OnClickListener {
    private ListView listCall;
    private ImageView ivBack;
    private MainActivity main;
    private String phoneNumber, name;
    private ContactsAdapter adapter;
    private ArrayList<Contacts> arr;
    private Cursor phones;
    private Intent intent;
    private Button btnCall, btnSms;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_sms_fragment,container,false);
        main = (MainActivity) getActivity();
        //initView
        listCall = view.findViewById(R.id.list_call);
        ivBack = view.findViewById(R.id.iv_back_white);
        //binData
        arr = new ArrayList<>();
        getNumber();
        //initEvent
        ivBack.setOnClickListener(this);
        listCall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogCallSms(position);
            }
        });

        return view;
    }
    public void getNumber()
    {
        phones = main.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Contacts s = new Contacts(name,phoneNumber);
            arr.add(s);
        }
        phones.close();// close cursor
        adapter = new ContactsAdapter(main,
                R.layout.item_contacts,arr);
        listCall.setAdapter(adapter);
        //display contact numbers in the list
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_white:
                main.showFragment(main.getCallSmsFragment(), main.getDanhMucFragment());
                break;
        }
    }
    private void showDialogCallSms(final int position) {

        final Dialog dialog = new Dialog(main);
        dialog.setContentView(R.layout.dialog_call_sms);
        ImageView ivClose = dialog.findViewById(R.id.iv_close_dialog);
        btnCall = dialog.findViewById(R.id.btn_call);
        btnSms = dialog.findViewById(R.id.btn_sms);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacts c = arr.get(position);
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ c.getNumber()));
                startActivity(intent);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacts c = arr.get(position);
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:"+c.getNumber()));
                startActivity(intent);
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
