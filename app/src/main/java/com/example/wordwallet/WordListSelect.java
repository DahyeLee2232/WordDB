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
import android.widget.TextView;

import java.util.ArrayList;

public class WordListSelect extends AppCompatActivity {

    Intent intent;
    int QuizNum;
    TextView selected;
    ListView listView;

    //단어장 목록
    ArrayList<ParentItem> showList;

    //선택된 단어장 번호들
    ArrayList<Integer> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list_select);

        selected = findViewById(R.id.selected);
        intent = getIntent();
        QuizNum = intent.getIntExtra("QuizNumber", 0);
        listView = findViewById(R.id.select_list);

        showList = new ArrayList<>();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        //일일 단어>나만의 단어로 순서 정렬해서 리스트 만듦
        Cursor cursor = db.rawQuery("select * from wordlist where day_my=0", null);
        while(cursor.moveToNext()){
            showList.add(new ParentItem(cursor.getInt(0), cursor.getString(1)));
        }
        cursor = db.rawQuery("select * from wordlist where day_my=1", null);
        while(cursor.moveToNext()){
            showList.add(new ParentItem(cursor.getInt(0), cursor.getString(1)));
        }
        db.close();

        MyAdapter adapter = new MyAdapter(showList);
        listView.setAdapter(adapter);

        selected.setOnClickListener(new View.OnClickListener() { // 저장 버튼 눌리면 선택된 단어 리스트 배열로 putExtra 해 줌

            @Override
            public void onClick(View v) {

                //putExtra할 리스트 만들기
                boolean[] checkBoxState;
                checkBoxState = adapter.checkBoxState;

                selectedList = new ArrayList<>();
                for(int i = 0; i < checkBoxState.length; i++){
                    if(checkBoxState[i] == true){
                        selectedList.add(showList.get(i).id_pk);
                    }
                }

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


    }
}

    class MyAdapter extends BaseAdapter {

        boolean[] checkBoxState;
        ArrayList<ParentItem> myList;

        public MyAdapter(ArrayList<ParentItem> myList) {
            this.myList = myList;
            checkBoxState = new boolean[myList.size()];
        }

        // Adapter에 사용되는 데이터의 개수
        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Context context = parent.getContext();

            // 커스텀뷰 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.check_wordlist, parent, false);
            }

            // position에 위치한 데이터 획득
            ParentItem listViewItem = myList.get(position);

            final LinearLayout lin = convertView.findViewById(R.id.lin);
            final CheckBox checkBox = convertView.findViewById(R.id.checkBox);

            // 아이템 내 각 위젯에 데이터 반영
            checkBox.setText(listViewItem.listName);

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
        public ParentItem getItem(int position) {

            return myList.get(position);
        }

    }






