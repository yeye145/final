package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import pojo.ResponseResult;
import service.impl.ForgetPasswordServiceImpl;
import service.utils.HashSaltUtil;
import utils.Constants;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forget/*")
public class ForgetPasswordController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private ForgetPasswordServiceImpl forgetPasswordService = new ForgetPasswordServiceImpl();



    /*--------------------------------------------    修改密码    --------------------------------------------*/
    public void forget(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        String account = request.getParameter("account");
        String password = HashSaltUtil.creatHashPassword(request.getParameter("password"));

        if (forgetPasswordService.forgetPassword(account, password)){
            System.out.println("ForgetPasswordController.forget，修改密码成功");
            response.getWriter().write(JSON.toJSONString(ResponseResult.success("修改密码")));
        } else {
            System.out.println("ForgetPasswordController.forget，修改密码失败");
            response.getWriter().write(JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "修改失败")));
        }

    }


}




