package dao;

import pojo.BoardBan;

import java.sql.SQLException;
import java.util.Set;

public interface BoardBanDao {
    void banUserInThisBoard(Integer boardId, Integer userId, String reason) throws Exception;

    Set<BoardBan> getAllBanUserSet() throws SQLException;
}
