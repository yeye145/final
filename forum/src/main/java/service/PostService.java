package service;

import pojo.Collect;
import pojo.History;
import pojo.Post;

import java.util.List;

public interface PostService {

    void cancelCollectThisPost(Integer postId, Integer userId) throws Exception;

    boolean checkIfCollect(Integer postId, Integer userId) throws Exception;

    /*-----------------------------------------    获得历史记录    --------------------------------------------*/
    List<Collect> getPostCollect(Integer userId) throws Exception;

    /*-----------------------------------------    新增历史记录    --------------------------------------------*/
    boolean collectThisPost(Integer postId, Integer userId, String remark) throws Exception;

    boolean recordPost(Integer postId, Integer userId) throws Exception;

    List<History> getPostHistory(Integer userId) throws Exception;

    /*--------------------------------------------    获取帖子    --------------------------------------------*/
    List<Post> getAllPostInThisBoardPrioritizeUserLike(Integer userId, Integer boardId) throws Exception;

    /*--------------------------------------------    获取帖子    --------------------------------------------*/
    Post getThisPostById(Integer postId) throws Exception;


    /*-----------------------------------    删除特定帖子，通过id    --------------------------------------------*/
    void deleteThesePost(List<Integer> postIdAboutToDelete);

    /*-----------------------------------------    为帖子点赞    ---------------------------------------------*/
    void likeThisPost(Integer postId) throws Exception;
}
