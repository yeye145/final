package utils;

public class Constants {

    public static final Integer RESPONSE_CODE_SUCCESS = 200;                // 请求成功

    public static final Integer RESPONSE_CODE_BAD_REQUEST = 400;            // 请求错误
    public static final Integer RESPONSE_CODE_UNAUTHORIZED = 401;           // 未认证
    public static final Integer RESPONSE_CODE_NOT_FOUND = 404;              // 资源不存在
    public static final Integer RESPONSE_CODE_CONFLICT = 409;               // 资源冲突

    public static final Integer RESPONSE_CODE_SERVER_ERROR = 500;           // 服务器错误


    // 过滤器白名单
    public static final String[] FREE_URLS = {"/login.html", ".css", ".png", ".js", ".images",
            "/login", "/user", "/admin", "/verify", "/forget", "/register",
            "/avatar", "/user", "/board", "/post", "/comment"};

    public static final String HOT = "view_count";
    public static final String NEW = "time";

    public static final String ASCENDING_ORDER = "ASC";
    public static final String DESCENDING_ORDER = "DESC";

    // 手机号正则表达式
    public static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    // 邮箱正则表达式
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

}
