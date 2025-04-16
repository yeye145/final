package controller;

import com.alibaba.fastjson.JSON;
import pojo.ResponseResult;
import pojo.User;
import service.impl.UploadAvatarServiceImpl;


import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.UUID;

@WebServlet("/avatar/*")
@MultipartConfig
public class AvatarController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UploadAvatarServiceImpl uploadAvatarService = new UploadAvatarServiceImpl();



    /*--------------------------------------------    上传头像    --------------------------------------------*/
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Part filePart = request.getPart("avatar");
            System.out.println("filePart:" + filePart);
            if (filePart == null || filePart.getSize() == 0) {
                System.out.println("未收到文件或文件为空");
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(400, "请选择有效的头像文件")));
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
                        ResponseResult.error(401, "用户未登录")));
                return;
            }

            Integer userId = user.getId();
            System.out.println("当前用户ID: " + userId);

            if (uploadAvatarService.uploadAvatar(filePart, savePath, fileName, userId)) {
                String json = JSON.toJSONString(
                        ResponseResult.success("/images/avatar/" + fileName));
                System.out.println("返回成功响应: " + json);
                response.getWriter().write(json);
            } else {
                String json = JSON.toJSONString(
                        ResponseResult.error(500, "上传头像失败"));
                System.out.println("返回失败响应: " + json);
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            System.out.println("上传过程中发生异常:");
            e.printStackTrace();
            response.getWriter().write(JSON.toJSONString(
                    ResponseResult.error(500, "服务器内部错误")));
        }
    }

}



