package service;

import pojo.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException;
}
