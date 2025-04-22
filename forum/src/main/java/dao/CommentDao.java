package dao;

import pojo.Comment;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException;

    void plusOneLikeCount(Integer commentId) throws Exception;

    void creatCommentOnPost(Integer postId, User user, String content) throws Exception;
}
