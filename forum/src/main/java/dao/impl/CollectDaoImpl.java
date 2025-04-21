package dao.impl;

import dao.CollectDao;
import dao.utils.MySearch;
import pojo.Collect;

import java.util.List;

public class CollectDaoImpl implements CollectDao {

    @Override
    public List<Collect> getCollectWithNullPostInformation(Integer userId) throws Exception {
        // 获取历史记录列表
        return MySearch.searchToList(
                "SELECT id, time, remark, post_id AS postId, user_id AS userId " +
                        "FROM `forum`.`collect` WHERE user_id = ? ORDER BY time DESC",
                Collect.class, userId
        );
    }
}
