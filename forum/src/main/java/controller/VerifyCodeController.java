package controller;


import service.utils.CaptchaUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/verify/*")
public class VerifyCodeController extends BaseServlet {


    /*--------------------------------------------  生成验证码图片  --------------------------------------------*/
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成验证码
        CaptchaUtil.CaptchaResult result = CaptchaUtil.generateCaptcha(100, 40);

        // 将验证码文本存入Session
        request.getSession().setAttribute("verifyCode", result.getCode());

        // 返回图片
        response.setContentType("image/jpeg");
        response.getOutputStream().write(result.getImageBytes());
    }



    /*------------------------------------------- 验证用户输入的验证码 -------------------------------------------*/
    public void checkVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
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




