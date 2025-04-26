package dao;

import pojo.BoardBan;

import java.sql.SQLException;
import java.util.Set;

public interface BoardBanDao {

    /*---------------------------------    在这个版块中，封禁用户  --------------------------------------*/
    void banUserInThisBoard(Integer boardId, Integer userId, String reason) throws Exception;

    /*---------------------------------    获取版块封禁用户的Set集合  -----------------------------------*/
    Set<BoardBan> getAllBanUserSet() throws SQLException;

}
