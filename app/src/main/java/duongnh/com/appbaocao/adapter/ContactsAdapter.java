package duongnh.com.appbaocao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.model.Contacts;

/**
 * Created by Admin on 4/27/2018.
 */

public class ContactsAdapter extends ArrayAdapter<Contacts> {
    private Context mContext;
    private int res;
    private ArrayList<Contacts> arrC;

    public ContactsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contacts> objects) {
        super(context, resource, objects);
        mContext = context;
        res = resource;
        arrC = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, parent, false);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvSdt = convertView.findViewById(R.id.tv_sdt);
        Contacts c = arrC.get(position);
        tvName.setText(c.getName());
        tvSdt.setText(c.getNumber());
        return convertView;
    }
}
