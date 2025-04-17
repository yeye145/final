package dao.impl;

import dao.BoardDao;
import dao.utils.MySearch;
import pojo.Board;

import java.sql.SQLException;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

    @Override
    public List<Board> getBoardList(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id, title, type, `time`" +
                ", host_id AS hostId" +
                ", post_count AS postCount " +
                "FROM `forum`.`board` " +
                "WHERE host_id = ?", Board.class, userId);
    }
}
