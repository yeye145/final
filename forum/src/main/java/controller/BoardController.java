package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.Board;
import pojo.Notice;
import pojo.ResponseResult;
import service.BoardService;
import service.NoticeService;
import service.ReportService;
import service.impl.BoardServiceImpl;
import service.impl.NoticeServiceImpl;
import service.impl.ReportServiceImpl;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/board/*")
public class BoardController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private BoardService boardService = new BoardServiceImpl();
    private NoticeService noticeService = new NoticeServiceImpl();


    /*---------------------------------    判断某个版块，用户是否已经关注   ---------------------------------------*/
    public void checkIfHadSubscribeThisBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        Integer userId = ControllerToolMethod.getUserId(request);
        System.out.println("BoardController.checkIfHadSubscribeThisBoard" +
                "，判断版块是否收藏，版块id" + boardId + "，用户id：" + userId);

        if (boardService.checkIfSubcribe(boardId, userId)) {
            // 返回已经收藏响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("yes")
                    )
            );
            System.out.println("-->已经关注该版块");
        } else {
            // 返回已经收藏响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("no")
                    )
            );
            System.out.println("-->还没有关注该版块");
        }


    }


    /*-----------------------------------------    取消关注这个版块    -----------------------------------------*/
    public void cancelSubscribeThisBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

       Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        Integer userId = ControllerToolMethod.getUserId(request);

        System.out.println("BoardController，cancelSubscribeThisBoard，取消关注版块id:" + boardId + ",用户id:" + userId);
        boardService.cancelSubscribeThisBoard(boardId,userId);


        // 返回成功响应
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("取消关注版块成功！")
                )
        );

        System.out.println("-->取消关注版块成功！");

    }


    /*-------------------------------------------    关注这个版块    ------------------------------------------*/
    public void subscribeThisBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        System.out.println("BoardController，关注版块，用户id" + userId + "关注的版块id：" + boardId);

        boardService.subscribeThisBoard(boardId, userId);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("关注成功！")
                )
        );

        System.out.println("-关注版块成功！--");
    }


    /*-------------------------------------------    获取我的版块    ------------------------------------------*/
    public void getMyBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Integer userId = ControllerToolMethod.getUserId(request);
        System.out.println("BoardController，获取我的版块，用户id" + userId);

        List<Board> targetBoard = boardService.getMyBoard(userId);

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(targetBoard)
                )
        );

        System.out.println("-获取版块成功！--" + targetBoard);
    }


    /*------------------------------------    获取全部版块，优先渲染他喜欢的   -----------------------------------*/
    public void getAllBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Integer userId = ControllerToolMethod.getUserId(request);
        System.out.println("BoardController，获取全部版块，用户id" + userId);

        List<Board> allBoard = boardService.getAllBoardPrioritizeUserLike(userId);

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(allBoard)
                )
        );

        System.out.println("-获取全部版块成功！");
    }


    /*-------------------------------------------    获取热门版块   ------------------------------------------*/
    public void getHotBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("BoardController，获取热门版块");

        List<Board> hotBoard = boardService.getHotBoard();

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(hotBoard)
                )
        );

        System.out.println("-获取热门版块成功！");
    }


    /*-------------------------------------------    获取最新版块   ------------------------------------------*/
    public void getNewBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("BoardController，获取最新版块");

        List<Board> newBoard = boardService.getNewBoard();

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(newBoard)
                )
        );

        System.out.println("-获取最新版块成功！");
    }


    /*-------------------------------------------    获取全部公告    ------------------------------------------*/
    public void getNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        System.out.println("BoardController，获取全部公告版块，版块id" + boardId);

        List<Notice> allNoticeInThisBoard = noticeService.getAllNoticeInThisBoard(boardId);
        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(allNoticeInThisBoard)
                )
        );

        System.out.println("-获取全部公告成功！");
    }


    /*-------------------------------------------    申请新的版块    ------------------------------------------*/
    public void applyNewBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);
        System.out.println("BoardController，申请版块，用户id" + userId);
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String notice = request.getParameter("notice");
        System.out.println("-版块标题：" + title + "，版块类型：" + type + "\n-版块公告：" + notice);

        boardService.applyNewBoard(userId, title, type, notice);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("申请成功！请等待管理员审核")
                )
        );

        System.out.println("-->申请成功！等待审核");

    }


    /*-------------------------------------------     版块封禁用户    -----------------------------------------*/
    public void banUserInThisBoard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("BoardController，版块封禁用户，版主id：" + ControllerToolMethod.getUserId(request));
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        String reason = request.getParameter("reason");
        String userName = request.getParameter("userName");
        System.out.println("-->封禁用户：" + userName + ",版块id：" + boardId + ",封禁原因：\n" + reason);

        if (boardService.banUserInThisBoard(boardId, userName, reason)) {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("封禁用户发帖成功")
                    )
            );
        } else {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.error(Constants.RESPONSE_CODE_NOT_FOUND, "用户名不存在")
                    )
            );
        }
    }


    /*-----------------------------------------    发布新的版块公告    ------------------------------------------*/
    public void publishNewNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        String content = request.getParameter("content");
        System.out.println("BoardController，发布版块公告，版块id" + boardId);

        boardService.publishNewNotice(boardId, content);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("发布成功")
                )
        );

        System.out.println("-->公告发布成功！内容：\n" + content
                + "\n--------------------------------------------------------------------------------");

    }

}
