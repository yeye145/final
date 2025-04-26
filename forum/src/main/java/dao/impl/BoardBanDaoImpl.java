package dao.impl;

import dao.BoardBanDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.BoardBan;

import java.sql.SQLException;
import java.util.Set;


public class BoardBanDaoImpl implements BoardBanDao {

    /*---------------------------------    在这个版块中，封禁用户  --------------------------------------*/
    @Override
    public void banUserInThisBoard(Integer boardId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`board_ban`" +
                " (`ban_user_id`, `board_id`, `reason`) VALUES (?, ?, ?);", userId, boardId, reason);
    }


    /*---------------------------------    获取版块封禁用户的Set集合  -----------------------------------*/
    @Override
    public Set<BoardBan> getAllBanUserSet() throws SQLException {
        return MySearch.searchToSet("SELECT id, reason" +
                ", ban_user_id AS banId" +
                ", board_id AS boardId FROM `forum`.`board_ban`", BoardBan.class);
    }
}
