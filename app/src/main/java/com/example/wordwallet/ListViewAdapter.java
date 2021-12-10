package com.example.wordwallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewAdapterData> list = new ArrayList<ListViewAdapterData>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Context context = viewGroup.getContext();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,viewGroup,false);
        }

        TextView tvNum = (TextView)view.findViewById(R.id.listWord);
        TextView tvName = (TextView)view.findViewById(R.id.listMean);

        ListViewAdapterData listdata = list.get(i);

        tvNum.setText(listdata.getWord());
        tvName.setText(listdata.getMean());

        return view;
    }

    public void addItemToList(String word, String mean) {
        ListViewAdapterData listdata = new ListViewAdapterData();

        listdata.setWord(word);
        listdata.setMean(mean);

        list.add(listdata);

    }
}
