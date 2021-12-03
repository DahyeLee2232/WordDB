package com.example.wordwallet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayWordFragment extends Fragment {
    private RecyclerView recyclerView;
    private TodayListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<WordListClass> list;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        makeList();
    }

    private void makeList(){
        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        list = new ArrayList<WordListClass>();
        int id = -1;
        String listName = "";
        ArrayList<WordItem> wordList = new ArrayList<>();

        Cursor listCursor = db.rawQuery("select _id, name from wordlist where day_my=0", null);

        while(listCursor.moveToNext()){
            id = listCursor.getInt(0);
            listName = listCursor.getString(1);

            Cursor wordCursor = db.rawQuery("select word, meaning, imageLink from word where listnumber=?", new String[]{String.valueOf(id)});
            while(wordCursor.moveToNext()){
                wordList.add(new WordItem(wordCursor.getString(0), wordCursor.getString(1), wordCursor.getString(2)));
            }
            list.add(new WordListClass(listName, wordList));
        }
        db.close();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_todayword, container, false);

        Button addBtn = (Button) view.findViewById(R.id.add_Btn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ;
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.todayword_recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TodayListAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new TodayListItemDecoration());
        return view;
    }

}
