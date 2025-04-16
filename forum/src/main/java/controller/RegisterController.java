package controller;

import com.alibaba.fastjson.JSON;
import pojo.ResponseResult;
import service.impl.RegisterServiceImpl;
import service.utils.HashSaltUtil;
import utils.Constants;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register/*")
public class RegisterController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private RegisterServiceImpl registerService = new RegisterServiceImpl();


    /*--------------------------------------------    注册验证    --------------------------------------------*/
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashPassword = HashSaltUtil.creatHashPassword(password);


        if (registerService.register(phone, email, hashPassword)) {
            System.out.println("RegisterController.register,注册成功！手机号：" + phone);
            String json = JSON.toJSONString(ResponseResult.success("手机号：" + phone + "，注册成功"));
            response.getWriter().write(json);
        } else {
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "注册失败"));
            response.getWriter().write(json);
        }
    }


}




