package com.hooltech.reader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hooltech.reader.R;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class worldAdapter extends RecyclerView.Adapter<worldAdapter.MyViewHolder> {

    private List<worldBean> mList;
    private static boolean mIsdeleteMode;


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;
        private CheckBox mCheckBox;
        public MyViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.my_img);
            mTextView = (TextView) v.findViewById(R.id.my_item);
            mCheckBox = (CheckBox) v.findViewById(R.id.my_checkbox);
        }

        public CheckBox getmCheckBox() {
            return mCheckBox;
        }
    }

    public worldAdapter(List<worldBean> myList){
        mList = myList;
    }



    @Override
    public worldAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("chenan","onCreateViewHolder");
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false));
        return myViewHolder;
    }

    //对item进行操作，如需要对textview进行操作，就是在这里面进行的
    @Override
    public void onBindViewHolder(worldAdapter.MyViewHolder holder, int position) {
//        Log.d("chenan1","onBindViewHolder");
//        holder.mCheckBox.setVisibility(MainActivity.getmIsDeletemode() ? View.VISIBLE : View.INVISIBLE);
//        //滑动会重新调用 onBindViewHolder ,checkbox状态会错乱
//        if(mList.get(position).checkboxStat)holder.mCheckBox.setChecked(true);
//        else holder.mCheckBox.setChecked(false);
        holder.mImageView.setImageResource(mList.get(position).itemImageResid);
        holder.mTextView.setText(mList.get(position).itemTitleid);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
