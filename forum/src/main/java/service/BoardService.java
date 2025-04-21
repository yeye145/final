package service;

import pojo.Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    /*-------------------------------------------    订阅版块    ----------------------------------------------*/
    void subscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    List<Board> getMyBoard(Integer userId) throws SQLException;

    List<Board> getHotBoard() throws Exception;

    List<Board> getNewBoard() throws Exception;

    List<Board> getAllBoardPrioritizeUserLike(Integer userId) throws SQLException;

    /*-------------------------------------------    新增版块    -----------------------------------------------*/
    void applyNewBoard(Integer userId, String title, String type, String notice) throws Exception;

    /*--------------------------------------    版块封禁用户发帖    ----------------------------------------------*/
    boolean banUserInThisBoard(Integer boardId, String userName, String reason) throws SQLException;

    /*--------------------------------------    发布新的版块公告   -----------------------------------------------*/
    void publishNewNotice(Integer boardId, String content) throws Exception;
}
