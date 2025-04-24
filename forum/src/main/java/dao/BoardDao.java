package dao;

import pojo.Board;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface BoardDao {


    List<Board> getAllBoardOrderById() throws SQLException;

    Board getBoardById(Integer boardId) throws SQLException;

    List<Board> getOneBoardList(Integer userId) throws SQLException;

    Set<Board> getAllBoardSet() throws SQLException;

    void plusOneViewCount(Integer boardId) throws Exception;

    List<Board> getAllBoardPrioritizeBy(String orderByName, String orderByWay) throws Exception;
}
