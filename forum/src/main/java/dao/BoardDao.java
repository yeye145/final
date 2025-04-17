package dao;

import pojo.Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardDao {

    List<Board> getBoardList(Integer userId) throws SQLException;
}
