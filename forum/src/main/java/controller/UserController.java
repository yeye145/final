package controller;

import com.alibaba.fastjson.JSON;
import pojo.ResponseResult;
import pojo.User;

import service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/user/*")
public class UserController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UserServiceImpl userService = new UserServiceImpl();


    /*--------------------------------------------    获取头像    --------------------------------------------*/
    public void avatar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("UserController.avatar,获取头像：");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            System.out.println("-用户id：" + user.getId());
            // 获取头像的url
            String avatarUrl = userService.getAvatar(user.getId());
            System.out.println("-avatarUrl: " + avatarUrl);
            // 返回 ResponseResult 对象
            String json = JSON.toJSONString(ResponseResult.success(avatarUrl));
            response.getWriter().write(json);
        } else {
            String json = JSON.toJSONString(ResponseResult.error(401, "未登录"));
            response.getWriter().write(json);
        }
    }
}
