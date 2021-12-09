package com.example.wordwallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {

    Button Q1, Q2, Q3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_quiz, container, false);

        Q1 = (Button) view.findViewById(R.id.Q1);
        Q2 = (Button) view.findViewById(R.id.Q2);
        Q3 = (Button) view.findViewById(R.id.Q3);

        Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), WordListSelect.class);
                intent1.putExtra("QuizNumber", 1);
                startActivity(intent1);
            }
        });

        Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), WordListSelect.class);
                intent2.putExtra("QuizNumber", 2);
                startActivity(intent2);
            }
        });
        return view;

    }

}
