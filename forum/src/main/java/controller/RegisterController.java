package controller;

import service.impl.RegisterServiceImpl;


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
        response.getWriter().write(registerService.register(phone, email, password));
    }



}




