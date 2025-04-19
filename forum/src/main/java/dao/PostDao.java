package dao;

import pojo.Post;

import java.sql.SQLException;
import java.util.List;


public interface PostDao {

    List<Post> getAllPostInThisBoardList(Integer boardId) throws SQLException;

    Post getThisPostById(Integer postId) throws Exception;

    void plusOneViewCount(Integer postId) throws Exception;
}
