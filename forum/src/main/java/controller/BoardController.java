package controller;

import controller.utils.GetUserId;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/*")
public class BoardController extends BaseServlet{

    public void getBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController，获取我的版块，用户id" + GetUserId.getUserId(request));
    }

    public void applyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController，申请版块，用户id" + GetUserId.getUserId(request));

        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String notice = request.getParameter("notice");
        System.out.println("-版块标题：" + title + "，版块类型：" + type + "\n-版块公告：" + notice);
    }
}
