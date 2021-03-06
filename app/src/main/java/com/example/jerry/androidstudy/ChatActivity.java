package com.example.jerry.androidstudy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jerry on 2018/5/25.
 */

public class ChatActivity extends AppCompatActivity {
    private LinearLayout chat_ly;
    private TextView left_text,right_text;
    private EditText chat_et;
    private Button send_btn;
    private ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat_ly = (LinearLayout) findViewById(R.id.chat_ly);
        chat_et = (EditText) findViewById(R.id.chat_et);
        send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatApplication.chatServer.sendMessage(chat_et.getText().toString().trim());
                chat_ly.addView(initRightView(chat_et.getText().toString().trim()));
            }
        });

        //添加接受消息队列
        ChatApplication.chatServer.setChatHandler(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==1){
                    //发送回来消息后，更新ui
                    chat_ly.addView(initLeftView(msg.obj.toString()));
                }
            }
        });

    }

    /**
     * 靠左的消息
     * @param messageContent
     * @return
     */
    private View initLeftView(String messageContent) {
        left_text = new TextView(this);
        left_text.setLayoutParams(layoutParams);
        left_text.setGravity(View.FOCUS_LEFT);
        left_text.setText(messageContent);
        return left_text;
    }

    /**
     * 靠右的消息
     * @param messageContent
     * @return
     */
    private View initRightView(String messageContent) {
        right_text = new TextView(this);
        right_text.setLayoutParams(layoutParams);
        right_text.setGravity(View.FOCUS_RIGHT);
        right_text.setText(messageContent);
        return right_text;
    }
}
