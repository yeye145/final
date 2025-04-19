package service;

import pojo.Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    List<Board> getMyBoard(Integer userId) throws SQLException;

    List<Board> getHotBoard() throws Exception;

    List<Board> getNewBoard() throws Exception;

    List<Board> getAllBoardPrioritizeUserLike(Integer userId) throws SQLException;
}
