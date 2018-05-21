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

import com.squareup.picasso.Picasso;

import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.model.Weather;

public class WeatherAdapter extends ArrayAdapter<Weather> {
    private Context mContext;
    private int res;
    private List<Weather> arr;
    public WeatherAdapter(@NonNull Context context, int resource, @NonNull List<Weather> objects) {
        super(context, resource, objects);
        mContext = context;
        res = resource;
        arr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder viewholder;
        if (convertView == null){
            viewholder = new Viewholder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent,false);
            viewholder.tvDay = convertView.findViewById(R.id.tv_ngay);
            viewholder.tvNhietDo = convertView.findViewById(R.id.tv_nhiet_do);
            viewholder.tvTrangThai = convertView.findViewById(R.id.tv_trangthai);
            viewholder.ivWeather = convertView.findViewById(R.id.iv_weather);
            convertView.setTag(viewholder);
        }else{
           viewholder = (Viewholder) convertView.getTag();
        }
        Weather w = arr.get(position);
        viewholder.tvDay.setText(w.getNgay());
        viewholder.tvTrangThai.setText(w.getTrangThai());
        viewholder.tvNhietDo.setText(w.getMinTemp()+"C - "+w.getMaxTemp()+"C");
        Picasso.with(mContext).load("http://openweathermap.org/img/w/"+w.getImage()+".png").into(viewholder.ivWeather);
        return convertView;
    }
    public class Viewholder{
        TextView tvDay, tvNhietDo, tvTrangThai;
        ImageView ivWeather;
    }
}
