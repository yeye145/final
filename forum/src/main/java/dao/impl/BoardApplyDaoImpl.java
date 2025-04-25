package dao.impl;

import dao.BoardApplyDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.BoardApply;

import java.util.List;

public class BoardApplyDaoImpl implements BoardApplyDao {

    /*-----------------------------------    已经读取这个版块的申请    ---------------------------------------------*/
    @Override
    public void hadReadThisApply(Integer applyId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`board_apply` SET if_deal = 1 WHERE id = ?", applyId);
    }


    /*------------------------------------    申请创造一个新的版块    ---------------------------------------------*/
    @Override
    public void applyNewBoard(Integer hostId, String title, String type, String notice) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`board_apply`" +
                " (`host_id`, `title`, `type`, `notice`) VALUES (?, ?, ?, ?);", hostId, title, type, notice);
    }


    /*------------------------------------    获取所有版块申请请求    ---------------------------------------------*/
    @Override
    public List<BoardApply> getAllApplyNewBoard() throws Exception {
        return MySearch.searchToList("SELECT id, time, title, type, notice" +
                ", host_id AS hostId, if_deal AS ifDeal FROM `forum`.`board_apply` ORDER BY time DESC", BoardApply.class);
    }


    /*------------------------------------    清空所有已处理的请求    ---------------------------------------------*/
    @Override
    public void deleteAllDealApply() throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`board_apply` WHERE if_deal = 1");
    }


    /*------------------------------------    获得某个版块的申请信息    --------------------------------------------*/
    @Override
    public BoardApply getBoardApplyById(Integer applyId) throws Exception {
        return MySearch.searchToOne("SELECT id, time, title, type, notice" +
                ", host_id AS hostId" +
                ", if_deal AS ifDeal" +
                " FROM `forum`.`board_apply` WHERE id = ? ", BoardApply.class, applyId);
    }


}
