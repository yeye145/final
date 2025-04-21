package dao;

import pojo.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface PostDao {

    List<Post> getAllPostInThisBoardList(Integer boardId) throws SQLException;

    Post getThisPostById(Integer postId) throws Exception;

    void plusOneViewCount(Integer postId) throws Exception;

    Map<Integer, Post> getPostMapIn(String inClause) throws Exception;

    void deleteThisPost(Integer postId) throws Exception;

    void plusOneLikeCount(Integer postId) throws Exception;
}
