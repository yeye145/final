package utils;

public class Constants {

    public static final Integer RESPONSE_CODE_SUCCESS = 200;                // 请求成功
    public static final Integer RESPONSE_CODE_BAD_REQUEST = 400;            // 请求错误
    public static final Integer RESPONSE_CODE_UNAUTHORIZED = 401;           // 未认证
    public static final Integer RESPONSE_CODE_FORBIDDEN = 403;              // 权限不足
    public static final Integer RESPONSE_CODE_NOT_FOUND = 404;              // 资源不存在
    public static final Integer RESPONSE_CODE_CONFLICT = 409;               // 资源冲突
    public static final Integer RESPONSE_CODE_SERVER_ERROR = 500;           // 服务器错误


    // 过滤器白名单
    public static final String[] FREE_URLS = {"/login.html", ".css", ".png", ".js", ".images",
            "/login", "/user", "/admin", "/verify", "/report",
            "/avatar", "/user", "/board", "/post", "/comment", "/message"};

    public static final String HOT = "view_count";
    public static final String NEW = "time";

    public static final String ASCENDING_ORDER = "ASC";
    public static final String DESCENDING_ORDER = "DESC";

    // 手机号正则表达式
    public static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    // 邮箱正则表达式
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static final Integer ADMIN_ID = 1;
    public static final String ADMIN_NAME = "管理员";

    public static final String ACTION_LOGIN = "登录自己的账号";
    public static final String ACTION_REGISTER = "注册了账号";
    public static final String ACTION_UPDATE_PASSWORD = "更改了自己的账号密码";
    public static final String ACTION_UPDATE_NAME_TO = "更改了自己的昵称，新的昵称为：%s";
    public static final String ACTION_APPLY_NEW_BOARD = "申请创建一个新的版块，版块名为：%s";
    public static final String ACTION_REPORT_USER = "举报了一个用户，用户id为：%d";
    public static final String ACTION_DELETE_ALL_DEAL_BOARD_APPLY = "删除了所有已经处理的版块申请";
    public static final String ACTION_REFUSE_BOARD_APPLY = "拒绝了版块申请，版块的名称为：%s";
    public static final String ACTION_AGREE_BOARD_APPLY = "同意了版块申请，版块的名称为：%s";
    public static final String ACTION_CANCEL_SUBSCRIBE_BOARD = "取消关注版块，版块的名称为：%s";
    public static final String ACTION_BAN_USER_IN_BOARD = "在版块：%s中，封禁了id为：%d，叫：%s的用户";
    public static final String ACTION_PUBLISH_NOTICE = "在版块：%s中，发布了新的公告：%s";
    public static final String ACTION_POST_COMMENT_ON_POST = "发布了一条新的评论，在id为：%d的帖子之下，内容为：%s";
    public static final String ACTION_POST_COMMENT_ON_COMMENT = "发布了一条新的评论，在评论id：%d 之下，内容为：%s";
    public static final String ACTION_CLEAN_READ_MESSAGE = "清除了所有已读通知信息";
    public static final String ACTION_PUBLISH_POST_TITLE= "发布了新的帖子，帖子标题为：%s";
    public static final String ACTION_REPORT_POST_TITLE = "举报了一条帖子，帖子标题为：%s";
    public static final String ACTION_COLLECT= "收藏了帖子，帖子id为：%d";
    public static final String ACTION_CANCEL_COLLECT= "取消收藏帖子，帖子id为：%d";
    public static final String ACTION_LIKE_POST= "点赞了帖子，帖子id为：%d";


}
