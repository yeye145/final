package dao;

import pojo.Notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {
    List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException;

    void publishNewNotice(Integer boardId, String content) throws Exception;
}
