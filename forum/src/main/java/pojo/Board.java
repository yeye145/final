package pojo;

import java.time.LocalDateTime;

public class Board {
    private Integer id;
    private String title;
    private String type;            // 板块类型
    private LocalDateTime time;
    private Integer hostId;
    private Integer postCount;
    private String hostName;


    public Board() {
    }

    public Board(Integer id, String title, String type, LocalDateTime time, Integer hostId, Integer postCount, String hostName) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.time = time;
        this.hostId = hostId;
        this.postCount = postCount;
        this.hostName = hostName;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * 获取
     * @return hostId
     */
    public Integer getHostId() {
        return hostId;
    }

    /**
     * 设置
     * @param hostId
     */
    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    /**
     * 获取
     * @return postCount
     */
    public Integer getPostCount() {
        return postCount;
    }

    /**
     * 设置
     * @param postCount
     */
    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    /**
     * 获取
     * @return hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * 设置
     * @param hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String toString() {
        return "Board{id = " + id + ", title = " + title + ", type = " + type + ", time = " + time + ", hostId = " + hostId + ", postCount = " + postCount + ", hostName = " + hostName + "}";
    }
}