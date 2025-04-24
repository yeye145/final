package pojo;

import java.time.LocalDateTime;

public class Report {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private Integer postId;
    private Integer reportedThisUserId;
    private Boolean ifDeal;
    private String reason;
    private String judge;
    private LocalDateTime time;

    public Report() {
    }

    public Report(Integer id, Integer userId, Integer boardId, Integer postId, Integer reportedThisUserId, Boolean ifDeal, String reason, String judge, LocalDateTime time) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.postId = postId;
        this.reportedThisUserId = reportedThisUserId;
        this.ifDeal = ifDeal;
        this.reason = reason;
        this.judge = judge;
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
     * @return boardId
     */
    public Integer getBoardId() {
        return boardId;
    }

    /**
     * 设置
     * @param boardId
     */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * 获取
     * @return postId
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * 设置
     * @param postId
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * 获取
     * @return reportedThisUserId
     */
    public Integer getReportedThisUserId() {
        return reportedThisUserId;
    }

    /**
     * 设置
     * @param reportedThisUserId
     */
    public void setReportedThisUserId(Integer reportedThisUserId) {
        this.reportedThisUserId = reportedThisUserId;
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

    /**
     * 获取
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取
     * @return judge
     */
    public String getJudge() {
        return judge;
    }

    /**
     * 设置
     * @param judge
     */
    public void setJudge(String judge) {
        this.judge = judge;
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
        return "Report{id = " + id + ", userId = " + userId + ", boardId = " + boardId + ", postId = " + postId + ", reportedThisUserId = " + reportedThisUserId + ", ifDeal = " + ifDeal + ", reason = " + reason + ", judge = " + judge + ", time = " + time + "}";
    }
}