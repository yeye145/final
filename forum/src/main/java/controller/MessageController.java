package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import dao.utils.MyUpdate;
import pojo.ResponseResult;
import service.MessageService;
import service.impl.MessageServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/message/*")
public class MessageController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private MessageService messageService = new MessageServiceImpl();


    /*--------------------------------------------    获取个人通知信息    --------------------------------------*/
    public void getAllMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("MessageController,getAllMessage，获取所有信息，用户id：" + userId);

            // 返回信息集合
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(messageService.getOneMessageList(userId))
                    )
            );

            System.out.println("---->获取所有信息成功！用户id：" + userId);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取个人通知信息时出错");
        }
    }


    /*-----------------------------------------    是否有新的信息    --------------------------------------------*/
    public void checkIfNewMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("MessageController,checkIfNewMessage，查看是否有新的信息，用户id：" + userId);

            if (messageService.checkIfNewMessage(userId)) {
                // 有新的通知
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("yes")
                        )
                );

                System.out.println("---->有新的信息！用户id：" + userId);
            } else {
                // 没有新的通知
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("no")
                        )
                );

                System.out.println("---->《没》有新的信息！用户id：" + userId);
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "查看是否有新的信息时出错");
        }
    }


    /*--------------------------------------------    查收信息    ----------------------------------------------*/
    public void receiveThisMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer messageId = Integer.parseInt(request.getParameter("messageId"));
            System.out.println("MessageController，receiveThisMessage，查收信息" + messageId);
            messageService.receiveThisMessage(messageId);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "查收信息时出错");
        }
    }


    /*-----------------------------------------    清空所有已读信息    -------------------------------------------*/
    public void deleteAllReceiveMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("MessageController.deleteAllReceiveMessage，清空所有已读信息，用户id：" + userId);
            messageService.deleteAllReceiveMessage(userId);
            // 返回信息集合
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("清除成功")
                    )
            );
            System.out.println("---->清楚所有已读信息成功！用户id：" + userId);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "清空所有已读信息时出错");
        }
    }


}
