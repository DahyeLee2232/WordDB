package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class WWmainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button todayBtn;
    private Button myBtn;
    private Button quizBtn;
    ImageButton profileBtn;

    private FragmentManager manager;
    private TodayWordFragment todayWordFragment;
    private MyWordFragment myWordFragment;
    private QuizFragment quizFragment;

    int Quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wwmain);

        todayBtn = findViewById(R.id.today_wordlist_tab);
        myBtn = findViewById(R.id.my_wordlist_tab);
        quizBtn = findViewById(R.id.quiz_tab);
        profileBtn = findViewById(R.id.gotoProfile);

        todayBtn.setOnClickListener(this);
        myBtn.setOnClickListener(this);
        quizBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);

        manager = getSupportFragmentManager();
        todayWordFragment = new TodayWordFragment();
        myWordFragment = new MyWordFragment();
        quizFragment = new QuizFragment();

        FragmentTransaction tf = manager.beginTransaction();
        tf.add(R.id.main_container, todayWordFragment);
        tf.commit();

        Intent intent = getIntent();
        Quiz = intent.getIntExtra("Quiz",0);

        if(Quiz==1){
            FragmentTransaction quiz = manager.beginTransaction();
            tf.replace(R.id.main_container, quizFragment);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == todayBtn){
            if(!todayWordFragment.isVisible()){
                FragmentTransaction tf = manager.beginTransaction();
                tf.replace(R.id.main_container, todayWordFragment);
                //tf.addToBackStack();
                tf.commit();
            }
        }
        else if (v == myBtn){
            if(!myWordFragment.isVisible()){
                FragmentTransaction tf = manager.beginTransaction();
                tf.replace(R.id.main_container, myWordFragment);
                //tf.addToBackStack();
                tf.commit();
            }
        }
        else if(v == quizBtn){
            if(!quizFragment.isVisible()){
                FragmentTransaction tf = manager.beginTransaction();
                tf.replace(R.id.main_container, quizFragment);
                //tf.addToBackStack();
                tf.commit();
            }
        }
        else if(v == profileBtn) {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }
    }
}
