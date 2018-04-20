package duongnh.com.appbaocao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.MainActivity;
import duongnh.com.appbaocao.adapter.DanhMucAdapter;
import duongnh.com.appbaocao.model.DanhMuc;

/**
 * Created by Admin on 4/20/2018.
 */

public class DanhMucFragment extends Fragment{
    private MainActivity main;
    private GridView gridView;
    private List<DanhMuc> arrDM;
    private DanhMucAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danh_muc_fragment, container, false);
        main = (MainActivity) getActivity();
        //initView
        gridView = view.findViewById(R.id.grid_view);

        //binData
        arrDM = new ArrayList<>();
        arrDM.add(new DanhMuc("Game",R.mipmap.ic_launcher,R.color.colorWhite));
        arrDM.add(new DanhMuc("Music",R.mipmap.ic_launcher,R.color.colorOrange));
        arrDM.add(new DanhMuc("Note",R.mipmap.ic_launcher,R.color.colorGrey));
        arrDM.add(new DanhMuc("Call/Sms",R.mipmap.ic_launcher,R.color.colorAccent));
        adapter = new DanhMucAdapter(main, R.layout.item_danh_muc,arrDM);
        gridView.setAdapter(adapter);
        //initEvent


        return view;
    }
}
