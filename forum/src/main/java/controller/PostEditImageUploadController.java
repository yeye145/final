package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import pojo.ResponseResult;
import utils.Constants;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/image/*")
@MultipartConfig
public class PostEditImageUploadController extends BaseServlet {

    // 允许的图片类型
    private static final String[] ALLOWED_TYPES = {"image/jpeg", "image/png", "image/gif"};
    // 最大文件大小 2MB
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 获取上传的文件部分
            Part filePart = request.getPart("image");

            // 验证文件是否存在
            if (filePart == null || filePart.getSize() == 0) {
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_BAD_REQUEST, "请选择有效的头像文件")));
                return;
            }

            // 验证文件类型
            String contentType = filePart.getContentType();
            if (!isValidType(contentType)) {
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_BAD_REQUEST, "请选择有效的头像文件")));
                return;
            }

            // 验证文件大小
            if (filePart.getSize() > MAX_FILE_SIZE) {
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_BAD_REQUEST, "图片不能大于2MB")));
                return;
            }

            // 生成唯一文件名
            String fileName = UUID.randomUUID() + getFileExtension(contentType);
            String savePath = getServletContext().getRealPath("/")
                    + "images" + File.separator
                    + "post_images" + File.separator;

            // 确保目录存在
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // 保存文件
            filePart.write(savePath + fileName);

            // 返回访问路径
            String imageUrl = "/images/post_images/" + fileName;
            out.write("{\"code\":200,\"data\":\"" + imageUrl + "\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(JSON.toJSONString(
                    ResponseResult.error(Constants.RESPONSE_CODE_SERVER_ERROR, "服务器处理错误")));
        }
    }

    // 验证文件类型
    private boolean isValidType(String contentType) {
        for (String type : ALLOWED_TYPES) {
            if (type.equals(contentType)) return true;
        }
        return false;
    }

    // 获取文件扩展名
    private String getFileExtension(String contentType) {
        switch (contentType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            default:
                return "";
        }
    }
}

