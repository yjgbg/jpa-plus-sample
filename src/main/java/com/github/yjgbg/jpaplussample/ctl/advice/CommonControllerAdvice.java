package com.github.yjgbg.jpaplussample.ctl.advice;

import com.github.yjgbg.jpaplussample.ctl.R;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonControllerAdvice {
    private final HttpServletResponse response;
    @ExceptionHandler
    public R<Void> onBizException(BizException e) {
        response.setStatus(e.getStatus());
        return R.code(e.getStatus()).withMessage(e.getMessage());
    }
}
