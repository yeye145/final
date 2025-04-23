package pojo;

import java.time.LocalDateTime;

public class Message {
    private Integer id;
    private String content;
    private Integer userIdReceive;
    private Integer userIdSend;
    private String userNameSend;
    private String userAvatarSend;
    private Integer viewCount;
    private String type;
    private LocalDateTime time;

    public Message() {
    }

    public Message(Integer id, String content, Integer userIdReceive, Integer userIdSend, String userNameSend, String userAvatarSend, Integer viewCount, String type, LocalDateTime time) {
        this.id = id;
        this.content = content;
        this.userIdReceive = userIdReceive;
        this.userIdSend = userIdSend;
        this.userNameSend = userNameSend;
        this.userAvatarSend = userAvatarSend;
        this.viewCount = viewCount;
        this.type = type;
        this.time = time;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return userIdReceive
     */
    public Integer getUserIdReceive() {
        return userIdReceive;
    }

    /**
     * 设置
     * @param userIdReceive
     */
    public void setUserIdReceive(Integer userIdReceive) {
        this.userIdReceive = userIdReceive;
    }

    /**
     * 获取
     * @return userIdSend
     */
    public Integer getUserIdSend() {
        return userIdSend;
    }

    /**
     * 设置
     * @param userIdSend
     */
    public void setUserIdSend(Integer userIdSend) {
        this.userIdSend = userIdSend;
    }

    /**
     * 获取
     * @return userNameSend
     */
    public String getUserNameSend() {
        return userNameSend;
    }

    /**
     * 设置
     * @param userNameSend
     */
    public void setUserNameSend(String userNameSend) {
        this.userNameSend = userNameSend;
    }

    /**
     * 获取
     * @return userAvatarSend
     */
    public String getUserAvatarSend() {
        return userAvatarSend;
    }

    /**
     * 设置
     * @param userAvatarSend
     */
    public void setUserAvatarSend(String userAvatarSend) {
        this.userAvatarSend = userAvatarSend;
    }

    /**
     * 获取
     * @return viewCount
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置
     * @param viewCount
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取
     * @return time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String toString() {
        return "Message{id = " + id + ", content = " + content + ", userIdReceive = " + userIdReceive + ", userIdSend = " + userIdSend + ", userNameSend = " + userNameSend + ", userAvatarSend = " + userAvatarSend + ", viewCount = " + viewCount + ", type = " + type + ", time = " + time + "}";
    }
}