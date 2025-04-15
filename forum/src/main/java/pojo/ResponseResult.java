package pojo;

import utils.Constants;

public class ResponseResult {
    private int code;
    private String message;
    private Object data;


    // 成功静态方法
    public static ResponseResult success(Object data) {
        return new ResponseResult(Constants.RESPONSE_CODE_SUCCESS, "操作成功", data);
    }

    // 失败静态方法
    public static ResponseResult error(int code, String message) {
        return new ResponseResult(code, message, null);
    }

    public ResponseResult() {
    }

    public ResponseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 获取
     *
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取
     *
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     *
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "ResponseResult{code = " + code + ", message = " + message + ", data = " + data + "}";
    }
}
