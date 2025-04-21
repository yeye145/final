package dao.impl;

import dao.PostDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PostDaoImpl implements PostDao {


    @Override
    public List<Post> getAllPostInThisBoardList(Integer boardId) throws SQLException {
        return MySearch.searchToList("SELECT id, title, content, time" +
                ", author_id AS authorId" +
                ", author_name AS authorName" +
                ", author_avatar AS authorAvatar" +
                ", board_id AS boardId" +
                ", view_count AS viewCount" +
                ", like_count AS likeCount" +
                ", comment_count AS commentCount " +
                "FROM `forum`.`post` WHERE board_id = ? ORDER BY time DESC", Post.class, boardId);
    }

    @Override
    public Post getThisPostById(Integer postId) throws Exception {
        return MySearch.searchToOne("SELECT id, title, content, time" +
                ", author_id AS authorId" +
                ", author_name AS authorName" +
                ", author_avatar AS authorAvatar" +
                ", board_id AS boardId" +
                ", view_count AS viewCount" +
                ", like_count AS likeCount" +
                ", comment_count AS commentCount " +
                "FROM `forum`.`post` WHERE id = ?", Post.class, postId);
    }

    @Override
    public void plusOneViewCount(Integer postId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`post`" +
                " SET view_count = view_count + 1" +
                " WHERE id = ?", postId);
    }

    @Override
    public Map<Integer, Post> getPostMapIn(String inClause) throws Exception {
        return MySearch.searchToMap(
                "SELECT id, title, time" +
                        ", author_id AS authorId" +
                        ", author_name AS authorName" +
                        ", author_avatar AS authorAvatar" +
                        ", board_id AS boardId" +
                        ", view_count AS viewCount" +
                        ", like_count AS likeCount" +
                        ", comment_count AS commentCount " +
                        "FROM `forum`.`post` WHERE id IN " + inClause,
                Post.class,
                "id"
        );
    }

    @Override
    public void deleteThisPost(Integer postId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`post` WHERE (`id` = ?);", postId);
    }


    @Override
    public void plusOneLikeCount(Integer postId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`post`" +
                " SET like_count = like_count + 1" +
                " WHERE (`id` = ?)", postId);
    }
}
