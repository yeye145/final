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
        return MySearch.searchToList("SELECT id, judge, reason" +
                ", user_id AS userId" +
                ", board_id AS boardId" +
                ", post_id AS postId" +
                ", reported_this_user_id AS reportedId" +
                " WHERE board_id = ? AND judge = 'host'", Report.class, boardId);
    }


}
