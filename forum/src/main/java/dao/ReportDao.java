package dao;

import pojo.Report;

import java.util.List;

public interface ReportDao {
    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    void reportPost(Integer postId, Integer boardId, Integer userId, String reason) throws Exception;

    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    void reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception;

    /*-----------------------------------    获取版块下所有举报帖子的信息    --------------------------------------*/
    List<Report> getReportPostToMe(Integer boardId) throws Exception;

    /*-----------------------------------     通过id获取对应的举报内容    --------------------------------------*/
    Report getReportById(Integer reportId) throws Exception;

    /*-----------------------------------------     举报已读    --------------------------------------------*/
    void hadKnowReport(Integer reportId) throws Exception;
}
