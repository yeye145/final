package service.impl;

import dao.NoticeDao;
import dao.impl.NoticeDaoImpl;
import pojo.Notice;
import service.NoticeService;

import java.sql.SQLException;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {

    private NoticeDao noticeDao = new NoticeDaoImpl();


    @Override
    public List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException {
        return noticeDao.getAllNoticeInThisBoard(boardId);
    }
}
