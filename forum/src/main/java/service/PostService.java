package service;

import pojo.Collect;
import pojo.History;
import pojo.Post;

import java.util.List;

public interface PostService {

    /*--------------------------------------------    发布帖子    --------------------------------------------*/
    void creatPost(Integer boardId, String title, String content, Integer userId) throws Exception;

    /*-----------------------------------------    取消收藏帖子    --------------------------------------------*/
    void cancelCollectThisPost(Integer postId, Integer userId) throws Exception;

    /*------------------------------------    判断帖子是否已收藏    --------------------------------------------*/
    boolean checkIfCollect(Integer postId, Integer userId) throws Exception;

    /*-----------------------------------------    获得历史记录    --------------------------------------------*/
    List<Collect> getPostCollect(Integer userId) throws Exception;

    /*-----------------------------------------    新增历史记录    --------------------------------------------*/
    boolean collectThisPost(Integer postId, Integer userId, String remark) throws Exception;

    /*------------------------------------    记录帖子在历史记录    --------------------------------------------*/
    boolean recordPost(Integer postId, Integer userId) throws Exception;

    /*------------------------------------    获取帖子的历史记录    --------------------------------------------*/
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
