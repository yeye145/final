package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.User;
import pojo.ResponseResult;
import service.LoginService;

import service.impl.LoginServiceImpl;

import utils.Constants;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login/*")
public class LoginController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private LoginService loginService = new LoginServiceImpl();


    /*--------------------------------------------    是否登录    --------------------------------------------*/
    public void userCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("LoginController.userCheck，验证是否登录");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 返回登录失败信息
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_FORBIDDEN, "未登录"));
            response.getWriter().write(json);
        } else {
            response.getWriter().write(JSON.toJSONString(ResponseResult.success("已登录")));
        }

    }


    /*------------------------------------------    管理员权限确认    -----------------------------------------*/
    public void adminCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("LoginController.adminCheck，验证是否为管理员");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getIsAdmin()) {
            // 返回登录失败信息
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_FORBIDDEN, "权限不足"));
            response.getWriter().write(json);
        } else {
            response.getWriter().write(JSON.toJSONString(ResponseResult.success("权限正常")));
        }

    }


    /*--------------------------------------------    登录验证    --------------------------------------------*/
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        System.out.println("LoginController的login方法，账号为：" + account + " 的用户正在登录，密码输入为：" + password);


        // 后端正则验证
        if (!ControllerToolMethod.accountRegexCheck(account)) {
            String json = JSON.toJSONString(
                    ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "手机号或邮箱格式无效")
            );
            response.getWriter().write(json);
        }


        User user = loginService.loginCheck(account, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if (user != null && !user.getIfBanLogin()) {
            System.out.println("用户：" + user.getName() + ",登录成功！" +
                    "\n_____________________________________________________________");
            // 返回登录成功信息
            String json = JSON.toJSONString(ResponseResult.success(user.getIsAdmin() ? "admin" : "user"));
            response.getWriter().write(json);
        }
        if (user != null && user.getIfBanLogin()) {
            // 返回登录失败信息
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_FORBIDDEN, "\n账号被封禁\n"));
            response.getWriter().write(json);
        }
        if (user == null) {
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




