package dao.impl;

import dao.HistoryDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.History;

import java.util.List;


public class HistoryDaoImpl implements HistoryDao {

    /*----------------------------    浏览帖子时记录到历史记录中  ---------------------------------------*/
    @Override
    public void recordHistory(Integer postId, Integer userId) throws Exception {
        MyUpdate.update("INSERT INTO history (user_id, post_id, time) " +
                "VALUES (?, ?, NOW()) " +
                "ON DUPLICATE KEY UPDATE time = NOW()", userId, postId);
    }


    /*----------------------------    获取我的历史记录（帖子内容未填充）  ---------------------------------*/
    @Override
    public List<History> getHistoryWithNullPostInformation(Integer userId) throws Exception {
        // 获取历史记录列表
        return MySearch.searchToList(
                "SELECT id, time, post_id AS postId, user_id AS userId " +
                        "FROM `forum`.`history` WHERE user_id = ? ORDER BY time DESC",
                History.class, userId
        );
    }

}
