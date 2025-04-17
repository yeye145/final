package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.GetUserId;
import pojo.Board;
import pojo.ResponseResult;
import service.BoardService;
import service.impl.BoardServiceImpl;

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

    public void getBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Integer userId = GetUserId.getUserId(request);
        System.out.println("BoardController，获取我的版块，用户id" + userId);

        List<Board> targetBoard = boardService.getMyBoard(userId);

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(targetBoard)
                )
        );

        System.out.println("-获取版块成功！--"+targetBoard);
    }

    public void applyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController，申请版块，用户id" + GetUserId.getUserId(request));
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String notice = request.getParameter("notice");
        System.out.println("-版块标题：" + title + "，版块类型：" + type + "\n-版块公告：" + notice);
    }
}
