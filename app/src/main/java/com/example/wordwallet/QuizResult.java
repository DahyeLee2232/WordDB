package com.example.wordwallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizResult extends AppCompatActivity {

    long first_time;
    long second_time;

    public void onBackPressed() {

        second_time = System.currentTimeMillis();
        Toast.makeText(QuizResult.this, "한번 더 누르면 퀴즈가 종료됩니다", Toast.LENGTH_SHORT).show();
        if(second_time - first_time < 2000){
            super.onBackPressed();
            Intent intent = new Intent(QuizResult.this, WWmainActivity.class);
            intent.putExtra("Quiz",1);
            startActivity(intent);
        }
        first_time = System.currentTimeMillis();
    }


    TextView correct, total;
    ListView Review;
    Button finish, retry;
    int correctCount, totalCount, QuizNumber, wrongCount, retryCount;
    Intent intent;
    ArrayList<String> wrongReviewQ, wrongReviewA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        correct = findViewById(R.id.correct);
        total = findViewById(R.id.total);
        Review = findViewById(R.id.review);

        finish = findViewById(R.id.finish);
        retry = findViewById(R.id.retry);


        intent = getIntent();

        correctCount = intent.getIntExtra("Correct",0);
        totalCount= intent.getIntExtra("lastIndex",0);
        wrongReviewQ = intent.getStringArrayListExtra("wrongDataQ");
        wrongReviewA = intent.getStringArrayListExtra("wrongDataA");
        QuizNumber = intent.getIntExtra("QuizNumber",0);
        retryCount = intent.getIntExtra("retry",0);

        correct.setText(String.valueOf(correctCount));
        total.setText("/ " + String.valueOf(totalCount));

        ArrayList<HashMap<String, String>> reviewData = new ArrayList<>();

        wrongCount = totalCount - correctCount;

        for(int i=0; i< wrongCount; i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("Q", wrongReviewQ.get(i));
            map.put("A", wrongReviewA.get(i));
            reviewData.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, reviewData,
                android.R.layout.simple_list_item_2,
                new String[]{"Q", "A"},
                new int[]{android.R.id.text1, android.R.id.text2});
        Review.setAdapter(adapter);



        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResult.this , WWmainActivity.class);
                intent.putExtra("Quiz",1);
                startActivity(intent);


            }
        });

        if(retryCount==1 || wrongCount == 0){
            retry.setVisibility(retry.INVISIBLE);
        }

        retry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(QuizNumber==1){
                    Intent intent1 = new Intent(QuizResult.this , Quiz1_retry.class);
                    intent1.putExtra("wrongQ", wrongReviewQ);
                    intent1.putExtra("wrongA", wrongReviewA);
                    intent1.putExtra("wrongCount", (wrongCount-1));
                    startActivity(intent1);

                }
                if(QuizNumber==2){
                    Intent intent2 = new Intent(QuizResult.this , Quiz2_retry.class);
                    intent2.putExtra("wrongQ", wrongReviewQ);
                    intent2.putExtra("wrongA", wrongReviewA);
                    intent2.putExtra("wrongCount", (wrongCount-1));
                    startActivity(intent2);
                }

            }
        });




    }


}