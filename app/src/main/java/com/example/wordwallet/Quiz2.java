package com.example.wordwallet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz2 extends AppCompatActivity{

    Button question, nextBtn;
    EditText answer;
    TextView current;
    int correctCount = 0;

    ArrayList<ArrayList<String>> wordData  = new ArrayList<ArrayList<String>>();

    ArrayList<String> wrongDataQ  = new ArrayList<String>();
    ArrayList<String> wrongDataA  = new ArrayList<String>();

    int currentIndex = 0;
    int lastIndex = -1;
    int tryCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        question = findViewById(R.id.Question);
        answer = findViewById(R.id.answer);
        nextBtn = findViewById(R.id.nextbtn);
        current = findViewById(R.id.QuestionIndex2);


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from word", null);


        for(int j=0; j<cursor.getCount(); j++) { // word DB에서 2차원 배열로 단어장 불러옴

            cursor.moveToNext();
            ArrayList<String> word = new ArrayList<String>();
            word.add(cursor.getString(1)); // 1차원: [0] = 단어
            word.add(cursor.getString(2)); //       [1] = 뜻

            wordData.add(word); // 2차원: 2차원 배열에 [단어/뜻] 저장

            lastIndex++;

        }
        cursor.close();
        db.close();

        Collections.shuffle(wordData); // 퀴즈 문제 랜덤

        displayQuestion2(currentIndex);

        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(answer.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(0))){ // 정답 -> 정답 Toast 띄우고 다음 문제 출제
                    Toast.makeText(Quiz2.this, "정답!", Toast.LENGTH_SHORT).show();
                    if(tryCount==0){
                        correctCount++;
                    }

                    if(currentIndex == lastIndex){  // 만약 index == 최종 -> 결과페이지
                        Toast.makeText(Quiz2.this, "퀴즈 종료!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Quiz2.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex",(lastIndex+1)); // 총 문제 수
                        intent.putExtra("Correct",correctCount); // 정답 수
                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        tryCount=0; // tryCount 초기화

                        currentIndex++;
                        displayQuestion2(currentIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    Toast.makeText(Quiz2.this, "오답...", Toast.LENGTH_SHORT).show();
                    answer.setText("");

                    tryCount++;

                    if(tryCount==1) { //처음으로 틀렸을 때 wrongQA에 추가
                        wrongDataQ.add(wordData.get(currentIndex).get(0));
                        wrongDataA.add(wordData.get(currentIndex).get(1));
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