package dao.impl;

import dao.PostDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Post;
import pojo.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PostDaoImpl implements PostDao {

    /*---------------------------------    获取我的所有帖子    ----------------------------------------*/
    @Override
    public List<Post> getMyPost(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id, title, content, time" +
                ", author_id AS authorId" +
                ", author_name AS authorName" +
                ", author_avatar AS authorAvatar" +
                ", board_id AS boardId" +
                ", view_count AS viewCount" +
                ", like_count AS likeCount" +
                ", comment_count AS commentCount " +
                "FROM `forum`.`post` WHERE author_id = ? ORDER BY time DESC", Post.class, userId);
    }


    /*---------------------------------    用户发布一条新帖子    ---------------------------------------*/
    @Override
    public void creatPost(Integer boardId, String title, String content, User user) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`post` (`title`, `content`, `author_id`" +
                        ", `author_name`, `board_id`, `author_avatar`)" +
                        " VALUES (?, ?, ?, ?, ?, ?)"
                , title, content
                , user.getId(), user.getName()
                , boardId, user.getAvatar());
    }


    /*---------------------------------    获取该版块下所有帖子的List集合    -----------------------------*/
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


    /*---------------------------------    通过id获取某条特定帖子    ------------------------------------*/
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


    /*---------------------------------    浏览帖子时增加一个浏览数    ----------------------------------*/
    @Override
    public void plusOneViewCount(Integer postId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`post`" +
                " SET view_count = view_count + 1" +
                " WHERE id = ?", postId);
    }


    /*---------------------------------    获取某个id范围下的所有帖子Map集合    --------------------------*/
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


    /*---------------------------------    通过id删除某条帖子    ---------------------------------------*/
    @Override
    public void deleteThisPost(Integer postId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`post` WHERE (`id` = ?);", postId);
    }


    /*---------------------------------    为某条帖子点个赞    -----------------------------------------*/
    @Override
    public void plusOneLikeCount(Integer postId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`post`" +
                " SET like_count = like_count + 1" +
                " WHERE (`id` = ?)", postId);
    }
}
