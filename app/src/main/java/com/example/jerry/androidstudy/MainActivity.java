package com.example.jerry.androidstudy;

import android.content.Intent;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText chat_name_text,chat_pwd_text;
    private Button chat_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat_name_text = (EditText) findViewById(R.id.chat_name_text);
        chat_pwd_text = (EditText) findViewById(R.id.chat_pwd_text);
        chat_login_btn = (Button) findViewById(R.id.chat_login_btn);
        chat_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getLogin(chat_name_text.getText().toString().trim(),chat_pwd_text.getText().toString().trim())){
                    getChatServer();
                    Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean getLogin(String userName, String pwd) {
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(pwd))
            return false;
        if (userName.equals("admin")&&pwd.equals("123")){
            return true;
        }
        return false;
    }

    public void getChatServer() {
        ChatApplication.chatServer = new ChatServer();
    }
}
