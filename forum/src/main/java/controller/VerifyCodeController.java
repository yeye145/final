package controller;


import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.ResponseResult;
import service.utils.CaptchaUtil;
import utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/verify/*")
public class VerifyCodeController extends BaseServlet {


    /*--------------------------------------------  生成验证码图片  --------------------------------------------*/
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 生成验证码
            CaptchaUtil.CaptchaResult result = CaptchaUtil.generateCaptcha(100, 40);

            String verifyCode = result.getCode();

            // 将验证码文本存入Session
            request.getSession().setAttribute("verifyCode", verifyCode);

            System.out.println("VerifyCodeController类，生成验证码：" + verifyCode);

            // 返回图片
            response.setContentType("image/jpeg");
            response.getOutputStream().write(result.getImageBytes());
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "生成验证码图片时出错");
        }
    }



    /*------------------------------------------- 验证用户输入的验证码 -------------------------------------------*/
    public void checkVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            String verifyCode = request.getParameter("verifyCode");
            String realCode = (String) request.getSession().getAttribute("verifyCode");

            boolean isValid = CaptchaUtil.validate(verifyCode, realCode);
            if (isValid) {
                String json = JSON.toJSONString(ResponseResult.success("验证码验证成功"));
                response.getWriter().write(json);
            } else {
                String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "验证码验证失败"));
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "验证用户输入的验证码时出错");
        }
    }

}




