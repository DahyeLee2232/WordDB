package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WWmainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wwmain);

        //임시 버튼
        //Button listBtn = findViewById(R.id.list_btn);
        //listBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}