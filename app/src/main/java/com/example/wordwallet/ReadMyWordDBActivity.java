package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ReadMyWordDBActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView wordView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_myword_db);

        wordView = findViewById(R.id.word);
        addBtn = findViewById(R.id.add_Btn);
        addBtn.setOnClickListener(this);

        ArrayList<HashMap<String, String>> wordData = new ArrayList<>();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from word", null);

        while(cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<>();
            map.put("word", cursor.getString(1));
            map.put("meaning", cursor.getString(2));
            wordData.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, wordData,
                android.R.layout.simple_list_item_2,
                new String[]{"word", "meaning"},
                new int[]{android.R.id.text1, android.R.id.text2});
        wordView.setAdapter(adapter);

        db.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //아직 구현 안함
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_Btn){
            //추가 화면으로 넘어감
            Intent intent = new Intent(this, MyWordDBActivity.class);
            startActivityForResult(intent, 0);
        }
    }
}