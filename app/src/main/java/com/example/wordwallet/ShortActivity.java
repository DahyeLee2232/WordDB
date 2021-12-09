package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class ShortActivity extends AppCompatActivity {
    VideoView videoView;
    int id = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short);

        videoView = findViewById(R.id.video);
        Uri videoUri;

        //Video 1
        if(id == 1) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cnn_1);
        } else if(id == 2) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cnn_2);
        } else {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cnn_3);
        }


        //비디어뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'
        videoView.setMediaController(new MediaController(this));

        //VideoView가 보여줄 동영상의 경로 주소 설정하기
        videoView.setVideoURI(videoUri);

        //동영상을 읽어오는데 시간이 걸리므로..
        //비디오 로딩 준비가 끝났을 때 실행하도록
        //리스너 설정
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시 정지
        if(videoView!=null && videoView.isPlaying()) videoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(videoView!=null) videoView.stopPlayback();
    }
}