package dao.impl;

import dao.CommentDao;
import dao.utils.MySearch;
import pojo.Comment;

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


}
