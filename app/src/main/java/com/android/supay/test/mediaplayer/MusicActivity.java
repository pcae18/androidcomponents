package com.android.supay.test.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.supay.test.R;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicActivity extends AppCompatActivity {

    @BindView(R.id.btnPlay)
    Button buttonPlay;

    @BindView(R.id.btnPause)
    Button buttonPause;

    @BindView(R.id.btnStop)
    Button buttonStop;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnPlay) public void play(View view){
        if(player == null ){
            player = MediaPlayer.create( this, R.raw.song);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    @OnClick(R.id.btnPause) public void pause(View view){
        if(player!=null){
            player.pause();
        }
    }

    @OnClick(R.id.btnStop) public void ston(){
        stopPlayer();
    }

    private void stopPlayer() {
        if(player != null){
            player.release();
            player = null;
            Util.showToast("MediaPlayer released", this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
