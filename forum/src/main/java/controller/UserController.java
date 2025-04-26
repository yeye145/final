package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.ResponseResult;
import pojo.User;

import service.UserService;
import service.impl.UserServiceImpl;
import utils.Constants;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@MultipartConfig
@WebServlet("/user/*")
public class UserController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UserService userService = new UserServiceImpl();


    /*--------------------------------------------    上传头像    --------------------------------------------*/
    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response)  {

        try {
            Part filePart = request.getPart("avatar");
            System.out.println("filePart:" + filePart);
            if (filePart == null || filePart.getSize() == 0) {
                System.out.println("未收到文件或文件为空");
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_BAD_REQUEST, "请选择有效的头像文件")));
                return;
            }

            String fileName = UUID.randomUUID() + ".jpg";
            String savePath = getServletContext().getRealPath("/") + "images\\avatar\\" + fileName;

            System.out.println("准备保存头像到: " + savePath);

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                System.out.println("用户未登录");
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "用户未登录")));
                return;
            }

            Integer userId = user.getId();
            System.out.println("当前用户ID: " + userId);

            if (userService.uploadAvatar(filePart, savePath, fileName, userId)) {
                String json = JSON.toJSONString(
                        ResponseResult.success("/images/avatar/" + fileName));
                System.out.println("返回成功响应: " + json);
                response.getWriter().write(json);
            } else {
                String json = JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_SERVER_ERROR, "上传头像失败"));
                System.out.println("返回失败响应: " + json);
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "上传头像时出错");
        }
    }


    /*-------------------------------------------    关注这个作者    ------------------------------------------*/
    public void subscribeThisUser(HttpServletRequest request, HttpServletResponse response)  {
        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "关注这个作者时出错");
        }
    }


    /*-----------------------------------------    取消关注这个作者    -----------------------------------------*/
    public void cancelSubscribeThisUser(HttpServletRequest request, HttpServletResponse response)  {

        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "取消关注这个作者时出错");
        }
    }


    /*--------------------------------------    判断用户是否关注该作者    ------------------------------------------*/
    public void checkIfHadSubscribeThisUser(HttpServletRequest request, HttpServletResponse response)  {

        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "判断用户是否关注该作者时出错");
        }

    }


    /*--------------------------------------------    获取头像    --------------------------------------------*/
    public void avatar(HttpServletRequest request, HttpServletResponse response)  {

        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取头像时出错");
        }
    }


    /*--------------------------------------------   获取个人信息   --------------------------------------------*/
    public void information(HttpServletRequest request, HttpServletResponse response)  {
        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取个人信息时出错");
        }
    }


    /*--------------------------------------------   更新个人昵称   --------------------------------------------*/
    public void updateName(HttpServletRequest request, HttpServletResponse response)  {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String newName = request.getParameter("newName");
            System.out.println("用户id：" + id + "，准备更换昵称：" + newName);
            if (userService.updateName(id, newName)) {
                response.getWriter().write(JSON.toJSONString(ResponseResult.success("昵称更改成功！")));
            } else {
                String json = JSON.toJSONString(ResponseResult.error(Constants.RESPONSE_CODE_CONFLICT, "昵称已存在"));
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "更新个人昵称时出错");
        }
    }


}
