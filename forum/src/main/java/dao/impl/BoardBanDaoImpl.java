package dao.impl;

import dao.BoardBanDao;
import dao.utils.MyUpdate;


public class BoardBanDaoImpl implements BoardBanDao {

    @Override
    public void banUserInThisBoard(Integer boardId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`board_ban`" +
                " (`ban_user_id`, `board_id`, `reason`) VALUES (?, ?, ?);", userId, boardId, reason);
    }
}
