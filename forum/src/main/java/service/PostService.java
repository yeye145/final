package service;

import pojo.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostService {
    /*--------------------------------------------    获取帖子    --------------------------------------------*/
    List<Post> getAllPostInThisBoardPrioritizeUserLike(Integer userId, Integer boardId) throws Exception;

    /*--------------------------------------------    获取帖子    --------------------------------------------*/
    Post getThisPostById(Integer postId) throws Exception;
}
