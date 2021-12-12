package com.example.wordwallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
/*        View v = convertView;

        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_parent_today, parent, false);
        }

        TextView listName = v.findViewById(R.id.list_name);
        ImageButton shortcutBtn = v.findViewById(R.id.shortcut_btn);
        CheckBox bookmark = v.findViewById(R.id.bookmark);
        ImageButton goBtn = v.findViewById(R.id.arrow_btn);

        listName.setText(getGroup(id).listName);*/
public class PagerFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_one_word, container, false);

        return rootView;
    }

}
