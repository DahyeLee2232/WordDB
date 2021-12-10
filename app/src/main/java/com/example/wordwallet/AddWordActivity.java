package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveBtn;
    EditText wordView;
    EditText meaningView;
    Button imageAdd;
    ImageView imageView;

    Uri uri;
    String imageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addword);

        saveBtn = findViewById(R.id.save_Btn);
        wordView = findViewById(R.id.word_edit);
        meaningView = findViewById(R.id.meaning_edit);
        imageAdd = findViewById(R.id.image_edit);
        imageView = findViewById(R.id.image_view);

        saveBtn.setOnClickListener(this);
        imageAdd.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            imageLink = data.getDataString();
            uri = data.getData();
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.image_edit){
            //이미지 추가 버튼을 누르면 기본 앱 갤러리로 넘어가서 사진을 골라온다
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);

            //선택된 이미지 표시
            if(uri != null){
                try {
                    InputStream in = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(view.getId() == R.id.save_Btn){
            String word = wordView.getText().toString();
            String meaning = meaningView.getText().toString();

            if(word.length() == 0 || meaning.length() == 0){
                Toast toast = Toast.makeText(this, "단어나 뜻은 비워둘 수 없습니다", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }

            DBHelper openHelper = new DBHelper(this);
            SQLiteDatabase db = openHelper.getWritableDatabase();

            Intent intent = getIntent();
            int listNumber = intent.getIntExtra("listnumber", 0);

            if(imageLink == null){
                //사진이 없으면
                db.execSQL("insert into word (word, meaning, imagelink, listnumber) " +
                        "values (?, ?, null,"+ listNumber +")", new String[]{word, meaning});
            }
            else{
                //사진이 있으면 이미지링크 포함해서 insert
                db.execSQL("insert into word (word, meaning, imagelink, listnumber) " +
                        "values (?, ?, ?, "+ listNumber +")", new String[]{word, meaning, imageLink});
            }

            //화면 갱신 코드
            db.close();
            finish();
        }

    }
}