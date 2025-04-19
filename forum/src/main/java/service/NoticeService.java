package service;

import pojo.Notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeService {
    List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException;
}
