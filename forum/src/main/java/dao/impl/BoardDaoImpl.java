package dao.impl;

import dao.BoardDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
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
                ", view_count AS viewCount" +
                ", host_avatar AS hostAvatar" +
                ", host_name AS hostName " +
                "FROM `forum`.`board` " +
                "WHERE host_id = ?", Board.class, userId);
    }


    @Override
    public Set<Board> getAllBoardSet() throws SQLException {
        return MySearch.searchToSet("SELECT id, title, type, `time`" +
                ", host_id AS hostId" +
                ", post_count AS postCount " +
                ", view_count AS viewCount" +
                ", host_avatar AS hostAvatar" +
                ", host_name AS hostName " +
                "FROM `forum`.`board`", Board.class);
    }

    @Override
    public void plusOneViewCount(Integer boardId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`board`" +
                " SET view_count = view_count + 1" +
                " WHERE id = ?", boardId);
    }

    @Override
    public List<Board> getAllBoardPrioritizeBy(String orderByName, String orderByWay) throws Exception {
        return MySearch.searchToList(
                "SELECT id, title, type, `time`, " +
                        "host_id AS hostId, " +
                        "post_count AS postCount, " +
                        "view_count AS viewCount, " +
                        "host_avatar AS hostAvatar, " +
                        "host_name AS hostName " +
                        "FROM `forum`.`board` " +
                        "ORDER BY " + orderByName + " " + orderByWay, Board.class);
    }


}
