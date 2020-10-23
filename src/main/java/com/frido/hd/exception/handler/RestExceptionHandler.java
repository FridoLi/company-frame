package com.frido.hd.exception.handler;

import com.frido.hd.exception.BusinessException;
import com.frido.hd.exception.code.BaseResponseCode;
import com.frido.hd.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    /**
       * 系统繁忙，请稍候再试
       * @Author:   小霍
       * @UpdateUser:
       * @Version:   0.0.1
       * @param e
       * @return    com.yingxue.lesson.utils.DataResult<T>
       * @throws
       */
    @ExceptionHandler(Exception.class)
    public <T> DataResult<T> handleException(Exception e){
        log.error("Exception,exception:{}", e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }
    @ExceptionHandler(BusinessException.class)
    public DataResult handlerBusinessException(BusinessException e){
        log.error("BusinessException ...{}",e);
        return DataResult.getResult(e.getMessageCode(),e.getDetailMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        log.error("handlerMethodArgumentNotValidException  AllErrors:{} MethodArgumentNotValidException:{}",e.getBindingResult().getAllErrors(),e);
        String msg="";
        for(ObjectError error:allErrors){
            msg+=error.getDefaultMessage();
            msg+=";";
        }
        return DataResult.getResult(BaseResponseCode.VALIDATOR_ERROR.getCode(),msg);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public DataResult unauthorizedException(UnauthorizedException e){
        log.error("UnauthorizedException:{}",e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }
}
