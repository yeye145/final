package dao.impl;

import dao.BoardDao;
import dao.utils.MySearch;
import pojo.Board;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class BoardDaoImpl implements BoardDao {

    @Override
    public List<Board> getOneBoardList(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id, title, type, `time`" +
                ", host_id AS hostId" +
                ", post_count AS postCount" +
                ", host_name AS hostName " +
                "FROM `forum`.`board` " +
                "WHERE host_id = ?", Board.class, userId);
    }


    @Override
    public Set<Board> getAllBoardSet() throws SQLException {
        return MySearch.searchToSet("SELECT id, title, type, `time`" +
                ", host_id AS hostId" +
                ", post_count AS postCount " +
                ", host_name AS hostName " +
                "FROM `forum`.`board`", Board.class);
    }

}
