package com.example.wordwallet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayWordFragment extends Fragment {
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> listName;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        makeList();
    }

    private void makeList(){
        listName = new ArrayList<String>();
        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id, name from wordlist where day_my=0", null);
        while(cursor.moveToNext()){
            listName.add(cursor.getString(1));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todayword, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.todayword_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListAdapter(listName);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ListItemDecoration());
        return rootView;
    }


    public void onListItemClick(RecyclerView r, View v, int position, long id){
        // super.onListItemClick(r, v, position, id);
    }

}
