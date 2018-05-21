package duongnh.com.appbaocao.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.adapter.WeatherAdapter;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.model.Weather;

public class WeatherNextFragment extends Fragment implements View.OnClickListener {
    private MainActivity main;
    private ImageView ivBack;
    private WeatherAdapter adapter;
    private List<Weather> listWeather;
    private ListView listView;
    private TextView tvName;
    private Weather w;
    private Date date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_next_fragment, container, false);
        main = (MainActivity) getActivity();
        //initView
        ivBack = view.findViewById(R.id.iv_back_white);
        listView = view.findViewById(R.id.list_weather);
        tvName = view.findViewById(R.id.tv_name);

        //binData
        listWeather = new ArrayList<>();
        getData(Value.WEATHER);
        adapter = new WeatherAdapter(main, R.layout.item_weather, listWeather);
        listView.setAdapter(adapter);

        //initEvent
        ivBack.setOnClickListener(this);
        return view;
    }

    public void getData(String data) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q="+data+"&units=metric&appid=0f7ea589629c9539c1618bda0f728c99";
        RequestQueue requestQueue = Volley.newRequestQueue(main);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listWeather.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonCity = jsonObject.getJSONObject("city");
                            String city = jsonCity.getString("name");
                            tvName.setText(city);

                            JSONArray jsonList = jsonObject.getJSONArray("list");

                            for (int i = 0; i < jsonList.length(); i++) {

                                if (i == 1 || i == 9|| i == 17 || i== 25 || i == 33 || i == jsonList.length()-2){
                                    JSONObject jsonObjectList = jsonList.getJSONObject(i);
                                    String ngay = jsonObjectList.getString("dt");
                                    long l = Long.parseLong(ngay);
                                    date = new Date(l * 1000L);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                                    String time = simpleDateFormat.format(date);
                                    JSONObject jsonTemp = jsonObjectList.getJSONObject("main");
                                    String min = jsonTemp.getString("temp_min");
                                    String max = jsonTemp.getString("temp_max");

                                    Double mi = Double.valueOf(min);
                                    String minn = String.valueOf(mi.intValue());

                                    Double ma = Double.valueOf(max);
                                    String maxx = String.valueOf(ma.intValue());

                                    JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                                    JSONObject jsonObjectweather = jsonArrayWeather.getJSONObject(0);
                                    String status = jsonObjectweather.getString("main");
                                    String icon = jsonObjectweather.getString("icon");
                                    w = new Weather(time, status, icon, minn, maxx);
                                    listWeather.add(w);
                                }
                            }
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, "loi roi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_white:
                main.showFragment(main.getWeatherNextFragment(), main.getWeatherFragment());
                break;
        }
    }

    public void binData() {
        getData(Value.WEATHER);
    }
}
