package duongnh.com.appbaocao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.model.Note;

/**
 * Created by Admin on 4/23/2018.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    private Context mContext;
    private int res;
    private List<Note> arrNote;
    public NoteAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
        mContext = context;
        res = resource;
        arrNote = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false);
            viewHolder.tvNumber = convertView.findViewById(R.id.tv_number);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            viewHolder.ivDelete = convertView.findViewById(R.id.iv_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Note n = arrNote.get(position);
        viewHolder.tvNumber.setText(n.getId()+"");
        viewHolder.tvName.setText(n.getName());
        viewHolder.tvContent.setText(n.getContent());
        viewHolder.tvTime.setText(n.getTime());

        return convertView;
    }
    public class ViewHolder{
        TextView tvNumber, tvName, tvContent, tvTime;
        ImageView ivDelete;
    }
}
