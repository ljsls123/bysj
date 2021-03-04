package demo.model.response;

import java.io.Serializable;

import demo.constant.ErrorCode;
import lombok.Data;

@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -5957731478544505658L;

    private int code;
    private String msg;
    private T result;

    private ResponseResult(ErrorCode errorCode, T result) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.result = result;
    }

    public static <T> ResponseResult<T> success(T result) {
        return new ResponseResult<>(ErrorCode.SUCCESS, result);
    }

    public static ResponseResult<Void> success() {
        return new ResponseResult<>(ErrorCode.SUCCESS, null);
    }

    public static ResponseResult<Void> error(ErrorCode errorCode) {
        return new ResponseResult<>(errorCode, null);
    }
}
