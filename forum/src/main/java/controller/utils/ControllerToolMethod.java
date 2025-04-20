package controller.utils;

import pojo.User;
import utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerToolMethod {

    public static Integer getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static boolean accountRegexCheck(String account) {
        // 后端正则验证
        if (    // 若不含@且不匹配手机正则
                (!account.matches(Constants.PHONE_REGEX) && !account.contains("@")
                ) || (  // 若含@且不匹配邮箱正则
                        !account.matches(Constants.EMAIL_REGEX) && account.contains("@")
                )
        ) {
            return false;
        }
        return true;

    }
}
