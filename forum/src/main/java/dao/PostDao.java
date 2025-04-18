package dao;

import pojo.Post;

import java.sql.SQLException;
import java.util.List;


public interface PostDao {

    List<Post> getAllPostInThisBoardList(Integer boardId) throws SQLException;
}
