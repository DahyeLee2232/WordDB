package com.example.wordwallet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MyExpandableListAdapter extends BaseExpandableListAdapter implements android.widget.ExpandableListAdapter {

    Context context;
    MyWordFragment fragment;
    ArrayList<ParentItem> parentItems;
    //key는 단어장 리스트의 id_pk, value는 단어장 이름
    ArrayList<ArrayList<ChildItem>> childItems;
    //각 단어장이 arraylist의 멤버가 됨



    public MyExpandableListAdapter(Context context, MyWordFragment fragment, ArrayList<ParentItem> parentItems, ArrayList<ArrayList<ChildItem>> childItems){
        this.context = context;
        this.fragment = fragment;
        this.parentItems = parentItems;
        this.childItems = childItems;
    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount (int id) {
        return childItems.get(id).size();
    }

    @Override
    public ParentItem getGroup(int id) {
        return parentItems.get(id);
    }

    @Override
    public ChildItem getChild(int listId, int index) {
        return childItems.get(listId).get(index);
    }

    @Override
    public long getGroupId(int id) {
        return id;
    }

    @Override
    public long getChildId(int id, int index) {
        return index;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int id, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_parent_my, parent, false);
        }

        TextView listName = v.findViewById(R.id.list_name);
        ImageButton addWordBtn = v.findViewById(R.id.plus_btn);
        ImageButton goBtn = v.findViewById(R.id.arrow_btn);

        listName.setText(getGroup(id).listName);

        //버튼에 리스너 할당
        addWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //단어 추가 화면으로 넘김
                Intent intent = new Intent(context, AddWordActivity.class);
                intent.putExtra("listnumber", parentItems.get(id).id_pk);
                context.startActivity(intent);
                //화면 갱신
                FragmentTransaction ft = fragment.getParentFragmentManager().beginTransaction();
                ft.detach(fragment).attach(fragment).commit();
                notifyDataSetChanged();
            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //단어장 뷰페이저로 넘어간다
                Intent intent = new Intent(context, OneWordActivity.class);
                //이 때 단어장 하나를 준다
                intent.putExtra("wordlist", parentItems.get((int) getGroupId(id)).id_pk);
                context.startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public View getChildView(int id, int index, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_child, parent, false);
        }

        TextView word = v.findViewById(R.id.word);
        TextView meaning = v.findViewById(R.id.meaning);

        ChildItem c = getChild(id, index);

        word.setText(c.word);
        meaning.setText(c.meaning);

        return v;
    }

    @Override
    public boolean isChildSelectable(int id, int index) {
        return false;
    }

    //삭제는 dialog로 물어보고 실행
    public void removeGroup(int id){
        /**
         * 쿼리
         */
    }

    public void removeWord(int id){
        /**
         * 쿼리 후 새로고침
         */
    }


}