package controller;

import com.alibaba.fastjson.JSON;
import pojo.User;
import pojo.ResponseResult;
import service.LoginService;

import service.impl.LoginServiceImpl;

import service.utils.CaptchaUtil;
import utils.Constants;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login/*")
public class LoginController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private LoginService loginService = new LoginServiceImpl();



    /*--------------------------------------------    登录验证    --------------------------------------------*/
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // 获取参数
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        System.out.println("LoginController的login方法，账号为：" + account + " 的用户正在登录，密码输入为：" + password);

        User user = loginService.loginCheck(account, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if (user != null) {
            System.out.println("用户：" + user.getName() + ",登录成功！");
            // 返回登录成功信息
            String json = JSON.toJSONString(ResponseResult.success(user.getIsAdmin() ? "admin" : "user"));
            response.getWriter().write(json);
        } else {
            // 返回登录失败信息
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "账号或密码错误"));
            response.getWriter().write(json);
        }

    }



    /*--------------------------------------------    退出登录    --------------------------------------------*/
    public void exitLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
    }



}




