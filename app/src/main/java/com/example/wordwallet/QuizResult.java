package com.example.wordwallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizResult extends AppCompatActivity {

    TextView correct, total;
    ListView Review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        correct = findViewById(R.id.correct);
        total = findViewById(R.id.total);
        Review = findViewById(R.id.review);

        Intent intent = getIntent();

        int correctCount = intent.getIntExtra("Correct",0);
        int totalCount= intent.getIntExtra("lastIndex",0);
        ArrayList<String> wrongReviewQ = intent.getStringArrayListExtra("wrongDataQ");
        ArrayList<String> wrongReviewA = intent.getStringArrayListExtra("wrongDataA");

        correct.setText(String.valueOf(correctCount));
        total.setText("/ " + String.valueOf(totalCount));

        ArrayList<HashMap<String, String>> reviewData = new ArrayList<>();

        int wrongCount = totalCount - correctCount;

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
    }
}