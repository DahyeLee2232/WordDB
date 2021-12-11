package com.example.wordwallet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    ImageButton imageButton;

    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageButton = (ImageButton)findViewById(R.id.profileImage);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    Glide.with(getApplication()).load(uri).into(imageButton);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }
}