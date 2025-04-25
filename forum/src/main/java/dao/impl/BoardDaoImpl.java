package dao.impl;

import dao.BoardDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Board;
import pojo.BoardApply;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class BoardDaoImpl implements BoardDao {


    /*---------------------------------------    创建新的版块   -----------------------------------------------*/
    @Override
    public void creatNewBoard(BoardApply apply) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`board`" +
                        " (`host_id`, `title`, `type`, `host_name`, `host_avatar`)" +
                        " VALUES (?, ?, ?, ?, ?);", apply.getHostId(), apply.getTitle()
                , apply.getType(), apply.getHostName(), apply.getHostAvatar());
    }


    /*---------------------------------    获得所有版块List集合，按照id排序  -------------------------------------*/
    @Override
    public List<Board> getAllBoardOrderById() throws SQLException {
        return MySearch.searchToList("SELECT id, title, type, `time`" +
                ", host_id AS hostId" +
                ", post_count AS postCount " +
                ", view_count AS viewCount" +
                ", host_avatar AS hostAvatar" +
                ", host_name AS hostName " +
                "FROM `forum`.`board`", Board.class);
    }

    /*-----------------------------------    获得某个版块，按照id排序   -----------------------------------------*/
    @Override
    public Board getBoardById(Integer boardId) throws SQLException {
        return MySearch.searchToOne("SELECT id, title, type, `time`" +
                ", host_id AS hostId" +
                ", post_count AS postCount " +
                ", view_count AS viewCount" +
                ", host_avatar AS hostAvatar" +
                ", host_name AS hostName " +
                "FROM `forum`.`board` WHERE id = ?", Board.class, boardId);
    }


    /*-------------------------------------    获得某个用户的所有版块   -----------------------------------------*/
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


    /*-------------------------------------   获得所有版块Set集合   --------------------------------------------*/
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

    /*-------------------------------------   该版块增加一个浏览数   --------------------------------------------*/
    @Override
    public void plusOneViewCount(Integer boardId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`board`" +
                " SET view_count = view_count + 1" +
                " WHERE id = ?", boardId);
    }


    /*---------------------------------   获得所有版块，优先获得用户喜欢的   --------------------------------------*/
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
