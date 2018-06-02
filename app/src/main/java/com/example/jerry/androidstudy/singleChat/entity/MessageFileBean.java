package com.example.jerry.androidstudy.singleChat.entity;

public class MessageFileBean {
    /**
     * 文件id
     */
    private int fileId;
    /**
     * 文件长度
     */
    private long fileLength;
    /**
     * 文件内容
     */
    private Byte[] fileByte;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件头名称
     */
    private String fileTitle;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public Byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(Byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }
}
