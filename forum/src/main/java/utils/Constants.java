package utils;

public class Constants {

    public static final Integer RESPONSE_CODE_SUCCESS = 200;

    public static final Integer RESPONSE_CODE_BAD_REQUEST = 400;
    public static final Integer RESPONSE_CODE_UNAUTHORIZED = 401;
    public static final Integer RESPONSE_CODE_CONFLICT = 409;

    public static final Integer RESPONSE_CODE_SERVER_ERROR = 500;


    // 过滤器白名单
    public static final String[] FREE_URLS = {"/login.html", ".css", ".png", ".js", ".images",
            "/login", "/user", "/admin", "/verify", "/forget", "/register",
            "/avatar", "/user", "/board", "/post"};
}
