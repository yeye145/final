package service.impl;

import dao.*;
import dao.impl.*;
import pojo.Post;
import pojo.Report;
import pojo.User;
import service.ReportService;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    /*-------------------------------------------    私有变量    ---------------------------------------------*/
    private ReportDao reportDao = new ReportDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private PostDao postDao = new PostDaoImpl();
    private MessageDao messageDao = new MessageDaoImpl();


    /*-------------------------------------    获取我的版块的举报信息    ----------------------------------------*/
    @Override
    public List<Report> getReportPostToMe(Integer boardId) throws Exception {
        return reportDao.getReportPostToMe(boardId);
    }


    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    @Override
    public boolean reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception {

        User user = userDao.getUserById(reportedThisUserId);

        // 若找不到该用户
        if (user == null) {
            return false;
        }

        reportDao.reportUser(reportedThisUserId, userId, reason);

        return true;
    }


    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    @Override
    public boolean reportPost(Integer postId, Integer userId, String reason) throws Exception {

        Post thePostWhichBeReported = postDao.getThisPostById(postId);

        // 若找不到这条帖子，返回举报失败
        if (thePostWhichBeReported == null) {
            return false;
        }

        reportDao.reportPost(postId, thePostWhichBeReported.getBoardId(), userId, reason);
        return true;
    }


    /*-------------------------------------    版主和确认已处理对帖子的举报    ------------------------------------*/
    @Override
    public void dealThisReport(Integer reportId) throws Exception {
        Report report = reportDao.getReportById(reportId);
        messageDao.creatMessage("您对id为" + report.getPostId()
                        + "的帖子举报情况属实，版主已处理相关违规行为！感谢您为维护版块内容健康文明做出的贡献！"
                , report.getUserId(), null, "举报结果通知");
        reportDao.hadKnowReport(reportId);
    }


    /*--------------------------------------    版主拒绝处理对帖子的举报    --------------------------------------*/
    @Override
    public void refuseThisReport(Integer reportId) throws Exception {
        Report report = reportDao.getReportById(reportId);
        messageDao.creatMessage("您对id为" + report.getPostId()
                        + "的帖子举报情况不属实，证据不足，版主拒绝了对相关举报帖子的处理"
                , report.getUserId(), null, "举报结果通知");
        reportDao.hadKnowReport(reportId);
    }


    /*------------------------------------   管理员确认已处理对用户的举报    --------------------------------------*/
    @Override
    public void adminDealThisReport(Integer reportId) throws Exception {
        Report report = reportDao.getReportById(reportId);
        messageDao.creatMessage("您对id为" + report.getReportedThisUserId()
                        + "的用户举报情况属实，管理员已处理相关违规行为！"
                , report.getUserId(), null, "举报结果通知");
        userDao.banUser(report.getReportedThisUserId());
        reportDao.hadKnowReport(reportId);
    }


    /*-----------------------------------    管理员拒绝处理对用户的举报    ----------------------------------------*/
    @Override
    public void adminRefuseThisReport(Integer reportId) throws Exception {
        Report report = reportDao.getReportById(reportId);
        messageDao.creatMessage("您对id为" + report.getReportedThisUserId()
                        + "的用户举报情况不属实，证据不足，管理员拒绝了对被举报用户的处理"
                , report.getUserId(), null, "举报结果通知");
        reportDao.hadKnowReport(reportId);
    }


    /*--------------------------------    版主清空所有已处理的举报    -------------------------------------------*/
    @Override
    public void deleteAllDealBoardReport(Integer boardId) throws Exception {
        reportDao.deleteAllDealBoardReport(boardId);
    }


    /*-------------------------------    管理员清空所有已处理的举报    -------------------------------------------*/
    @Override
    public void deleteAllDealUserReport() throws Exception {
        reportDao.deleteAllDealUserReport();
    }


    /*-------------------------------------   管理员获取用户的举报信息     ---------------------------------------*/
    @Override
    public List<Report> getReportUserToAdmin() throws Exception {
        return reportDao.getReportUserToAdmin();
    }

}
