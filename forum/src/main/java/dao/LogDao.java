package dao;

import pojo.Log;

import java.sql.SQLException;
import java.util.List;

public interface LogDao {
    /*-----------------------------------    记录这个操作到日志中    ---------------------------------------------*/
    void recordThisActionInLog(Integer userId, String userName, String action) throws Exception;

    /*-----------------------------------    获取操作日志的List集合    ------------------------------------------*/
    List<Log> getAllLog() throws SQLException;
}
