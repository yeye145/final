package dao;

import pojo.Board;
import pojo.BoardApply;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface BoardDao {

    /*---------------------------------------    创建新的版块   -----------------------------------------------*/
    void creatNewBoard(BoardApply apply) throws Exception;

    /*---------------------------------    获得所有版块List集合，按照id排序  -------------------------------------*/
    List<Board> getAllBoardOrderById() throws SQLException;

    /*-----------------------------------    获得某个版块，按照id排序   -----------------------------------------*/
    Board getBoardById(Integer boardId) throws SQLException;

    /*-------------------------------------   获得某个用户的所有版块   ------------------------------------------*/
    List<Board> getOneBoardList(Integer userId) throws SQLException;

    /*-------------------------------------   获得所有版块Set集合   --------------------------------------------*/
    Set<Board> getAllBoardSet() throws SQLException;

    /*-------------------------------------   该版块增加一个浏览数   --------------------------------------------*/
    void plusOneViewCount(Integer boardId) throws Exception;

    /*---------------------------------   获得所有版块，优先获得用户喜欢的   --------------------------------------*/
    List<Board> getAllBoardPrioritizeBy(String orderByName, String orderByWay) throws Exception;
}
