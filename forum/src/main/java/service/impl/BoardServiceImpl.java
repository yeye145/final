package service.impl;

import dao.BoardDao;
import dao.impl.BoardDaoImpl;
import pojo.Board;
import service.BoardService;

import java.sql.SQLException;
import java.util.List;

public class BoardServiceImpl implements BoardService {


    private BoardDao boardDao = new BoardDaoImpl();

    @Override
    public List<Board> getMyBoard(Integer useId) throws SQLException {
        return boardDao.getBoardList(useId);
    }
}
