package dao.impl;

import dao.PostDao;
import dao.utils.MySearch;
import pojo.Post;

import java.sql.SQLException;
import java.util.List;

public class PostDaoImpl implements PostDao {


    @Override
    public List<Post> getAllPostInThisBoardList(Integer boardId) throws SQLException {
        return MySearch.searchToList("SELECT id, title, content, time" +
                ", author_id AS authorId" +
                ", author_name AS authorName" +
                ", board_id AS boardId" +
                ", view_count AS viewCount" +
                ", like_count AS likeCount" +
                ", comment_count AS commentCount " +
                "FROM `forum`.`post` WHERE board_id = ? ORDER BY time DESC", Post.class, boardId);
    }


}
