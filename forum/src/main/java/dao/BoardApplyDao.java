package dao;

import pojo.BoardApply;

import java.util.List;

public interface BoardApplyDao {

    /*-----------------------------------    已经读取这个版块的申请    ---------------------------------------------*/
    void hadReadThisApply(Integer applyId) throws Exception;

    /*------------------------------------    申请创造一个新的版块    ---------------------------------------------*/
    void applyNewBoard(Integer hostId, String title, String type, String notice) throws Exception;

    /*------------------------------------    获取所有版块申请请求    ---------------------------------------------*/
    List<BoardApply> getAllApplyNewBoard() throws Exception;

    /*------------------------------------    清空所有已处理的请求    ---------------------------------------------*/
    void deleteAllDealApply() throws Exception;

    /*------------------------------------    获得某个版块的申请信息    --------------------------------------------*/
    BoardApply getBoardApplyById(Integer applyId) throws Exception;
}
