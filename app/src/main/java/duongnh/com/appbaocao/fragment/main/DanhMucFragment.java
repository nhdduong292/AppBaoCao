package duongnh.com.appbaocao.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.adapter.DanhMucAdapter;
import duongnh.com.appbaocao.model.DanhMuc;

/**
 * Created by Admin on 4/20/2018.
 */

public class DanhMucFragment extends Fragment implements View.OnClickListener {
    private MainActivity main;
    private GridView gridView;
    private List<DanhMuc> arrDM;
    private DanhMucAdapter adapter;
    private ImageView ivMore;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danh_muc_fragment, container, false);
        main = (MainActivity) getActivity();
        //initView
        gridView = view.findViewById(R.id.grid_view);
ivMore = view.findViewById(R.id.iv_more);

        //binData
        arrDM = new ArrayList<>();
        arrDM.add(new DanhMuc("Game",R.mipmap.ic_games_white_48dp,R.color.colorBrown));
        arrDM.add(new DanhMuc("Music",R.mipmap.ic_queue_music_white_48dp,R.color.colorOrange));
        arrDM.add(new DanhMuc("Note",R.mipmap.ic_create_white_48dp,R.color.colorGrey));
        arrDM.add(new DanhMuc("Call/Sms",R.mipmap.ic_call_white_48dp,R.color.colorAccent));
        arrDM.add(new DanhMuc("Weather",R.mipmap.ic_call_white_48dp,R.color.colorBlue));
        arrDM.add(new DanhMuc("Other..",R.mipmap.ic_call_white_48dp,R.color.colorYellow));
        adapter = new DanhMucAdapter(main, R.layout.item_danh_muc,arrDM);
        gridView.setAdapter(adapter);

        //initEvent
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    main.showFragment(main.getDanhMucFragment(), main.getGameFragment());
                }else if(position == 1){
                    main.showFragment(main.getDanhMucFragment(), main.getMusicFragment());
                }
                else if(position == 2){
                    main.showFragment(main.getDanhMucFragment(), main.getNoteFragment());
                }else if(position == 3){
                    main.showFragment(main.getDanhMucFragment(), main.getCallSmsFragment());
                }
                else if(position == 4){
                    main.showFragment(main.getDanhMucFragment(), main.getWeatherFragment());
                }
            }
        });
        ivMore.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_more:
                main.showFragment(main.getTemp(), main.getMenuFragment());
                break;
        }
    }
}
