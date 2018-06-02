package com.example.jerry.androidstudy;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.jerry.androidstudy.entity.MessageBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jerry on 2018/5/25.
 * 聊天逻辑，最主要的
 */

public class ChatServer {

    private Socket socket;

    private Handler handler;

    private MessageBean messageBean;

    private Gson gson = new Gson();

    /**
     * 有socket对象得到输出流，并构造PrintWriter对象
     */
    PrintWriter printWriter;

    InputStream input;

    OutputStream output;

    DataOutputStream dataOutputStream;

    public ChatServer(){
        initMessage();
        initChatServer();
    }

    private void initChatServer() {
        //开个线程接受消息
        reveiveMessage();
    }

    /**
     * 接受消息，在子线程中
     */
    private void reveiveMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //向本机的8080端口发送发出客户请求
                    socket = new Socket(SocketUrls.IP,SocketUrls.PORT);
                    //有socket对象得到输出流，并构造相应的BufferedReader对象
                    printWriter = new PrintWriter(socket.getOutputStream());
                    input = socket.getInputStream();
                    output = socket.getOutputStream();
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    //从客户端获取信息
                    BufferedReader bff = new BufferedReader(new InputStreamReader(input));
                    //读取发来服务器消息
                    String line;
                    while (true){
                        Thread.sleep(500);
                        while ((line = bff.readLine())!=null){
                            Log.d("TAG", "内容: "+line);
                            Message message = handler.obtainMessage();
                            message.what =1;
                            message.obj = line;
                            handler.sendMessage(message);
                        }
                        if (socket==null)
                            break;
                    }
                    output.close();
                    input.close();
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("TAG", "错误: "+e.getMessage());
                }
            }
        }).start();
    }

    /**
     * 初始化用户信息
     */
    private void initMessage() {
        messageBean = new MessageBean();
        messageBean.setUserId(1);
        messageBean.setMessageId(1);
        messageBean.setCharType(1);
        messageBean.setUserName("admin");
        ChatApplication.userInfoBean = messageBean;
    }

    /**
     * 发送消息
     * @param contentMsg
     */
    public void sendMessage(String contentMsg) {
        try{
            if (socket==null){
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "服务器已关闭";
                handler.sendMessage(message);
                return;
            }
            byte[] str = contentMsg.getBytes("utf-8");
            String aaa = new String(str);
            messageBean.setContent(aaa);
            String messageJson = gson.toJson(messageBean);
            /**
             * 因为服务器那边的readLine()为阻塞阅读
             * 如果他读取不到换行虎哦这输出流结束就会一直阻塞再哪里
             * 所以再json消息的最后加上换上符号，告诉服务器，消息已经发送完毕了
             */
            messageJson+="\n";
            output.write(messageJson.getBytes("utf-8"));
            output.flush();
        }catch(Exception e){
            e.printStackTrace();
            Log.d("TAG", "错误: "+e.getMessage());
        }
    }

    /**
     * 消息队列，用于传送消息
     * @param chatHandler
     */
    public void setChatHandler(Handler chatHandler) {
        this.handler = chatHandler;
    }
}
