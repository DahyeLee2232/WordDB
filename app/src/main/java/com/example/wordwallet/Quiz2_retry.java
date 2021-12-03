package com.example.wordwallet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz2_retry extends AppCompatActivity{

    Button question, nextBtn, exit;
    EditText answer;
    TextView current;
    int correctCount = 0;
    MediaPlayer correct, incorrect;

    ArrayList<ArrayList<String>> wordData  = new ArrayList<ArrayList<String>>();
    ArrayList<String> Q = new ArrayList<String>();
    ArrayList<String> A = new ArrayList<String>();

    ArrayList<String> wrongDataQ  = new ArrayList<String>();
    ArrayList<String> wrongDataA  = new ArrayList<String>();

    int currentIndex = 0;
    int lastIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        question = findViewById(R.id.Question);
        answer = findViewById(R.id.answer);
        nextBtn = findViewById(R.id.nextbtn);
        current = findViewById(R.id.QuestionIndex2);
        exit = findViewById(R.id.exit);


        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from word", null);

        Intent intent = getIntent();

        lastIndex = intent.getIntExtra("wrongCount", -1);
        Q = intent.getStringArrayListExtra("wrongQ");
        A = intent.getStringArrayListExtra("wrongA");

        for(int j = 0; j<= lastIndex; j++) {
            //n번째 단어의 스키마
            ArrayList<String> word = new ArrayList<String>();
            word.add(Q.get(j));
            word.add(A.get(j));
            wordData.add(word);
        }

        cursor.close();
        db.close();

        Collections.shuffle(wordData); // 퀴즈 문제 랜덤

        displayQuestion2(currentIndex);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz2_retry.this, WWmainActivity.class);
                startActivity(intent);
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(answer.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(0))){ // 정답 -> 정답 Toast 띄우고 다음 문제 출제

                    correctCount++;
                    correct.start();


                    if(currentIndex == lastIndex){  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz2_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex",(lastIndex+1)); // 총 문제 수
                        intent.putExtra("Correct",correctCount); // 정답 수
                        intent.putExtra("QuizNumber",2);
                        intent.putExtra("retry",1);
                        startActivity(intent);
                    }

                    else{
                        currentIndex++;
                        displayQuestion2(currentIndex);
                    }

                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    answer.setText("");
                    incorrect.start();

                    wrongDataQ.add(wordData.get(currentIndex).get(0));
                    wrongDataA.add(wordData.get(currentIndex).get(1));

                    if(currentIndex == lastIndex){  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz2_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex",(lastIndex+1)); // 총 문제 수
                        intent.putExtra("Correct",correctCount); // 정답 수
                        intent.putExtra("QuizNumber",2);
                        intent.putExtra("retry",1);
                        startActivity(intent);
                    }

                }


            }
        });
    }

    public void displayQuestion2(int index){ //문제 띄우는 메소드

        current.setText((index+1) + "/" + (lastIndex+1));

        question.setText(wordData.get(index).get(1));
        answer.setText("");



    }
}