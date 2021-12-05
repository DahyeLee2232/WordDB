package com.example.wordwallet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListAdapter extends BaseExpandableListAdapter implements android.widget.ExpandableListAdapter {

    Context context;
    int parentLayout;
    int childLayout;
    ArrayList<ParentItem> parentItems;
    //key는 단어장 리스트의 id_pk, value는 단어장 이름
    ArrayList<ArrayList<ChildItem>> childItems;
    //각 단어장이 arraylist의 멤버가 됨

    public ExpandableListAdapter(Context context, ArrayList<ParentItem> parentItems, ArrayList<ArrayList<ChildItem>> childItems, int parentLayout, int childLayout){
        this.context = context;
        this.parentItems = parentItems;
        this.childItems = childItems;
        this.parentLayout = parentLayout;
        this.childLayout = childLayout;
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
            v = inflater.inflate(parentLayout, parent, false);
        }

        TextView listName = (TextView) v.findViewById(R.id.list_name);
        CheckBox bookmark = (CheckBox) v.findViewById(R.id.bookmark);

        listName.setText(getGroup(id).listName);

        return v;
    }

    @Override
    public View getChildView(int id, int index, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(childLayout, parent, false);
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

    public void addGroup(String listName){
        int id = -1;
        /**
         * 쿼리
         */

        //parentItems.put(id, listName);
    }

    public void addWord(int listNumber, String word, String meaning, String imageLink){
        int id = -1;
        /**
         * 쿼리
         */
/*
        if(id != -1){
            childItems.get(listNumber).add(new ChildItem(id, word, meaning, imageLink));
        }

 */
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