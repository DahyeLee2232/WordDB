package com.example.wordwallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuizFragment extends Fragment {

    Button Q1, Q2, Q3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_quiz, container, false);

        Q1 = (Button) view.findViewById(R.id.Q1);
        Q2 = (Button) view.findViewById(R.id.Q2);
        Q3 = (Button) view.findViewById(R.id.Q3);

        Q1.setOnClickListener(new View.OnClickListener() { // 첫번쨰 Quiz 누르면 범위 설정으로 넘어감

            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(WWmainActivity, wordListSelect.class);
                intent.putExtra("QuizNumber", 1);
                startActivity(intent);
                 */
            }
        });

        Q2.setOnClickListener(new View.OnClickListener() { // 두번째 Quiz 누르면 범위 설정으로 넘어감

            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(QuizFragment.this, wordListSelect.class);
                intent.putExtra("QuizNumber", 2);
                startActivity(intent);
                 */
            }
        });

        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    public void onListItemClick(RecyclerView r, View v, int position, long id){
        // super.onListItemClick(r, v, position, id);
    }

}
