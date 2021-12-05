package com.example.wordwallet;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
    ExpandableListAdapter adapter;
    ArrayList<ParentItem>  wordLists;        //단어장 이름
    ArrayList<ArrayList<ChildItem>> wordList;        //단어장 리스트

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        makeList();
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_todayword, container, false);

        addBtn = view.findViewById(R.id.add_Btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { }
        });

        listView = view.findViewById(R.id.expandable_wordlist);
        listView.setGroupIndicator(null);
        adapter = new ExpandableListAdapter(getContext(), wordLists, wordList, R.layout.item_parent, R.layout.item_child);
        listView.setAdapter(adapter);
        //단어장 펼침 닫힘

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                parent.smoothScrollToPosition(groupPosition);
                return false;
            }
        });

        return view;
    }

}
