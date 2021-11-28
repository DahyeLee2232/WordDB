package com.example.wordwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class TodayWordFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_todayword, container, false);
    }


    public void onListItemClick(RecyclerView r, View v, int position, long id){
        // super.onListItemClick(r, v, position, id);
    }
}
