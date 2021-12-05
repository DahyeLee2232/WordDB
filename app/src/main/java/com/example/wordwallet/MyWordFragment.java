package com.example.wordwallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MyWordFragment extends Fragment {

    Button addBtn;
    ExpandableListView listView;
    MyExpandableListAdapter adapter;
    ArrayList<ParentItem>  wordLists;        //단어장 이름
    ArrayList<ArrayList<ChildItem>> wordList;        //단어장 리스트

    DBHelper helper;
    SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void makeList() {
        helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        wordLists = new ArrayList<>();
        wordList = new ArrayList<>();
        //db 값 들어올 변수들
        ParentItem p;
        ArrayList<ChildItem> l;

        Cursor listCursor = db.rawQuery("select _id, name from wordlist where day_my=1", null);

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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_myword, container, false);

        listView = view.findViewById(R.id.expandable_wordlist);
        listView.setGroupIndicator(null);
        adapter = new MyExpandableListAdapter(getContext(), wordLists, wordList);
        listView.setAdapter(adapter);
        //단어장 펼침 닫힘

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                parent.smoothScrollToPosition(groupPosition);
                return false;
            }
        });

        addBtn = view.findViewById(R.id.add_Btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //alertdialog 출력
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(getContext())
                        .setTitle("단어장 추가")
                        .setMessage("단어장 이름을 입력하세요")
                        .setView(R.layout.dialog_edit)
                        .setPositiveButton("추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //단어장 추가 쿼리
                                Dialog d = (Dialog) dialogInterface;
                                EditText ed = d.findViewById(R.id.dialog_edit_box);
                                String name = ed.getText().toString();

                                if(name.length() == 0){
                                    //입력이 없으면 창 닫고 토스트
                                    Toast blankErr = Toast.makeText(getContext(), "단어장 이름은 비울 수 없습니다.", Toast.LENGTH_LONG);
                                    blankErr.show();
                                }
                                else{
                                    db = helper.getWritableDatabase();
                                    db.execSQL("insert into wordlist (name, day_my) values (?, 1)", new String[] {name});
                                    db.close();
                                    makeList();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        });

                AlertDialog listAddPopUp = adBuilder.create();
                listAddPopUp.show();
            }
        });

        return view;
    }
}



