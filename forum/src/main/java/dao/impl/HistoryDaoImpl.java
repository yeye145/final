package dao.impl;

import dao.HistoryDao;
import dao.utils.MyUpdate;

public class HistoryDaoImpl implements HistoryDao {


    @Override
    public void recordHistory(Integer postId, Integer userId) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`history` " +
                "(`user_id`, `post_id`) VALUES (?, ?)", postId, userId);
    }
}
