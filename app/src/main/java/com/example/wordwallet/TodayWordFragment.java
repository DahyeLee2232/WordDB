package com.example.wordwallet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class TodayWordFragment extends Fragment {

    Button addBtn;
    ExpandableListView listView;
    TodayExpandableListAdapter adapter;
    ArrayList<ParentItem>  wordLists;        //단어장 이름
    ArrayList<ArrayList<ChildItem>> wordList;        //단어장 리스트

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        makeList();
        return inflater.inflate(R.layout.fragment_todayword, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.expandable_wordlist);
        listView.setGroupIndicator(null);
        adapter = new TodayExpandableListAdapter(getContext(), wordLists, wordList);
        listView.setAdapter(adapter);
        //단어장 펼침 닫힘

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                parent.smoothScrollToPosition(groupPosition);
                return false;
            }
        });
    }

    private void makeList() {
        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        wordLists = new ArrayList<>();
        wordList = new ArrayList<>();
        //db 값 들어올 변수들
        ParentItem p;
        ArrayList<ChildItem> l;

        Cursor listCursor = db.rawQuery("select _id, name from wordlist where day_my=0", null);

        while (listCursor.moveToNext()) {
            //단어장 리스트 번호와 이름을 읽어온다
            p = new ParentItem(listCursor.getInt(0), listCursor.getString(1));

            //단어장 하나의 단어들을 읽어온다
            Cursor wordCursor = db.rawQuery("select _id, word, meaning, imageLink from word where listnumber="+p.id_pk, null);

            //단어장에 단어 하나 씩 저장
            l = new ArrayList<>();
            while(wordCursor.moveToNext()){
                l.add(new ChildItem(wordCursor.getInt(0), wordCursor.getString(1), wordCursor.getString(2), wordCursor.getString(3)));
            }
            wordLists.add(p);
            wordList.add(l);
        }
        db.close();
    }
}
