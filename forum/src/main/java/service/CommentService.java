package service;

import pojo.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    /*-----------------------------------------    在该评论下发表评论    --------------------------------------*/
    boolean creatCommentOnComment(Integer postId, Integer boardId, Integer parentId, Integer userId, String content) throws Exception;

    /*-----------------------------------------    在该帖子下发表评论    --------------------------------------*/
    boolean creatCommentOnPost(Integer postId, Integer boardId, Integer userId, String content) throws Exception;

    List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException;


    /*-----------------------------------------    为评论点赞    ---------------------------------------------*/
    boolean likeThisComment(Integer commentId, Integer userId) throws Exception;
}
