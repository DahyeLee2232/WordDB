package com.example.wordwallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuizFragment extends Fragment implements View.OnClickListener {

    Button Q1, Q2, Q3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_quiz, container, false);

        Q1 = (Button) view.findViewById(R.id.Q1);
        Q2 = (Button) view.findViewById(R.id.Q2);
        Q3 = (Button) view.findViewById(R.id.Q3);

        Q1.setOnClickListener(this);
        Q2.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_quiz, container, false);

    }

    @Override
    public void onClick(View v) {
        // implements your things
        switch (v.getId()){
            case R.id.Q1:
                Intent intent1 = new Intent(getActivity(), wordListSelect.class);
                intent1.putExtra("QuizNumber", 1);
                startActivity(intent1);
            case R.id.Q2:
                Intent intent2 = new Intent(getActivity(), wordListSelect.class);
                intent2.putExtra("QuizNumber", 2);
                startActivity(intent2);

        }
    }

}
