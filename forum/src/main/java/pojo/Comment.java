package pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {

    private Integer id;
    private String content;
    private Integer postId;
    private Integer userId;
    private Integer parentId;            // 父评论
    private Integer likeCount;
    private LocalDateTime time;
    private String userName;
    private String userAvatar;
    private List<Comment> childComment = new ArrayList<>();   // 子评论列表

    public Comment() {
    }

    public Comment(Integer id, String content, Integer postId, Integer userId, Integer parentId, Integer likeCount, LocalDateTime time, String userName, String userAvatar, List<Comment> childComment) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.parentId = parentId;
        this.likeCount = likeCount;
        this.time = time;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.childComment = childComment;
    }

    public void addChildComment(Comment comment) {
        this.childComment.add(comment);
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
     * @return parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
     * @return userAvatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置
     * @param userAvatar
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * 获取
     * @return childComment
     */
    public List<Comment> getChildComment() {
        return childComment;
    }

    /**
     * 设置
     * @param childComment
     */
    public void setChildComment(List<Comment> childComment) {
        this.childComment = childComment;
    }

    public String toString() {
        return "Comment{id = " + id + ", content = " + content + ", postId = " + postId + ", userId = " + userId + ", parentId = " + parentId + ", likeCount = " + likeCount + ", time = " + time + ", userName = " + userName + ", userAvatar = " + userAvatar + ", childComment = " + childComment + "}";
    }
}