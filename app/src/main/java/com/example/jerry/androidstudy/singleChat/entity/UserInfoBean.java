package com.example.jerry.androidstudy.singleChat.entity;

/**
 * 用户信息
 */
public class UserInfoBean {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String likeName;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 用户头像
     */
    private String userIcon;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
