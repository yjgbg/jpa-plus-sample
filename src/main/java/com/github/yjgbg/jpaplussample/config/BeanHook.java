package com.github.yjgbg.jpaplussample.config;

import com.github.yjgbg.jpaplussample.jpa.repo.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Getter
@Component
@Accessors(fluent = true)
@RequiredArgsConstructor
public class BeanHook {
    private static BeanHook SELF;
    private final HttpServletRequest request;
    private final UserRepo userRepo;

    @Autowired
    public void injectSelf(BeanHook beanHook) {
        SELF = beanHook;
    }

    public static BeanHook self() {
        return SELF;
    }
}
