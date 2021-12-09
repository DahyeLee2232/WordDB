package com.example.wordwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class WordListSelect extends AppCompatActivity {

    Intent intent;
    int QuizNum;
    Button selected;
    ListView listView;

    //전체 단어장 번호/단어장 이름, 한 쌍으로 기능한다
    ArrayList<Integer> showNumber;
    ArrayList<String> showList;

    //선택된 단어장 번호들
    ArrayList<Integer> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list_select);
/*

        selected = findViewById(R.id.selected);
        intent = getIntent();
        QuizNum = intent.getIntExtra("QuizNumber", 0);
        listView = findViewById(R.id.select_list);

        showNumber = new ArrayList<>();
        showList = new ArrayList<>();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from wordlist", null); // where listnumber exists ( 범위 선택 받은 것의 list)

        while(cursor.moveToNext()){
            showNumber.add(cursor.getInt(0));
            showList.add(cursor.getString(1));
        }

        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, showList);
        listView.setAdapter(adapter);

        selected.setOnClickListener(new View.OnClickListener() { // 저장 버튼 눌리면 선택된 단어 리스트 배열로 putExtra 해 줌

            @Override
            public void onClick(View v) {

                //putExtra할 리스트 만들기
                selectedList = new ArrayList<>();



                if (QuizNum == 1) {
                    Intent intent1 = new Intent(WordListSelect.this, Quiz1.class);
                    intent1.putExtra("ListNumber", selectedList);
                    //putExtra 단어장 리스트

                    startActivity(intent1);
                }
                if (QuizNum == 2) {
                    Intent intent1 = new Intent(WordListSelect.this, Quiz2.class);
                    intent1.putExtra("ListNumber", selectedList);
                    //putExtra 단어장 리스트
                    startActivity(intent1);
                }
            }

        });

        //-----------------------------------------------------------------------------------여기서부터 서치한 Custom Adaptor 코드



        //listView = findViewById(R.id.select_list);
        listView.setAdapter(adapter);
*/

    }
}


    class MyAdapter extends BaseAdapter {

        boolean[] checkBoxState;

        ArrayList<String> myList;

        public MyAdapter(ArrayList<String> myList) {

            this.myList = myList;
            checkBoxState = new boolean[myList.size()];
        }

        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {

            return myList.size();
        }

        // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Context context = parent.getContext();

            // 커스텀뷰 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.check_wordlist, parent, false);
            }

            // position에 위치한 데이터 획득
            String listViewItem = myList.get(position);

            final LinearLayout lin = convertView.findViewById(R.id.lin);
            final CheckBox checkBox = convertView.findViewById(R.id.checkBox);

            // 아이템 내 각 위젯에 데이터 반영
            checkBox.setText(listViewItem);

            checkBox.setOnClickListener(new CheckBox.OnClickListener() {

                public void onClick(View v) {

                    if (checkBoxState[position] == true) {
                        checkBoxState[position] = false;
                    } else {
                        checkBoxState[position] = true;
                    }
                }
            });

            lin.setOnClickListener(new Button.OnClickListener() {

                public void onClick(View v) {
                    //리스트 클릭시 체크박스에 체크
                    if (checkBox.isChecked() == false) {
                        checkBox.setChecked(true);
                        notifyDataSetChanged(); //새로고침 같은 기능
                        checkBoxState[position] = true;
                    }
                    //한번 더 누르면 체크 풀림
                    else {
                        checkBox.setChecked(false);
                        notifyDataSetChanged();
                        checkBoxState[position] = false;
                    }
                }
            });

            //여기서 checkBoxState가 true 인 아이들의 체크박스를 체크해주는 기능을 넣어주는 것이 중요합니다.
            //이러한 방식을 적용할 경우 converView로 뷰를 재사용하여 아래로 내려왔다 올라올 경우 다른 곳에 체크가 되거나 체크가 해제되는 것과 같은 문제를 해결할 수 있게 됩니다!
            if (checkBoxState[position] == true) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }

            return convertView;
        }

        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
        @Override
        public long getItemId(int position) {

            return position;
        }

        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public Object getItem(int position) {

            return myList.get(position);
        }

    }






