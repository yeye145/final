package service;

import pojo.Report;

import java.util.List;

public interface ReportService {
    /*-----------------------------------    获取我的版块的举报信息    ------------------------------------------*/
    List<Report> getReportPostToMe(Integer boardId) throws Exception;

    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    boolean reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception;

    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    boolean reportPost(Integer postId, Integer userId, String reason) throws Exception;

    /*-------------------------------------    确认已处理对帖子的举报    -----------------------------------------*/
    void dealThisReport(Integer reportId) throws Exception;

    /*--------------------------------------    拒绝处理对帖子的举报    -----------------------------------------*/
    void refuseThisReport(Integer reportId) throws Exception;
}
