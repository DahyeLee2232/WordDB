package com.example.wordwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

//단어 하나씩 보여주는 activity

public class OneWordActivity extends AppCompatActivity {

    ViewPager2 pager;
    MyStateAdapter adapter;

    int num_pages;
    int listNumber;
    ArrayList<ChildItem> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word);

        Intent intent = getIntent();
        listNumber = intent.getIntExtra("listnumber", 0);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from word where listnumber="+listNumber, null);

        while (cursor.moveToNext()){
            words.add(new ChildItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        num_pages = words.size();

        pager = findViewById(R.id.view_pager);
        adapter = new MyStateAdapter(this, words);
        pager.setAdapter(adapter);

    }

    private class MyStateAdapter extends FragmentStateAdapter{
        ArrayList<ChildItem> words;

        MyStateAdapter(FragmentActivity fa, ArrayList<ChildItem> words){
            super(fa);
            this.words = words;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return new PagerFragment();
        }


        @Override
        public int getItemCount() {
            return num_pages;
        }

    }
}

