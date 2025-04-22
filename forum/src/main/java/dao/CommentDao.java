package dao;

import pojo.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException;

    void plusOneLikeCount(Integer commentId) throws Exception;
}
