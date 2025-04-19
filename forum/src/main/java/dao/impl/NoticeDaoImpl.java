package dao.impl;

import dao.NoticeDao;
import dao.utils.MySearch;
import pojo.Notice;

import java.sql.SQLException;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {

    @Override
    public List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException {
        return MySearch.searchToList("SELECT id, `time`, content" +
                ", board_id AS boardId" +
                " FROM `forum`.`notice` WHERE board_id = ? ORDER BY time DESC", Notice.class, boardId);
    }
}
