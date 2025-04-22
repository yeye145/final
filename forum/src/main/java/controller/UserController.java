package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.ResponseResult;
import pojo.User;

import service.impl.UpdateServiceImpl;
import service.impl.UserServiceImpl;
import utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/*")
public class UserController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UserServiceImpl userService = new UserServiceImpl();
    private UpdateServiceImpl updateService = new UpdateServiceImpl();


    /*-------------------------------------------    关注这个作者    ------------------------------------------*/
    public void subscribeThisUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);
        Integer authorId = Integer.parseInt(request.getParameter("authorId"));
        System.out.println("UserController，用户id" + userId + "关注的作者id：" + authorId);

        userService.subscribeThisUser(authorId, userId);
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("关注作者成功！")
                )
        );

        System.out.println("-关注作者成功！--");
    }


    /*-----------------------------------------    取消关注这个用户    -----------------------------------------*/
    public void cancelSubscribeThisUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer userId = ControllerToolMethod.getUserId(request);
        Integer authorId = Integer.parseInt(request.getParameter("authorId"));

        System.out.println("UserController，cancelSubscribeThisUser，取消关注作者id:" + authorId + ",用户id:" + userId);
        userService.cancelSubscribeThisUser(authorId, userId);

        // 返回成功响应
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("取消关注用户成功！")
                )
        );

        System.out.println("-->取消关注用户成功！");
    }


    /*--------------------------------------    判断用户是否关注了    ------------------------------------------*/
    public void checkIfHadSubscribeThisUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer authorId = Integer.parseInt(request.getParameter("authorId"));
        Integer userId = ControllerToolMethod.getUserId(request);
        System.out.println("UserController.checkIfHadSubscribeThisUser" +
                "，判断用户是否订阅作者id" + authorId + "，用户id：" + userId);

        if (userService.checkIfSubcribe(authorId, userId)) {
            // 返回已经收藏响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("yes")
                    )
            );
            System.out.println("-->已经关注该用户");
        } else {
            // 返回已经收藏响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("no")
                    )
            );
            System.out.println("-->还没有关注该用户");
        }

    }


    /*--------------------------------------------    获取头像    --------------------------------------------*/
    public void avatar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("UserController.avatar，获取头像：");

        Integer userId = ControllerToolMethod.getUserId(request);

        if (userId != null) {
            System.out.println("-用户id：" + userId);
            // 获取头像的url
            String avatarUrl = userService.getAvatar(userId);
            System.out.println("-avatarUrl: " + avatarUrl);
            // 返回 ResponseResult 对象
            response.getWriter().write(JSON.toJSONString(ResponseResult.success(avatarUrl)));
        } else {
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "未登录"));
            response.getWriter().write(json);
        }
    }


    /*--------------------------------------------   获取个人信息   --------------------------------------------*/
    public void information(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("UserController.information，获取个人信息");
        Integer userId = ControllerToolMethod.getUserId(request);
        if (userId != null) {
            System.out.println("-用户id：" + userId);

            // 获取个人信息
            User targetUser = userService.getInformation(userId);

            // 返回 ResponseResult 对象
            response.getWriter().write(JSON.toJSONString(ResponseResult.success(targetUser)));
        } else {
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "未登录"));
            response.getWriter().write(json);
        }
    }


    /*--------------------------------------------   更新个人昵称   --------------------------------------------*/
    public void updateName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String newName = request.getParameter("newName");
        System.out.println("用户id：" + id + "，准备更换昵称：" + newName);
        if (updateService.updateName(id, newName)) {
            response.getWriter().write(JSON.toJSONString(ResponseResult.success("昵称更改成功！")));
        } else {
            String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_CONFLICT, "昵称已存在"));
            response.getWriter().write(json);
        }
    }


}
