package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//단어 하나씩 보여주는 activity

public class OneWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word);
    }
}