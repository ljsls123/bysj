package demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.constant.ErrorCode;
import demo.exception.BizException;
import demo.model.response.ResponseResult;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Repository
@ResponseBody
public class GlobalExcepitonHandler {

    @ExceptionHandler(value = BizException.class)
    public ResponseResult<Void> bizExceptionHandler(HttpServletRequest request, BizException e) {
        return ResponseResult.error(e.getErrorCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Void> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ResponseResult.error(ErrorCode.SERVICE_INTERNAL_ERROR);
    }
}
