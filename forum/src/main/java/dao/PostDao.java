package dao;

import pojo.Post;
import pojo.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface PostDao {

    /*---------------------------------    获取所有帖子（根据用户等级排序）    --------------------------*/
    List<Post> getAllPostOrderByGrade() throws SQLException;

    /*---------------------------------    获取我的所有帖子    ----------------------------------------*/
    List<Post> getMyPost(Integer userId) throws SQLException;

    /*---------------------------------    用户发布一条新帖子    ---------------------------------------*/
    void creatPost(Integer boardId, String title, String content, User user) throws Exception;

    /*---------------------------------    获取该版块下所有帖子的List集合    -----------------------------*/
    List<Post> getAllPostInThisBoardList(Integer boardId) throws SQLException;

    /*---------------------------------    通过id获取某条特定帖子    ------------------------------------*/
    Post getThisPostById(Integer postId) throws Exception;

    /*---------------------------------    浏览帖子时增加一个浏览数    ----------------------------------*/
    void plusOneViewCount(Integer postId) throws Exception;

    /*---------------------------------    发布评论时增加一个评论数    ----------------------------------*/
    void plusOneCommentCount(Integer postId) throws Exception;

    /*---------------------------------    获取某个id范围下的所有帖子Map集合    --------------------------*/
    Map<Integer, Post> getPostMapIn(String inClause) throws Exception;

    /*---------------------------------    通过id删除某条帖子    ---------------------------------------*/
    void deleteThisPost(Integer postId) throws Exception;

    /*---------------------------------    为某条帖子点个赞    -----------------------------------------*/
    void plusOneLikeCount(Integer postId) throws Exception;
}
