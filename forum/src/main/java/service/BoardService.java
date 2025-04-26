package service;

import pojo.Board;
import pojo.BoardApply;
import pojo.Notice;
import pojo.Report;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    /*------------------------------    获取该版块下的历史公告    ---------------------------------------*/
    List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException;

    /*------------------------------    删除所有已处理的版块申请    -------------------------------------*/
    void deleteAllDealApply() throws Exception;

    /*------------------------------    检查是否存在未处理的版块申请    ---------------------------------*/
    boolean checkIfNewBoardApply() throws Exception;

    /*------------------------------    拒绝版块创建申请（通过申请ID）    --------------------------------*/
    void refuseApplyNewBoard(Integer applyId) throws Exception;

    /*------------------------------    同意版块创建申请（通过申请ID）    --------------------------------*/
    void agreeApplyNewBoard(Integer applyId) throws Exception;

    /*------------------------------    获取所有版块创建申请列表    ------------------------------------*/
    List<BoardApply> getAllApplyNewBoard() throws Exception;

    /*------------------------------    获取所有版块并按ID排序    -------------------------------------*/
    List<Board> getAllBoardOrderById() throws Exception;

    /*------------------------------    取消用户对指定版块的关注    ------------------------------------*/
    void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    /*------------------------------    检查用户是否已关注指定版块    ----------------------------------*/
    boolean checkIfSubcribe(Integer boardId, Integer userId) throws Exception;

    /*------------------------------    用户订阅指定版块    -------------------------------------------*/
    void subscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    /*------------------------------    获取用户管理的版块列表（通过用户ID）    --------------------------*/
    List<Board> getMyBoard(Integer userId) throws SQLException;

    /*------------------------------    获取热门版块列表（按浏览量降序）    ------------------------------*/
    List<Board> getHotBoard() throws Exception;

    /*------------------------------    获取最新版块列表（按创建时间降序）    ----------------------------*/
    List<Board> getNewBoard() throws Exception;

    /*------------------------------    获取推荐版块列表（优先用户关注）    -----------------------------*/
    List<Board> getAllBoardPrioritizeUserLike(Integer userId) throws SQLException;

    /*------------------------------    提交新版块创建申请    -----------------------------------------*/
    void applyNewBoard(Integer userId, String title, String type, String notice) throws Exception;

    /*------------------------------    封禁用户在指定版块发帖    -------------------------------------*/
    boolean banUserInThisBoard(Integer boardId, String userName, String reason) throws Exception;

    /*------------------------------    发布新版块公告    --------------------------------------------*/
    void publishNewNotice(Integer boardId, String content) throws Exception;
}