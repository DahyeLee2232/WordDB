package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class ShortActivity extends AppCompatActivity {
    VideoView videoView;
    Intent intent;
    int id = 1;
    ListView listView;


    ArrayList<ParentItem>  wordLists;        //단어장 이름
    ArrayList<ArrayList<ChildItem>> wordList;        //단어장 리스트


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short);

        String[] word = new String[100];
        String[] mean = new String[100];
        videoView = findViewById(R.id.video);
        Uri videoUri;
        listView = (ListView)findViewById(R.id.shortWord);

        //adapter에 쓰는 변수들
        wordLists = new ArrayList<>();
        wordList = new ArrayList<>();
        //db 값 들어올 변수들
        ParentItem p;
        ArrayList<ChildItem> l;


        // 인텐트 받아오는 부분
        intent= getIntent();
        id = intent.getIntExtra("wordlist",0);


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from word where listnumber=?", new String[]{String.valueOf(Listnumber)}); // where listnumber exists ( 범위 선택 받은 것의 list)

        Cursor cursor = db.rawQuery("select * from word", null);


        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            word[i] = cursor.getString(1);
            mean[i] = cursor.getString(2);
        }

        displayList();

        //Video 1
        if(id == 1) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cnn_1);
        } else if(id == 2) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cnn_2);
        } else {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cnn_3);
        }


//        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, word);
//        listView.setAdapter(listAdapter);




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

    void displayList() {
        DBHelper helper2 = new DBHelper(this);
        SQLiteDatabase database = helper2.getReadableDatabase();

//        Cursor cursor = database.rawQuery("select word, meaning from word where listnumber = ?", new String[] {"%" + id + "%"} , null);

        Cursor cursor = database.rawQuery("select word, meaning from word where listnumber = 2", null);



        ListViewAdapter adapter = new ListViewAdapter();

        while(cursor.moveToNext()) {
            adapter.addItemToList(cursor.getString(0), cursor.getString(1));
            Log.e("tag", "test : " + cursor.getString(0) + cursor.getString(1));
        }



        listView.setAdapter(adapter);

    }

}