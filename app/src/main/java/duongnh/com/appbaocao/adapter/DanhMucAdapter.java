package duongnh.com.appbaocao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.model.DanhMuc;

/**
 * Created by Admin on 4/20/2018.
 */

public class DanhMucAdapter extends ArrayAdapter<DanhMuc> {
    private MainActivity main;
    private int res;
    private List<DanhMuc> arrDM;
    public DanhMucAdapter(@NonNull Context context, int resource, @NonNull List<DanhMuc> objects) {
        super(context, resource, objects);
        main = (MainActivity) context;
        res = resource;
        arrDM = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(main).inflate(R.layout.item_danh_muc, parent,false);
            viewHolder.ivDM = convertView.findViewById(R.id.iv_category);
            viewHolder.tvDM = convertView.findViewById(R.id.tv_category);
            viewHolder.llDM = convertView.findViewById(R.id.ll_dm);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        DanhMuc d = arrDM.get(position);
        viewHolder.tvDM.setText(d.getName());
        viewHolder.ivDM.setImageResource(d.getPicture());
        viewHolder.llDM.setBackgroundResource(d.getColor());
        return convertView;
    }
    public class ViewHolder{
        LinearLayout llDM;
        ImageView ivDM;
        TextView tvDM;
    }
}
