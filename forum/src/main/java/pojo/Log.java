package pojo;

import java.time.LocalDateTime;

public class Log {
    private Integer id;
    private Integer userId;
    private String userName;
    private String action;          // 进行了什么操作
    private LocalDateTime time;


    public Log() {
    }

    public Log(Integer id, Integer userId, String userName, String action, LocalDateTime time) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.action = action;
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
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
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
        return "Log{id = " + id + ", userId = " + userId + ", userName = " + userName + ", action = " + action + ", time = " + time + "}";
    }
}