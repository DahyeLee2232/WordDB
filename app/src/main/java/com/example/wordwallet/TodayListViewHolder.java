package com.example.wordwallet;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * 단어장 이름 뷰홀더
 */

class TodayListViewHolder extends RecyclerView.ViewHolder{
    private final TextView textView;

    TodayListViewHolder(View view) {
        super(view);
        textView = view.findViewById(android.R.id.text1);
    }

    TextView getTextView() {return textView;}
}

class TodayListAdapter extends RecyclerView.Adapter<TodayListViewHolder>{
    private ArrayList<WordListClass> list;

    public TodayListAdapter(ArrayList<WordListClass> list){
        this.list = list;
    }

    public TodayListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_multiple_choice, viewGroup, false);
        return new TodayListViewHolder(view);
    }

    public void onBindViewHolder(TodayListViewHolder viewHolder, final int position){
        String text = list.get(position).getListName();
        viewHolder.getTextView().setText(text);
    }

    public int getItemCount(){
        return list.size();
    }
}

class TodayListItemDecoration extends RecyclerView.ItemDecoration{
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){
        super.onDraw(c, parent, state);
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect, view, parent, state);

        int index = parent.getChildAdapterPosition(view)+1;

        outRect.set(20, 20, 20, 20);

        view.setBackgroundColor(0xFFF6F6F6);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state){
        super.onDrawOver(c, parent, state);
        int width = parent.getWidth();
        int height = parent.getHeight();
/*
        Drawable dr = ResourcesCompat.getDrawable(parent.getResources(), R.drawable.check1, null);

        int drWidth = dr.getIntrinsicWidth();
        int drHeight = dr.getIntrinsicHeight();

        int left = width / 2 - drWidth / 2;
        int top = height / 2 - drHeight / 2;

        c.drawBitmap(BitmapFactory.decodeResource(parent.getResources(), R.drawable.check1), left, top, null);
*/
    }
}
