package dao.impl;

import dao.LogDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Log;

import java.sql.SQLException;
import java.util.List;

public class LogDaoImpl implements LogDao {

    /*-----------------------------------    记录这个操作到日志中    ---------------------------------------------*/
    @Override
    public void recordThisActionInLog(Integer userId, String userName, String action) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`log`" +
                " (`user_id`, `user_name`, `action`) VALUES (?, ?, ?)", userId, userName, action);
    }

    /*-----------------------------------    获取操作日志的List集合    ------------------------------------------*/
    @Override
    public List<Log> getAllLog() throws SQLException {
        return MySearch.searchToList("SELECT id, action, time" +
                ", user_id AS userId" +
                ", user_name AS userName FROM `forum`.`log`", Log.class);
    }
}
