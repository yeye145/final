package pojo;

import java.time.LocalDateTime;

public class BoardApply {
    private Integer id;
    private String title;
    private String notice;
    private Integer hostId;
    private LocalDateTime time;
    private String type;
    private String hostName;
    private String hostAvatar;
    private Integer grade;
    private Boolean ifDeal;

    public BoardApply() {
    }

    public BoardApply(Integer id, String title, String notice, Integer hostId, LocalDateTime time, String type, String hostName, String hostAvatar, Integer grade, Boolean ifDeal) {
        this.id = id;
        this.title = title;
        this.notice = notice;
        this.hostId = hostId;
        this.time = time;
        this.type = type;
        this.hostName = hostName;
        this.hostAvatar = hostAvatar;
        this.grade = grade;
        this.ifDeal = ifDeal;
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
     * @return notice
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 设置
     * @param notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
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

    /**
     * 获取
     * @return hostAvatar
     */
    public String getHostAvatar() {
        return hostAvatar;
    }

    /**
     * 设置
     * @param hostAvatar
     */
    public void setHostAvatar(String hostAvatar) {
        this.hostAvatar = hostAvatar;
    }

    /**
     * 获取
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取
     * @return ifDeal
     */
    public Boolean getIfDeal() {
        return ifDeal;
    }

    /**
     * 设置
     * @param ifDeal
     */
    public void setIfDeal(Boolean ifDeal) {
        this.ifDeal = ifDeal;
    }

    public String toString() {
        return "BoardApply{id = " + id + ", title = " + title + ", notice = " + notice + ", hostId = " + hostId + ", time = " + time + ", type = " + type + ", hostName = " + hostName + ", hostAvatar = " + hostAvatar + ", grade = " + grade + ", ifDeal = " + ifDeal + "}";
    }
}