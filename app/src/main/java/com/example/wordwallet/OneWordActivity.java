package com.example.wordwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//단어 하나씩 보여주는 activity

public class OneWordActivity extends AppCompatActivity {

    ViewPager2 pager;
    MyStateAdapter adapter;

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

        pager = findViewById(R.id.view_pager);
        adapter = new MyStateAdapter(words);
        pager.setAdapter(adapter);

    }

    class Holder extends RecyclerView.ViewHolder{

        ImageButton prevBtn;
        ImageView wordImage;
        ImageButton nextBtn;
        TextView word;
        TextView meaning;

        public Holder(@NonNull View v) {
            super(v);
            prevBtn = v.findViewById(R.id.previous_btn);
            wordImage = v.findViewById(R.id.wordimage);
            nextBtn = v.findViewById(R.id.nextbtn);
            word = v.findViewById(R.id.word);
            meaning = v.findViewById(R.id.meaning);


            //클릭 리스너
            prevBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private class MyStateAdapter extends RecyclerView.Adapter<Holder> {
        ArrayList<ChildItem> words;

        MyStateAdapter(ArrayList<ChildItem> words){
            this.words = words;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_one_word, parent, false);
            return new Holder(view);

        }


        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.word.setText(words.get(position).word);
            holder.meaning.setText(words.get(position).meaning);

            if(words.get(position).imageLink.length() == 0){
                holder.wordImage.setImageResource();
            }
        }

        @Override
        public int getItemCount() {
            return words.size();
        }
    }


}

