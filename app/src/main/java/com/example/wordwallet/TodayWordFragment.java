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
    DBHelper helper = new DBHelper(getContext());
    SQLiteDatabase db = helper.getWritableDatabase();


    private ArrayList<Integer> listNumber;      //단어장 번호 관리(눈에 안 보임)
    private ArrayList<String> listName;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        makeList();
    }

    private void makeList(){
        listName = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select _id, name from wordlist where day_my=0", null);
        while(cursor.moveToNext()){
            listNumber.add(cursor.getInt(0));
            listName.add(cursor.getString(1));
        }
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

        adapter = new TodayListAdapter(listName);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new TodayListItemDecoration());
        return view;
    }

}
