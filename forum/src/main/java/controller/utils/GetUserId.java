package controller.utils;

import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetUserId {

    public static Integer getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            return null;
        }

        return user.getId();
    }
}
