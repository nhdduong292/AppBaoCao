package duongnh.com.appbaocao.fragment.main;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.common.Value;

public class WeatherFragment extends Fragment implements View.OnClickListener {
    private MainActivity main;
    private TextView tvThanhPho, tvTenNuoc, tvNhietDo, tvTrangThai, tvDoAm, tvMay, tvGio, tvDay;
    private ImageView ivBack, ivWeather, ivSearch;
    private EditText edtWeather;
    private Button btnWeather;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_fragment, container,false);
        main = (MainActivity) getActivity();
        //initView
        tvThanhPho = view.findViewById(R.id.tv_thanhpho);
        tvTenNuoc = view.findViewById(R.id.tv_quocgia);
        tvNhietDo = view.findViewById(R.id.tv_nhietdo);
        tvTrangThai = view.findViewById(R.id.tv_trangthai);
        tvDoAm = view.findViewById(R.id.tv_do_am);
        tvMay = view.findViewById(R.id.tv_may);
        tvGio = view.findViewById(R.id.tv_gio);
        tvDay = view.findViewById(R.id.tv_day);
        ivBack = view.findViewById(R.id.iv_back_white);
        ivWeather = view.findViewById(R.id.iv_weather);
        btnWeather = view.findViewById(R.id.btn_weather);
        ivSearch = view.findViewById(R.id.iv_search);
        edtWeather = view.findViewById(R.id.edt_name);
        //binData
        getCurrentData(Value.WEATHER);

        //initEvent
        ivBack.setOnClickListener(this);
        btnWeather.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        return view;
    }
    public void getCurrentData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(main);
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=0f7ea589629c9539c1618bda0f728c99";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String day = jsonObject.getString("dt");
                    String name = jsonObject.getString("name");
                    tvThanhPho.setText(name);
                    long l = Long.valueOf(day);
                    Date date = new Date(l*1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy - MM - dd HH:mm");
                    String time = simpleDateFormat.format(date);
                    tvDay.setText(time);

                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject jsonWeather = jsonArray.getJSONObject(0);
                    String status = jsonWeather.getString("main");
                    String icon = jsonWeather.getString("icon");
                    Picasso.with(main).load("http://openweathermap.org/img/w/"+icon+".png").into(ivWeather);
                    tvTrangThai.setText(status);

                    JSONObject jsonMain = jsonObject.getJSONObject("main");
                    String nhietdo = jsonMain.getString("temp");
                    String doam = jsonMain.getString("humidity");
                    Double a = Double.valueOf(nhietdo);
                    String nd = String .valueOf(a.intValue());
                    tvNhietDo.setText(nd+" C");
                    tvDoAm.setText(doam+"%");

                    JSONObject jsonWind = jsonObject.getJSONObject("wind");
                    String gio = jsonWind.getString("speed");
                    tvGio.setText(gio+"m/s");

                    JSONObject jsonMay = jsonObject.getJSONObject("clouds");
                    String may = jsonMay.getString("all");
                    tvMay.setText(may+"%");

                    JSONObject jsonSys = jsonObject.getJSONObject("sys");
                    String country = jsonSys.getString("country");
                    tvTenNuoc.setText(country);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_white:
                main.showFragment(main.getWeatherFragment(), main.getDanhMucFragment());
                break;
            case R.id.btn_weather:
                if(edtWeather.getText().toString().equals("")){
                    main.showFragment(main.getWeatherFragment(), main.getWeatherNextFragment());
                }else{
                    Value.WEATHER = edtWeather.getText().toString();
                    main.showFragment(main.getWeatherFragment(), main.getWeatherNextFragment());
                }
                break;
            case R.id.iv_search:
                if (edtWeather.getText().toString().equals("")){
                    Toast.makeText(main, "Nhập tên thành phố!", Toast.LENGTH_SHORT).show();
                }else{
                    getCurrentData(edtWeather.getText().toString());
                }
                break;
        }
    }
}
