package pojo;

public class Report {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private Integer postId;
    private Integer reportedId;
    private String judge;

    public Report() {
    }

    public Report(Integer id, Integer userId, Integer boardId, Integer postId, Integer reportedId, String judge) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.postId = postId;
        this.reportedId = reportedId;
        this.judge = judge;
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
     * @return reportedId
     */
    public Integer getReportedId() {
        return reportedId;
    }

    /**
     * 设置
     * @param reportedId
     */
    public void setReportedId(Integer reportedId) {
        this.reportedId = reportedId;
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

    public String toString() {
        return "Report{id = " + id + ", userId = " + userId + ", boardId = " + boardId + ", postId = " + postId + ", reportedId = " + reportedId + ", judge = " + judge + "}";
    }
}