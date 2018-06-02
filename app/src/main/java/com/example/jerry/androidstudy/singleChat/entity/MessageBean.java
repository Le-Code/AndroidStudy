package com.example.jerry.androidstudy.singleChat.entity;

import java.io.Serializable;

/**
 * 聊天信息MessageBean
 */
public class MessageBean implements Serializable {

    /**
     * 消息id
     */
    private long messageId;
    /**
     * 群id
     */
    private String groupId;
    /**
     * 是否是群消息
     */
    private boolean isGroup;

    /**
     * 消息类型
     * 1，文本；2，图片；3，小视频；4，文件；5，地理位置；6，语音；7，视频通话
     */
    private int charType;
    /**
     * 文本消息内容
     */
    private String content;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 错误代码
     */
    private int errorCode;

    /**
     * 用户id
     */
    private int userId;
    /**
     * 目标好友id
     */
    private int friendId;
    /**
     * 消息附件
     */
    private MessageFileBean chatFile;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public int getCharType() {
        return charType;
    }

    public void setCharType(int charType) {
        this.charType = charType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public MessageFileBean getChatFile() {
        return chatFile;
    }

    public void setChatFile(MessageFileBean chatFile) {
        this.chatFile = chatFile;
    }
}
