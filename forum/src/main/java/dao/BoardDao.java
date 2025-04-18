package dao;

import pojo.Board;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface BoardDao {


    List<Board> getOneBoardList(Integer userId) throws SQLException;


    Set<Board> getAllBoardSet() throws SQLException;
}
