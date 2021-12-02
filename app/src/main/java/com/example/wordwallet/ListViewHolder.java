package com.example.wordwallet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 단어장 이름 뷰홀더(접근 제어자 default로 수정할 예정)
 */

class ListViewHolder extends RecyclerView.ViewHolder{
    private final TextView listName;

    ListViewHolder(View view) {
        super(view);
        listName = view.findViewById(android.R.id.text1);
    }

    TextView getListName() {return listName;}
}

class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{
    private ArrayList<String> list;

    public ListAdapter(ArrayList<String> list){
        this.list = list;
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new ListViewHolder(view);
    }

    public void onBindViewHolder(ListViewHolder viewHolder, final int position){
        String text = list.get(position);
        viewHolder.getListName().setText(list.get(position));
    }

    public int getItemCount(){
        return list.size();
    }
}

class ListItemDecoration extends RecyclerView.ItemDecoration{
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){
        super.onDraw(c, parent, state);

        int width = parent.getWidth();
        int height = parent.getHeight();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        c.drawRect(0, 0, width/2, height, paint);
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect, view, parent, state);

        int index = parent.getChildAdapterPosition(view)+1;

        if(index % 3 == 0){
            outRect.set(20, 20, 20, 60);
        }
        else{
            outRect.set(20, 20, 20, 20);
        }

        view.setBackgroundColor(0xFFECE9E9);
        ViewCompat.setElevation(view, 20.0f);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state){
        super.onDrawOver(c, parent, state);
        int width = parent.getWidth();
        int height = parent.getHeight();

        //배경 그릴 때
    }
}
