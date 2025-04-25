package dao.impl;

import dao.ReportDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Report;

import java.util.List;

public class ReportDaoImpl implements ReportDao {

    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    @Override
    public void reportPost(Integer postId, Integer boardId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`report`" +
                " (`user_id`, `board_id`, `post_id`,`judge`, `reason`)" +
                " VALUES (?, ?, ?, ?, ?)", userId, boardId, postId, "host", reason);
    }


    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    @Override
    public void reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`report`" +
                " (`user_id`, `reported_this_user_id`, `judge`, `reason`)" +
                " VALUES (?, ?, ?, ?)", userId, reportedThisUserId, "admin", reason);
    }


    /*-----------------------------------    获取版块下所有举报帖子的信息    --------------------------------------*/
    @Override
    public List<Report> getReportPostToMe(Integer boardId) throws Exception {
        return MySearch.searchToList("SELECT id, judge, reason, time" +
                ", if_deal AS ifDeal" +
                ", user_id AS userId" +
                ", board_id AS boardId" +
                ", post_id AS postId" +
                ", reported_this_user_id AS reportedThisUserId" +
                " FROM `forum`.`report` WHERE board_id = ? AND judge = 'host'", Report.class, boardId);
    }


    /*-----------------------------------     通过id获取对应的举报内容    --------------------------------------*/
    @Override
    public Report getReportById(Integer reportId) throws Exception{
        return MySearch.searchToOne("SELECT id, judge, reason, time" +
                ", if_deal AS ifDeal" +
                ", user_id AS userId" +
                ", board_id AS boardId" +
                ", post_id AS postId" +
                ", reported_this_user_id AS reportedThisUserId" +
                " FROM `forum`.`report` WHERE id = ?", Report.class, reportId);
    }


    /*-----------------------------------------     举报已读    --------------------------------------------*/
    @Override
    public void hadKnowReport(Integer reportId) throws Exception{
        MyUpdate.update("UPDATE `forum`.`report` SET if_deal = 1 WHERE id = ?", reportId);
    }


    /*--------------------------------    版主清空所有已处理的举报    -----------------------------------------*/
    @Override
    public void deleteAllDealBoardReport(Integer boardId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`report`" +
                " WHERE board_id = ? AND if_deal = 1", boardId);
    }


    /*--------------------------------    管理员清空所有已处理的举报    ----------------------------------------*/
    @Override
    public void deleteAllDealUserReport() throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`report`" +
                " WHERE judge = 'admin' AND if_deal = 1");
    }



    /*-----------------------------------    获取版块下所有举报帖子的信息    --------------------------------------*/
    @Override
    public List<Report> getReportUserToAdmin() throws Exception {
        return MySearch.searchToList("SELECT id, judge, reason, time" +
                ", if_deal AS ifDeal" +
                ", user_id AS userId" +
                ", board_id AS boardId" +
                ", post_id AS postId" +
                ", reported_this_user_id AS reportedThisUserId" +
                " FROM `forum`.`report` WHERE judge = 'admin'", Report.class);
    }


}
