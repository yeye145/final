package pojo;

import java.time.LocalDateTime;

public class History {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private Post post;
    private LocalDateTime time;


    public History() {
    }

    public History(Integer id, Integer userId, Integer postId, Post post, LocalDateTime time) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
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
        return "History{id = " + id + ", userId = " + userId + ", postId = " + postId + ", post = " + post + ", time = " + time + "}";
    }
}