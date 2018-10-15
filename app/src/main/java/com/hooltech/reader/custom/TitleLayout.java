package com.hooltech.reader.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hooltech.reader.R;

public class TitleLayout extends LinearLayout {
    private Button button_right;
    private Button button_left;
    private TextView textView;
    private OnLeftAndRightClickListener listener;
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_title,this);
        textView = (TextView) findViewById(R.id.title_text);
        button_right = (Button) findViewById(R.id.button_right);
        button_left = (Button) findViewById(R.id.button_left);
// 按键监听只能在构造函数中做，不能通过  implements View.OnClickListener 接口实现来做，具体原因未知
        button_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.OnLeftButtonClick();
                }
            }
        });

        button_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.OnRightButtonClick();
                }
            }
        });

    }

    public void setOnLeftAndRightClickListener(OnLeftAndRightClickListener listener){
        this.listener = listener;
    }

    public Button getButton_left() {
        return button_left;
    }

    public Button getButton_right() {
        return button_right;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTitleText(String title){
        textView.setText(title);
    }

    public String getButtontext(String name){
        if(name.equals("left")){
            return button_left.getText().toString();
        } else if(name.equals("right")){
            return button_right.getText().toString();
        } else
            return "ERROR";
    }
    public void setButtontext(String text,String name){
        if(name.equals("left")){
            button_left.setText(text);
        } else if (name.equals("right")){
            button_right.setText(text);
        }
    }


    public interface OnLeftAndRightClickListener{
        void OnLeftButtonClick();
        void OnRightButtonClick();
    }
}
