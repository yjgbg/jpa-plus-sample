package com.github.yjgbg.jpaplussample.annotations.processing;

import com.github.yjgbg.jpaplussample.SecurityUtils;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import lombok.SneakyThrows;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(Integer.MIN_VALUE)
public class SecurityProcessing {
    @SneakyThrows
    @Around("@within(com.github.yjgbg.jpaplussample.annotations.Security)")
    public Object send(ProceedingJoinPoint joinPoint) {
        val methodSig = (MethodSignature) joinPoint.getSignature();
        val method = methodSig.getMethod();
        val obj = joinPoint.getTarget().getClass();
        val permissionName = obj.getName() + "."+method.getName();
        val user = SecurityUtils.currentUser();
        if (user==null) throw new BizException(401,"需要登录");
        val hasPermission = SecurityUtils.currentUser().hasPermission(permissionName);
        if (!hasPermission) throw new BizException(403,"权限不够");
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
