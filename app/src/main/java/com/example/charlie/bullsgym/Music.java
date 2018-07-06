package com.example.charlie.bullsgym;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Music extends AppCompatActivity implements  View.OnClickListener{

    ImageView Play,Stop,Pause;
    MediaPlayer mediaPlayer;
    int pausemusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Play=findViewById(R.id.PlayMusic);
        Pause=findViewById(R.id.PauseMusic);
        Stop=findViewById(R.id.StopMusic);

        Play.setOnClickListener(this);
        Pause.setOnClickListener(this);
        Stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.PlayMusic:
                if(mediaPlayer==null){
                    mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.shortnsweet);
                    mediaPlayer.start();
                }else if(!mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(pausemusic);
                    mediaPlayer.start();
                }
                break;
            case R.id.PauseMusic:
                if(mediaPlayer!=null){
                    mediaPlayer.pause();
                    pausemusic=mediaPlayer.getCurrentPosition();

                }
                break;
            case R.id.StopMusic:
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer=null;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer=null;
        }
        finish();
    }
}
