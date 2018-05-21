package duongnh.com.appbaocao.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.adapter.SongAdapter;
import duongnh.com.appbaocao.common.Value;
import duongnh.com.appbaocao.database.MediaManager;
import duongnh.com.appbaocao.model.Song;

/**
 * Created by Admin on 4/27/2018.
 */

public class MusicActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private ListView mListView;
    private SongAdapter mSongAdapter;
    private LinearLayout mLinearLayout;
    private TextView mName, mSingger;
    private ImageView mPlay;
    private ArrayList<Song> mListSong;
    private MediaManager mediaManager;
    private ImageView ivBack;
    private SeekBar seekBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity);
        //initView
        mListView = findViewById(R.id.list_music);
        mLinearLayout = findViewById(R.id.tab_bottom);
        mName = findViewById(R.id.tv_name);
        mSingger = findViewById(R.id.tv_singger);
        mPlay = findViewById(R.id.iv_play);
        ivBack = findViewById(R.id.iv_back_black);
        seekBar = findViewById(R.id.seek_bar);

        //binData
        mListSong = new ArrayList<>();
        mediaManager = new MediaManager(this);
        mListSong.addAll(mediaManager.getListSong());
        mSongAdapter = new SongAdapter(this, R.layout.item_song, mListSong);
        mListView.setAdapter(mSongAdapter);
        mLinearLayout.setVisibility(View.GONE);

        //initEvent
        mListView.setOnItemClickListener(this);
        mPlay.setOnClickListener(this);
        mLinearLayout.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mediaManager.play(position);
        updateMusic();
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_play:
                if (mediaManager.play() == false) {
                    mPlay.setImageResource(R.mipmap.ic_play_arrow_black_24dp);
                } else {
                    updateMusic();
                }
                break;
            case R.id.iv_back_black:
                mediaManager.stop();
                Value.MAIN_FRAGMENT = Value.MUSIC_FRAGMENT;
                Intent intent = new Intent(MusicActivity.this, MainActivity.class);
                startActivity(intent);
        }
    }
    public void updateMusic() {
        Song s = mediaManager.getCurrentSong();
        mName.setText(s.getmName());
        mSingger.setText(s.getmArtist());
        mPlay.setImageResource(R.mipmap.ic_pause_black_24dp);
        seekBar.setMax(s.getmDuration());
        new UpdateSeekBar().execute();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mediaManager.seek(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaManager.seek(seekBar.getProgress());
    }

    public class UpdateSeekBar extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while (mediaManager.isStarted()) {
                try {
                    Thread.sleep(1000);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
//            mTimeS.setText(mediaManager.getCurrentTimeText());
            seekBar.setProgress(mediaManager.getCurrentTime());
        }
    }
}
