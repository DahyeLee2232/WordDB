package com.example.wordwallet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyWordFragment extends Fragment implements AdapterView.OnItemClickListener {

    private RecyclerView recyclerView;
    private MyListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> listName;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        makeList();
    }

    private void makeList(){
        listName = new ArrayList<String>();
        listName.add("+");      //나만의 단어장 추가용
        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id, name from wordlist where day_my=1", null);
        while(cursor.moveToNext()){
            listName.add(cursor.getString(1));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myword, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.myword_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyListAdapter(listName);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyListItemDecoration());
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}




