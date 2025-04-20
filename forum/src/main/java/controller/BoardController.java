package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.Board;
import pojo.Notice;
import pojo.ResponseResult;
import service.BoardService;
import service.NoticeService;
import service.impl.BoardServiceImpl;
import service.impl.NoticeServiceImpl;

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
    public void applyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController，申请版块，用户id" + ControllerToolMethod.getUserId(request));
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String notice = request.getParameter("notice");
        System.out.println("-版块标题：" + title + "，版块类型：" + type + "\n-版块公告：" + notice);
    }


    /*-------------------------------------------     版块封禁用户    -----------------------------------------*/
    public void banUserInThisBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController，版块封禁用户，版主id：" + ControllerToolMethod.getUserId(request));
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        String reason = request.getParameter("reason");
        String userName = request.getParameter("userName");
        System.out.println("封禁用户：" + userName + ",版块id：" + boardId + ",封禁原因：\n" + reason);
    }


}
