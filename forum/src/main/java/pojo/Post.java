package pojo;

import java.time.LocalDateTime;

public class Post {

    private Integer id;
    private String title;
    private String content;
    private Integer authorId;
    private Integer boardId;
    private Integer viewCount;                  // 浏览次数
    private Integer likeCount;                  // 点赞数量
    private Integer commentCount;               // 评论条数
    private LocalDateTime time;


    public Post() {
    }

    public Post(Integer id, String title, String content, Integer authorId, Integer boardId, Integer viewCount, Integer likeCount, Integer commentCount, LocalDateTime time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.boardId = boardId;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
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
     * @return authorId
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置
     * @param authorId
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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
     * @return likeCount
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置
     * @param likeCount
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取
     * @return commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置
     * @param commentCount
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
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
        return "Post{id = " + id + ", title = " + title + ", content = " + content + ", authorId = " + authorId + ", boardId = " + boardId + ", viewCount = " + viewCount + ", likeCount = " + likeCount + ", commentCount = " + commentCount + ", time = " + time + "}";
    }
}