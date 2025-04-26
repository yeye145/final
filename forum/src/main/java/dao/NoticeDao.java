package dao;

import pojo.Notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {

    /*---------------------------------    获取一个版块的历史公告    ------------------------------------*/
    List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException;

    /*---------------------------------    在该版块下发布一个新公告    ----------------------------------*/
    void publishNewNotice(Integer boardId, String content) throws Exception;
}
