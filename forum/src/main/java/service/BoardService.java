package service;

import pojo.Board;
import pojo.BoardApply;
import pojo.Report;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    /*--------------------------------------    删除所有已处理的版块申请    -------------------------------------*/
    void deleteAllDealApply() throws Exception;

    /*------------------------------------    查看是否有新的版块申请    ------------------------------------------*/
    boolean checkIfNewBoardApply() throws Exception;

    /*------------------------------------    拒绝创建版块的申请    --------------------------------------------*/
    void refuseApplyNewBoard(Integer applyId) throws Exception;

    /*------------------------------------    拒绝创建版块的申请    --------------------------------------------*/
    void agreeApplyNewBoard(Integer applyId) throws Exception;

    /*------------------------------------    获取创建版块的申请    --------------------------------------------*/
    List<BoardApply> getAllApplyNewBoard() throws Exception;

    /*-----------------------------------------    取消关注版块    --------------------------------------------*/
    List<Board> getAllBoardOrderById() throws Exception;

    /*-----------------------------------------    取消关注版块    --------------------------------------------*/
    void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    /*------------------------------------    判断用户是否已经关注版块    ----------------------------------------*/
    boolean checkIfSubcribe(Integer boardId, Integer userId) throws Exception;

    /*-------------------------------------------    订阅版块    ----------------------------------------------*/
    void subscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    /*------------------------------------    获取我的版块，通过用户id    ----------------------------------------*/
    List<Board> getMyBoard(Integer userId) throws SQLException;

    /*-----------------------------------------    获取热门的版块    --------------------------------------------*/
    List<Board> getHotBoard() throws Exception;

    /*-----------------------------------------    获取最新的版块    --------------------------------------------*/
    List<Board> getNewBoard() throws Exception;

    /*----------------------------------    获取所有版块，优先显示用户关注的    -------------------------------------*/
    List<Board> getAllBoardPrioritizeUserLike(Integer userId) throws SQLException;

    /*-------------------------------------------    新增版块    -----------------------------------------------*/
    void applyNewBoard(Integer userId, String title, String type, String notice) throws Exception;

    /*--------------------------------------    版块封禁用户发帖    ----------------------------------------------*/
    boolean banUserInThisBoard(Integer boardId, String userName, String reason) throws SQLException;

    /*--------------------------------------    发布新的版块公告   -----------------------------------------------*/
    void publishNewNotice(Integer boardId, String content) throws Exception;
}
