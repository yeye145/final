package dao.impl;

import dao.BoardApplyDao;
import dao.utils.MyUpdate;

public class BoardApplyDaoImpl implements BoardApplyDao {

    @Override
    public void applyNewBoard(Integer hostId, String title, String type, String notice) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`board_apply`" +
                " (`host_id`, `title`, `type`, `notice`) VALUES (?, ?, ?, ?);", hostId, title, type, notice);
    }
}
