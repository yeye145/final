package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import dao.utils.MyUpdate;
import pojo.ResponseResult;
import service.ReportService;
import service.impl.ReportServiceImpl;
import utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/report/*")
public class ReportController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private ReportService reportService = new ReportServiceImpl();


    /*--------------------------------    清空所有已处理的举报    ---------------------------------------------*/
    public void deleteAllDealReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            System.out.println("ReportController.deleteAllDealReport，清空所有已处理的举报，版块id" + boardId);

            reportService.deleteAllDealReport(boardId);
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("清空所有已处理的举报")
                    )
            );
            System.out.println("-->清空所有已处理的举报成功！--" );
        } catch (Exception e) {
            System.err.println("ReportController.deleteAllDealReport发送错误：" + e.getMessage());
        }
    }



    /*------------------------------    获取我的版块下的举报帖子信息    ------------------------------------------*/
    public void getReportPostToMe(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            System.out.println("ReportController，获取我的版块下的举报信息，版块id" + boardId);

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(reportService.getReportPostToMe(boardId))
                    )
            );

            System.out.println("-->获取版块下的举报信息成功！--" );
        } catch (Exception e) {
            System.err.println("ReportController.getReportPostToMe发送错误：" + e.getMessage());
        }
    }


    /*-------------------------------------------    举报这个作者    ------------------------------------------*/
    public void reportUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);
        Integer reportedThisUserId = Integer.parseInt(request.getParameter("reportedThisUserId"));
        String reason = request.getParameter("reason");
        System.out.println("ReportController，reportUser,用户id" + userId + "举报的作者id：" + reportedThisUserId);

        if (reportService.reportUser(reportedThisUserId, userId, reason)) {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("举报成功！管理员会尽快处理")
                    )
            );

            System.out.println("-->举报成功！管理员会尽快处理-----------------------------------------");
        } else {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.error(Constants.RESPONSE_CODE_NOT_FOUND, "举报失败，用户不存在！")
                    )
            );

            System.out.println("-->举报失败，用户不存在-----------------------------------------");
        }

    }


    /*-------------------------------------------    举报这条帖子    ------------------------------------------*/
    public void reportPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        String reason = request.getParameter("reason");
        System.out.println("ReportController，reportPost，用户id" + userId + "举报的帖子id：" + postId);

        if (reportService.reportPost(postId, userId, reason)) {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("举报成功！版主会尽快处理")
                    )
            );

            System.out.println("-->举报成功！版主会尽快处理-----------------------------------------");
        } else {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.error(Constants.RESPONSE_CODE_NOT_FOUND, "举报失败，帖子不存在！")
                    )
            );

            System.out.println("-->举报失败，帖子不存在-----------------------------------------");
        }

    }


    /*-----------------------------------    举报属实，已确认处理    ------------------------------------------*/
    public void dealThisReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer reportId = Integer.parseInt(request.getParameter("reportId"));
        System.out.println("ReportController.dealThisReport，举报属实");

        // 给举报者发送处理完毕的通知
        reportService.dealThisReport(reportId);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("举报属实，已处理！")
                )
        );

        System.out.println("-->举报属实，已处理！--" );
    }



    /*-----------------------------------    举报不属实，拒绝处理    ------------------------------------------*/
    public void refuseThisReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer reportId = Integer.parseInt(request.getParameter("reportId"));
        System.out.println("ReportController.dealThisReport，举报不属实");

        // 给举报者发送处理完毕的通知
        reportService.refuseThisReport(reportId);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("举报不属实，拒绝处理！")
                )
        );

        System.out.println("-->举报不属实，拒绝处理！--" );
    }
}
