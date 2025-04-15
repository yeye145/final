package controller;

import com.alibaba.fastjson.JSON;
import pojo.ResponseResult;
import service.impl.ForgetPasswordServiceImpl;
import utils.Constants;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forget/*")
public class ForgetPasswordController extends BaseServlet {

    private ForgetPasswordServiceImpl forgetPasswordService = new ForgetPasswordServiceImpl();


    public void forget(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        if (forgetPasswordService.forgetPassword(account, password)){
            System.out.println("ForgetPasswordController.forget，修改密码成功");
            response.getWriter().write(JSON.toJSONString(ResponseResult.success("修改密码")));
        } else {
            System.out.println("ForgetPasswordController.forget，修改密码失败");
            response.getWriter().write(JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "修改失败")));
        }

    }


}




