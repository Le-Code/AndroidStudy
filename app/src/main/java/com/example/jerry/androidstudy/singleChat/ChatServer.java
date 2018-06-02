package com.example.jerry.androidstudy.singleChat;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.jerry.androidstudy.SocketUrls;
import com.example.jerry.androidstudy.singleChat.entity.MessageBean;
import com.example.jerry.androidstudy.singleChat.entity.UserInfoBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;

/**
 * Created by jerry on 2018/5/25.
 */

public class ChatServer {
    private Socket socket;
    private Handler handler;
    private MessageBean messageBean;
    private Gson gson = new Gson();
    PrintWriter printWriter;
    InputStream input;
    OutputStream output;
    DataOutputStream dataOutputStream;
    public ChatServer(int status){
        initMessage(status);
        initChatServer();
    }

    private void initChatServer() {
        //开个线程接受消息
        receiveMessage();
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    /**
     * 初始化用户信息
     * @param status
     */
    private void initMessage(int status) {
        messageBean = new MessageBean();
        UserInfoBean userInfo = new UserInfoBean();
        userInfo.setUserId(2);
        messageBean.setMessageId(1);
        messageBean.setCharType(1);
        userInfo.setUserName("admin");
        userInfo.setUserPwd("123");
        //一下操作是为了模仿当用户点击了某个好友展开的聊天界面，保存用户id，和用户目标id
        if (status==1){
            messageBean.setUserId(1);
            messageBean.setFriendId(2);
        }else{
            messageBean.setUserId(2);
            messageBean.setFriendId(1);
        }
        ChatApplication.userInfoBean = userInfo;
    }

    /**
     * 发送消息
     * @param contentMsg
     */
    public void sendMessage(String contentMsg){
        try{
            if (socket==null){
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "服务器已关闭";
                handler.sendMessage(message);
                return;
            }
            byte[]str = contentMsg.getBytes("utf-8");
            String aaa = new String(str);
            messageBean.setContent(aaa);
            String messageJson = gson.toJson(messageBean);
            messageJson+="\n";
            output.write(messageJson.getBytes("utf-8"));
            output.flush();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("TAG", "错误: "+e.getMessage());
        }
    }

    /**
     * 接受消息
     */
    public void receiveMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    socket = new Socket(SocketUrls.IP,SocketUrls.PORT);
                    printWriter = new PrintWriter(socket.getOutputStream());
                    input = socket.getInputStream();
                    output = socket.getOutputStream();
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    //从客户端获取消息
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //读取服务器消息
                    String line;
                    while (true){
                        Thread.sleep(1000);
                        while ((line = reader.readLine())!=null){
                            Log.d("TAG", "内容: "+line);
                            MessageBean messageBean = gson.fromJson(line,MessageBean.class);
                            Message message = handler.obtainMessage();
                            message.obj = messageBean;
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                        if (socket==null)
                            break;
                        output.close();
                        input.close();
                        socket.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("TAG", "错误: "+e.getMessage());
                }
            }
        }).start();
    }

    public Socket getSocket(){
        return socket;
    }
}
