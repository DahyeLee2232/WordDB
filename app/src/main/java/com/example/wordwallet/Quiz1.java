package com.example.wordwallet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz1 extends AppCompatActivity {

    Button q, btn1, btn2, btn3;
    TextView current;
    int currentIndex = 0;
    int lastIndex = -1;
    int tryCount = 0;
    int correctCount = 0;

    ArrayList<ArrayList<String>> wordData  = new ArrayList<ArrayList<String>>();
    ArrayList<String> wrongDataQ  = new ArrayList<String>();
    ArrayList<String> wrongDataA  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        q = findViewById(R.id.q);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        current = findViewById(R.id.QuestionIndex1);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from word", null); // where listnumber exists ( 범위 선택 받은 것의 list)

        for(int j=0; j<cursor.getCount(); j++) {
            //n번째 단어의 스키마

            cursor.moveToNext();

            ArrayList<String> word = new ArrayList<String>();
            word.add(cursor.getString(1));
            word.add(cursor.getString(2));
            wordData.add(word);

            lastIndex++;

        }
        cursor.close();
        db.close();

        Collections.shuffle(wordData);

        displayQuestion1(currentIndex);

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(btn1.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(1))){
                    Toast.makeText(Quiz1.this, "정답!", Toast.LENGTH_SHORT).show();
                    correctCount++;

                    if(currentIndex == lastIndex){  // 만약 index == 최종 -> 결과페이지
                        Toast.makeText(Quiz1.this, "퀴즈 종료!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex",lastIndex); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수 .toArray().lenght 안된다면 변수 생성
                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    Toast.makeText(Quiz1.this, "오답...", Toast.LENGTH_SHORT).show();
                    tryCount++;

                    if(tryCount == 1) { //처음으로 틀렸을 때 wrongQA에 추가
                        wrongDataQ.add(wordData.get(currentIndex).get(0));
                        wrongDataA.add(wordData.get(currentIndex).get(1));
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(btn2.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(1))){
                    Toast.makeText(Quiz1.this, "정답!", Toast.LENGTH_SHORT).show();

                    if(currentIndex == lastIndex){  // 만약 index == 최종 -> 결과페이지
                        Toast.makeText(Quiz1.this, "퀴즈 종료!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex",lastIndex); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수 .toArray().lenght 안된다면 변수 생성
                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    Toast.makeText(Quiz1.this, "오답...", Toast.LENGTH_SHORT).show();
                    tryCount++;

                    if(tryCount == 1) { //처음으로 틀렸을 때 wrongQA에 추가
                        wrongDataQ.add(wordData.get(currentIndex).get(0));
                        wrongDataA.add(wordData.get(currentIndex).get(1));
                    }
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(btn3.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(1))){
                    Toast.makeText(Quiz1.this, "정답!", Toast.LENGTH_SHORT).show();

                    if(currentIndex == lastIndex){  // 만약 index == 최종 -> 결과페이지
                        Toast.makeText(Quiz1.this, "퀴즈 종료!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex",lastIndex); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수 .toArray().lenght 안된다면 변수 생성
                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    Toast.makeText(Quiz1.this, "오답...", Toast.LENGTH_SHORT).show();
                    tryCount++;

                    if(tryCount == 1) { //처음으로 틀렸을 때 wrongQA에 추가
                        wrongDataQ.add(wordData.get(currentIndex).get(0));
                        wrongDataA.add(wordData.get(currentIndex).get(1));
                    }
                }
            }
        });
    }

    public void displayQuestion1(int index){ // 문제와 답 출력

        if(lastIndex>=2) {

            tryCount=0; // tryCount 초기화

            current.setText((index+1) + "/" + (lastIndex+1));

            q.setText(wordData.get(index).get(0));

            int correct = (int)((Math.random()*2)+1); // 옳은 답의 위치를 지정하는 난수

            int incorrectIndex1, incorrectIndex2; // 틀린 답을 출력하기 위한 wordData의 인덱스

            switch (correct) {
                case 1:
                    btn1.setText(wordData.get(index).get(1));

                    do {
                        incorrectIndex1 = (int) (Math.random() * lastIndex);
                    } while (index == incorrectIndex1);

                    do {
                        incorrectIndex2 = (int) (Math.random() * lastIndex);
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);

                    btn2.setText(wordData.get(incorrectIndex1).get(1));
                    btn3.setText(wordData.get(incorrectIndex2).get(1));
                    break;

                case 2:
                    btn2.setText(wordData.get(index).get(1));

                    do {
                        incorrectIndex1 = (int) (Math.random() * lastIndex);
                    } while (index == incorrectIndex1);

                    do {
                        incorrectIndex2 = (int) (Math.random() * lastIndex);
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);

                    btn1.setText(wordData.get(incorrectIndex1).get(1));
                    btn3.setText(wordData.get(incorrectIndex2).get(1));
                    break;

                case 3:
                    btn3.setText(wordData.get(index).get(1));

                    do {
                        incorrectIndex1 = (int) (Math.random() * lastIndex);
                    } while (index == incorrectIndex1);

                    do {
                        incorrectIndex2 = (int) (Math.random() * lastIndex);
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);

                    btn1.setText(wordData.get(incorrectIndex1).get(1));
                    btn2.setText(wordData.get(incorrectIndex2).get(1));
                    break;
            }
        }

        if(lastIndex<2){
            q.setText("단어를 더 추가해주세요..ㅎㅎ");
        }
    }
}