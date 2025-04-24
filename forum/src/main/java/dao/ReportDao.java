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
}
