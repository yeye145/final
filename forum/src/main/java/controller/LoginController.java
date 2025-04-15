package controller;

import pojo.User;
import service.LoginService;

import service.impl.LoginServiceImpl;

import service.utils.CaptchaUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login/*")
public class LoginController extends BaseServlet {

    private LoginService loginService = new LoginServiceImpl();


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
            response.getWriter().write("{\"code\":200, \"message\":\"" +
                    (user.getIsAdmin() ? "admin" : "student") + "\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\":401, \"error\":\"账号或密码错误\"}");
        }

    }


    public void exitLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
    }



    // 生成验证码图片
    public void getHorse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成验证码
        CaptchaUtil.CaptchaResult result = CaptchaUtil.generateCaptcha(100, 40);

        // 将验证码文本存入Session
        request.getSession().setAttribute("verifyCode", result.getCode());

        // 返回图片
        response.setContentType("image/jpeg");
        response.getOutputStream().write(result.getImageBytes());
    }


    // 验证用户输入的验证码
    public void checkHorse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String inputHorse = request.getParameter("inputHorse");
        String realCode = (String) request.getSession().getAttribute("verifyCode");

        boolean isValid = CaptchaUtil.validate(inputHorse, realCode);
        if (isValid) {
            response.getWriter().write("{\"success\":true, \"message\":\"验证成功\"}");
        } else {
            response.getWriter().write("{\"success\":false, \"message\":\"验证失败\"}");
        }
    }

}




