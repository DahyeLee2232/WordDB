package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyWordDBActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveBtn;
    EditText wordView;
    EditText meaningView;
    Button imageAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myword_db);

        saveBtn = findViewById(R.id.save_Btn);
        wordView = findViewById(R.id.word_edit);
        meaningView = findViewById(R.id.meaning_edit);
        imageAdd = findViewById(R.id.image_edit);

        saveBtn.setOnClickListener(this);
        imageAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.save_Btn){
            String word = wordView.getText().toString();
            String meaning = meaningView.getText().toString();

            DBHelper openHelper = new DBHelper(this);
            SQLiteDatabase db = openHelper.getWritableDatabase();
            db.execSQL("insert into word (word, meaning, imagelink, listnumber) " +
                            "values (?, ?, null, 1)", new String[]{word, meaning});
            db.close();

            Intent intent = new Intent(this, ReadMyWordDBActivity.class);
            startActivity(intent);

        }
        else if(view.getId() == R.id.image_edit){
            //아직 이미지 추가 구현 안함
            ;
        }

    }
}