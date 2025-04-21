package pojo;

import java.time.LocalDateTime;

public class Collect {

    private Integer id;             // 主键
    private Integer userId;        // 收藏者id
    private Integer postId;        // 收藏的帖子的id
    private Integer boardId;       // 收藏的板块id
    private String remark;          // 备注，收藏了个啥
    private Post post;
    private LocalDateTime time;


    public Collect() {
    }

    public Collect(Integer id, Integer userId, Integer postId, Integer boardId, String remark, Post post, LocalDateTime time) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.boardId = boardId;
        this.remark = remark;
        this.post = post;
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

    /**
     * 获取
     * @return post
     */
    public Post getPost() {
        return post;
    }

    /**
     * 设置
     * @param post
     */
    public void setPost(Post post) {
        this.post = post;
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
        return "Collect{id = " + id + ", userId = " + userId + ", postId = " + postId + ", boardId = " + boardId + ", remark = " + remark + ", post = " + post + ", time = " + time + "}";
    }
}