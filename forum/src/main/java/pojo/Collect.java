package pojo;

public class Collect {

    private Integer id;             // 主键
    private Integer userId;        // 收藏者id
    private Integer postId;        // 收藏的帖子的id
    private Integer boardId;       // 收藏的板块id
    private String remark;          // 备注，收藏了个啥


    public Collect() {
    }

    public Collect(Integer id, Integer userId, Integer postId, Integer boardId, String remark) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.boardId = boardId;
        this.remark = remark;
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
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "Collect{id = " + id + ", userId = " + userId + ", postId = " + postId + ", boardId = " + boardId + ", remark = " + remark + "}";
    }
}