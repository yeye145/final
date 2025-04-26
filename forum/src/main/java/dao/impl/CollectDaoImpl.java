package dao.impl;

import dao.CollectDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Collect;

import java.util.List;

public class CollectDaoImpl implements CollectDao {


    /*----------------------------    获取帖子收藏列表（帖子内容未填充）  ----------------------------------*/
    @Override
    public List<Collect> getCollectWithNullPostInformation(Integer userId) throws Exception {
        // 获取帖子收藏列表
        return MySearch.searchToList(
                "SELECT id, time, remark, post_id AS postId, user_id AS userId " +
                        "FROM `forum`.`collect` WHERE user_id = ? ORDER BY time DESC",
                Collect.class, userId
        );
    }


    /*----------------------------    用户收藏这条帖子  ------------------------------------------------*/
    @Override
    public void collectThisPost(Integer postId, Integer userId, String remark) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`collect`" +
                " (`user_id`, `post_id`, `remark`)" +
                " VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE remark = ?", userId, postId, remark, remark);
    }


    /*----------------------------    用户取消收藏这条帖子  ---------------------------------------------*/
    @Override
    public void cancelCollectThisPost(Integer postId, Integer userId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`collect`" +
                " WHERE (`user_id` = ? AND `post_id` = ?)", userId, postId);
    }



}
