package com.example.wordwallet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Quiz1 extends AppCompatActivity implements View.OnClickListener{

    TextView q1;
    Button btn1;
    Button btn2;
    Button btn3;

    ArrayList<ArrayList<String>> wordData  = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from word", null); // where listnumber exists ( 범위 선택 받은 것의 list)

            for(int j=1; j<cursor.getCount(); j++) {
                //n번째 단어의 스키마
                cursor.moveToNext();

                ArrayList<String> word = new ArrayList<String>();
                word.add(cursor.getString(1));
                word.add(cursor.getString(2));
                wordData.add(word);
            }

        // 임의로 넣어봄
        btn1.setText(wordData.get(1).get(1));
        btn2.setText(wordData.get(2).get(1));
        btn3.setText(wordData.get(4).get(1));

        db.close();
    }

    public void onClick(View v){
        /*
        if (v.getId()==R.id.button1){
            // 만약 답과 일치한다면 - 다음 문제 넘어감 (정답이라고 표시 할지 말지)
        }

        else {
            // 만약 답과 일치 안한다면 - 다시 풀게 함 (오답이라고 표시 할지 말지)
        }
         */
    }

}