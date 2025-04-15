package controller;

import pojo.User;

import service.LoginService;

import service.impl.LoginServiceImpl;
import service.impl.RegisterServiceImpl;
import service.utils.CaptchaUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register/*")
public class RegisterController extends BaseServlet {

    private RegisterServiceImpl registerService = new RegisterServiceImpl();




    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        response.getWriter().write(registerService.register(phone, email, password));
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




