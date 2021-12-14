package com.example.wordwallet;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Quiz1 extends AppCompatActivity {

    long first_time;
    long second_time;

    public void onBackPressed() {

        second_time = System.currentTimeMillis();
        Toast.makeText(Quiz1.this, "한번 더 누르면 퀴즈가 종료됩니다", Toast.LENGTH_SHORT).show();
        if(second_time - first_time < 2000){
            super.onBackPressed();
            Intent intent = new Intent(Quiz1.this, WWmainActivity.class);
            intent.putExtra("Quiz",1);
            startActivity(intent);
        }
        first_time = System.currentTimeMillis();
    }


    Button q, btn1, btn2, btn3;
    TextView current;
    int currentIndex = 0;
    int lastIndex = -1;
    int correctCount = 0;
    Cursor cursor;

    ImageView home;
    MediaPlayer correct, incorrect;
    ArrayList<Integer> Listnumber;


    ArrayList<ArrayList<String>> wordData = new ArrayList<ArrayList<String>>();
    ArrayList<String> wrongDataQ = new ArrayList<String>();
    ArrayList<String> wrongDataA = new ArrayList<String>();

    ArrayList<String> W = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);


        q = findViewById(R.id.q);
        btn1 = findViewById(R.id.shortcut_btn);
        btn2 = findViewById(R.id.bookmark);
        btn3 = findViewById(R.id.button3);
        current = findViewById(R.id.QuestionIndex1);
        home = findViewById(R.id.menu);



        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);



        Intent intent = getIntent();
        Listnumber = intent.getIntegerArrayListExtra("ListNumber");

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        for(int a=0; a< Listnumber.size();a++){
            cursor = db.rawQuery("select * from word where listnumber="+Listnumber.get(a), null); // where listnumber exists ( 범위 선택 받은 것의 list)

            for (int j = 0; j < cursor.getCount(); j++) {
                //n번째 단어의 스키마

                cursor.moveToNext();


                ArrayList<String> word = new ArrayList<String>();
                word.add(cursor.getString(1));
                word.add(cursor.getString(2));
                wordData.add(word);

                lastIndex++;

            }

        }


        cursor = db.rawQuery("select * from word where listnumber=1", null);
        for(int h = 0; h<cursor.getCount();h++){
            cursor.moveToNext();
            W.add(cursor.getString(2));
        }





        cursor.close();
        db.close();

        Collections.shuffle(wordData);
        Collections.shuffle(W);

        displayQuestion1(currentIndex);


        btn1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (btn1.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(1))) {
                    correctCount++;
                    correct.start();


                    if (currentIndex == lastIndex) {  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex", (lastIndex + 1)); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        startActivity(intent);
                    } else {
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }


                }

                else {
                    incorrect.start();

                    wrongDataQ.add(wordData.get(currentIndex).get(0));
                    wrongDataA.add(wordData.get(currentIndex).get(1));


                    if (currentIndex == lastIndex) {  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex", (lastIndex + 1)); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        startActivity(intent);
                    } else {
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }
                }

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (btn2.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(1))) {
                    correctCount++;
                    correct.start();

                    if (currentIndex == lastIndex) {  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex", (lastIndex + 1)); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        startActivity(intent);
                    } else {
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }


                }

                else {
                    incorrect.start();

                    wrongDataQ.add(wordData.get(currentIndex).get(0));
                    wrongDataA.add(wordData.get(currentIndex).get(1));

                    if (currentIndex == lastIndex) {  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex", (lastIndex + 1)); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        startActivity(intent);
                    } else {
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }

                }


            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (btn3.getText().toString().equalsIgnoreCase(wordData.get(currentIndex).get(1))) {

                    correctCount++;
                    correct.start();

                    if (currentIndex == lastIndex) {  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex", (lastIndex + 1)); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        startActivity(intent);
                    } else {
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }


                }

                else {
                    incorrect.start();
                    wrongDataQ.add(wordData.get(currentIndex).get(0));
                    wrongDataA.add(wordData.get(currentIndex).get(1));


                    if (currentIndex == lastIndex) {  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", wrongDataQ);
                        intent.putExtra("wrongDataA", wrongDataA);
                        intent.putExtra("lastIndex", (lastIndex + 1)); // 총 문제 수
                        intent.putExtra("Correct", correctCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        startActivity(intent);
                    } else {
                        currentIndex++;
                        displayQuestion1(currentIndex);
                    }
                }
            }
        });


    }



    public void displayQuestion1(int index) { // 문제와 답 출력


        if (lastIndex > 1) {
            current.setText((index + 1) + "/" + (lastIndex + 1));


            q.setText(wordData.get(index).get(0));


            int correct = (int) ((Math.random() * 3) + 1); // 옳은 답의 위치를 지정하는 난수
            int incorrectIndex1, incorrectIndex2; // 틀린 답을 출력하기 위한 wordData의 인덱스


            switch (correct) {
                case 1:
                    btn1.setText(wordData.get(index).get(1));


                    do {
                        incorrectIndex1 = (int) (Math.random() * (lastIndex+1));
                    } while (index == incorrectIndex1);


                    do {
                        incorrectIndex2 = (int) (Math.random() * (lastIndex+1));
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);


                    btn2.setText(wordData.get(incorrectIndex1).get(1));
                    btn3.setText(wordData.get(incorrectIndex2).get(1));
                    break;


                case 2:
                    btn2.setText(wordData.get(index).get(1));


                    do {
                        incorrectIndex1 = (int) (Math.random() * (lastIndex+1));
                    } while (index == incorrectIndex1);


                    do {
                        incorrectIndex2 = (int) (Math.random() * (lastIndex+1));
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);


                    btn1.setText(wordData.get(incorrectIndex1).get(1));
                    btn3.setText(wordData.get(incorrectIndex2).get(1));
                    break;


                case 3:
                    btn3.setText(wordData.get(index).get(1));


                    do {
                        incorrectIndex1 = (int) (Math.random() * (lastIndex+1));
                    } while (index == incorrectIndex1);


                    do {
                        incorrectIndex2 = (int) (Math.random() * (lastIndex+1));
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);


                    btn1.setText(wordData.get(incorrectIndex1).get(1));
                    btn2.setText(wordData.get(incorrectIndex2).get(1));
                    break;
            }
        }


        else if (lastIndex == 1) {

            current.setText((index + 1) + "/" + (lastIndex + 1));


            q.setText(wordData.get(index).get(0));


            int correct = (int) ((Math.random() * 3) + 1); // 옳은 답의 위치를 지정하는 난수
            int wW;

            do {
                wW = (int) ((Math.random() * 3) + 1);
            } while (correct == wW);

            int incorrectIndex; // 틀린 답을 출력하기 위한 wordData의 인덱스


            switch (correct) {
                case 1:
                    btn1.setText(wordData.get(index).get(1));

                    switch (wW) {
                        case 2:
                            btn2.setText(W.get(index));

                            do {
                                incorrectIndex = (int) (Math.random() * (lastIndex+1));
                            } while (index == incorrectIndex);

                            btn3.setText(wordData.get(incorrectIndex).get(1));
                            break;
                        case 3:
                            btn3.setText(W.get(index));

                            do {
                                incorrectIndex = (int) (Math.random() * (lastIndex+1));
                            } while (index == incorrectIndex);

                            btn2.setText(wordData.get(incorrectIndex).get(1));
                            break;

                    }

                    break;


                case 2:
                    btn2.setText(wordData.get(index).get(1));


                    switch (wW) {
                        case 1:
                            btn1.setText("수용하다");

                            do {
                                incorrectIndex = (int) (Math.random() * (lastIndex+1));
                            } while (index == incorrectIndex);

                            btn3.setText(wordData.get(incorrectIndex).get(1));
                            break;

                        case 3:
                            btn3.setText("성공");

                            do {
                                incorrectIndex = (int) (Math.random() * (lastIndex+1));
                            } while (index == incorrectIndex);

                            btn1.setText(wordData.get(incorrectIndex).get(1));
                            break;
                    }
                    break;

                case 3:
                    btn3.setText(wordData.get(index).get(1));

                    switch (wW) {
                        case 1:
                            btn1.setText("처리하다");

                            do {
                                incorrectIndex = (int) (Math.random() * (lastIndex+1));
                            } while (index == incorrectIndex);

                            btn2.setText(wordData.get(incorrectIndex).get(1));
                            break;
                        case 2:
                            btn2.setText("원시적인");

                            do {
                                incorrectIndex = (int) (Math.random() * (lastIndex+1));
                            } while (index == incorrectIndex);

                            btn1.setText(wordData.get(incorrectIndex).get(1));
                            break;

                    }
                    break;
            }
        }

        else if (lastIndex == 0) {

            current.setText((index + 1) + "/" + (lastIndex + 1));


            q.setText(wordData.get(index).get(0));


            int correct = (int) ((Math.random() * 3) + 1); // 옳은 답의 위치를 지정하는 난수



            switch (correct) {
                case 1:
                    btn1.setText(wordData.get(index).get(1));
                    btn2.setText("문제");
                    btn3.setText("명령하다");
                    break;


                case 2:
                    btn2.setText(wordData.get(index).get(1));
                    btn1.setText("옳은");
                    btn3.setText("찬성하다");
                    break;

                case 3:
                    btn3.setText(wordData.get(index).get(1));
                    btn1.setText("명성");
                    btn2.setText("의무");
                    break;
            }
        }

        else if (lastIndex == -1) {
            q.setText("단어를 등록해주세요");

            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        }

    }
}