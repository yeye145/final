package dao;

import pojo.Collect;

import java.util.List;

public interface CollectDao {

    /*----------------------------    获取帖子收藏列表（帖子内容未填充）  ----------------------------------*/
    List<Collect> getCollectWithNullPostInformation(Integer userId) throws Exception;

    /*----------------------------    用户收藏这条帖子  ------------------------------------------------*/
    void collectThisPost(Integer postId, Integer userId, String remark) throws Exception;

    /*----------------------------    用户取消收藏这条帖子  ---------------------------------------------*/
    void cancelCollectThisPost(Integer postId, Integer userId) throws Exception;
}
