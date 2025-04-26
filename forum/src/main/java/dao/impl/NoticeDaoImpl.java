package dao.impl;

import dao.NoticeDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Notice;

import java.sql.SQLException;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {

    /*---------------------------------    获取一个版块的历史公告    ------------------------------------*/
    @Override
    public List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException {
        return MySearch.searchToList("SELECT id, `time`, content" +
                ", board_id AS boardId" +
                " FROM `forum`.`notice` WHERE board_id = ? ORDER BY time DESC", Notice.class, boardId);
    }


    /*---------------------------------    在该版块下发布一个新公告    ----------------------------------*/
    @Override
    public void publishNewNotice(Integer boardId, String content) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`notice`" +
                " (`board_id`, `content`) VALUES (?, ?)", boardId, content);
    }
}

