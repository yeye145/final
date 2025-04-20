package service;

import pojo.History;
import pojo.Post;

import java.util.List;

public interface PostService {

    boolean recordPost(Integer postId, Integer userId) throws Exception;

    List<History> getPostHistory(Integer userId) throws Exception;

    /*--------------------------------------------    获取帖子    --------------------------------------------*/
    List<Post> getAllPostInThisBoardPrioritizeUserLike(Integer userId, Integer boardId) throws Exception;

    /*--------------------------------------------    获取帖子    --------------------------------------------*/
    Post getThisPostById(Integer postId) throws Exception;
}
