package dao.impl;

import dao.CommentDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Comment;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    @Override
    public List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException {
        return MySearch.searchToList("SELECT id, content, time" +
                ", post_id AS postId, user_id AS userId" +
                ", parent_id AS parentId, like_count AS likeCount" +
                ", user_name AS userName, user_avatar AS userAvatar " +
                " FROM `forum`.`comment` WHERE post_id = ?" +
                " ORDER BY parent_id ASC, time ASC", Comment.class, postId);
    }


    @Override
    public void plusOneLikeCount(Integer commentId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`comment`" +
                " SET like_count = like_count + 1 WHERE (`id` = ?)", commentId);
    }

    @Override
    public void creatCommentOnPost(Integer postId, User user, String content) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`comment`" +
                " (`post_id`, `content`, `user_id`, `user_name`, `user_avatar`)" +
                " VALUES (?, ?, ?, ?, ?)", postId, content, user.getId(), user.getName(), user.getAvatar());
    }


}
