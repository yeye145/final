package dao;

import pojo.Comment;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {

    /*----------------------------    通过id获取指定评论  --------------------------------------------*/
    Comment getCommentById(Integer commentId) throws SQLException;

    /*----------------------------    获取该帖子下所有评论  -------------------------------------------*/
    List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException;

    /*----------------------------    为某条评论点1个赞  ---------------------------------------------*/
    void plusOneLikeCount(Integer commentId) throws Exception;

    /*----------------------------    在某条帖子发布一条评论  ------------------------------------------*/
    void creatCommentOnPost(Integer postId, User user, String content) throws Exception;

    /*----------------------------    在某条评论下进行跟评  -------------------------------------------*/
    void creatCommentOnComment(Integer postId, Integer parentId, User user, String content) throws Exception;
}
