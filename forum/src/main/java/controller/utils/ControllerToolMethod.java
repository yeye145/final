package controller.utils;

import com.alibaba.fastjson.JSON;
import pojo.ResponseResult;
import pojo.User;
import utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerToolMethod {

    /*---------------------------------------    向前端发送错误响应   ------------------------------------------*/
    private static void sendError(HttpServletResponse response, int code, String message) throws IOException {
        response.getWriter().write(
                JSON.toJSONString(ResponseResult.error(code, message))
        );
        System.err.println("错误信息: " + message);
    }

    /*---------------------------------------    获取当前session中的用户id   ----------------------------------*/
    public static Integer getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return null;
        }
        return user.getId();
    }


    /*---------------------------------------    对账号进行正则验证   ------------------------------------------*/
    public static boolean accountRegexCheck(String account) {
        // 后端正则验证
        // 若不含@且不匹配手机正则
        // 若含@且不匹配邮箱正则
        return (account.matches(Constants.PHONE_REGEX)
                || account.contains("@"))
                && (account.matches(Constants.EMAIL_REGEX)
                || !account.contains("@"));

    }


    /*---------------------------------------    统一异常处理方法   --------------------------------------------*/
    public static void fetchException(HttpServletRequest request, HttpServletResponse response, Exception e, String errorMsg) {
        try {
            if (e instanceof NumberFormatException) {
                sendError(response, Constants.RESPONSE_CODE_BAD_REQUEST, errorMsg + "参数格式有误" + e.getMessage());
            } else if (e instanceof NullPointerException) {
                sendError(response, Constants.RESPONSE_CODE_BAD_REQUEST, errorMsg + "空指针异常" + e.getMessage());
            } else if (e instanceof SQLException) {
                sendError(response, Constants.RESPONSE_CODE_BAD_REQUEST, errorMsg + "数据库操作异常: " + e.getMessage());
            } else {
                sendError(response, Constants.RESPONSE_CODE_SERVER_ERROR, "服务器异常：" + errorMsg + "，详细信息：" + e.getMessage());
            }
        } catch (IOException ioException) {
            System.err.println("发送错误响应时出现异常：" + ioException.getMessage());
        } finally {
            System.err.println(errorMsg + e.getMessage());
        }
    }

    /*---------------------------------------    统一异常处理方法   ----------------------------------------------*/
    public static void fetchException(Exception e, String errorMsg) {
        if (e instanceof NumberFormatException) {
            System.err.println(Constants.RESPONSE_CODE_BAD_REQUEST + errorMsg + "参数格式有误" + e.getMessage());
        } else if (e instanceof NullPointerException) {
            System.err.println(Constants.RESPONSE_CODE_BAD_REQUEST + errorMsg + "空指针异常" + e.getMessage());
        } else if (e instanceof SQLException) {
            System.err.println(Constants.RESPONSE_CODE_BAD_REQUEST + errorMsg + "数据库操作异常: " + e.getMessage());
        } else {
            System.err.println(Constants.RESPONSE_CODE_SERVER_ERROR + "服务器异常：" + errorMsg + "，详细信息：" + e.getMessage());
        }
    }
}
