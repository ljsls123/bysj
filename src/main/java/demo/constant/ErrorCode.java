package demo.constant;

public enum ErrorCode {
    /**
     * 处理成功
     */
    SUCCESS(0, "处理成功"),

    /**
     * 通用错误码 服务器错误
     */
    SERVICE_INTERNAL_ERROR(-10001, "服务器内部错误"),
    SERVICE_BUSY_ERROR(-10002, "服务器繁忙"),
    SERVICE_TIME_OUT_ERROR(-10003, "服务器超时"),
    BAD_REQUEST_ERROR(-10004, "请求参数有误"),

    /**
     * 注册错误码
     */
    EMAIL_FORMAT_ERROR(-20101, "邮箱格式不合法"),
    PASSWORD_FORMAT_ERROR(-20102, "密码格式不合法"),
    EMAIL_EXISTED_ERROR(-20111, "邮箱已存在"),

    /**
     * 登录错误码
     */
    PASSWORD_WRONG_ERROR(-20112, "密码不正确"),
    EMAIL_NOT_EXISTED_ERROR(-20113, "邮箱不存在"),


    /**
     * 获取用户信息错误码
     */
    USER_NOT_EXISTED(-20222, "用户不存在"),

    /**
     * 更新用户请求错误码
     */
    NICKNAME_FORMAT_ERROR(-20103, "昵称格式不合法"),
    ADDRESS_FORMAT_ERROR(-20104, "地址格式不合法"),
    NICKNAME_AND_ADDRESS_BOTH_NULL(-20107, "地址和昵称都为空"),

    /**
     * 修改密码错误码
     */
    OLD_PASSWORD_FORMAT_ERROR(-20105, "老密码格式不合法"),
    NEW_PASSWORD_FORMAT_ERROR(-20106, "新密码格式不合法"),
    OLD_PASSWORD_WRONG_ERROR(-20112, "老密码不正确");

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误描述
     */
    private String msg;

    /**
     * Constructor
     *
     * @param code error code
     * @param msg  error message
     */
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorCodeEnum{"
                + "code=" + code
                + ", msg='" + msg
                + '\'' + '}';
    }
}
