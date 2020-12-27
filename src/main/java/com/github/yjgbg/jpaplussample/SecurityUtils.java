package com.github.yjgbg.jpaplussample;

import com.github.yjgbg.jpaplussample.config.BeanHook;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import com.github.yjgbg.jpaplussample.jpa.entity.Role;
import com.github.yjgbg.jpaplussample.jpa.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.github.yjgbg.jpa.plus.utils.Getter.c;
import static com.github.yjgbg.jpa.plus.utils.Getter.s;

@RequiredArgsConstructor
public class SecurityUtils {
    private static final String KEY = "CURRENT_USER";


    public static User currentUser() {
        val request = BeanHook.self().request();
        val session = request.getSession();
        return (User) session.getAttribute(KEY);
    }

    public static void setCurrentUser(String username) {
        val user = BeanHook.self().userRepo()
                .spec()
                .eq(User::getUsername, username)
                .eager(s(c(User::getRoles), Role::getPermissions))
                .findOne()
                .orElseThrow(new BizException(404,"没有找到该用户"));
        val request = BeanHook.self().request();
        val session = request.getSession();
        session.setAttribute(KEY, user);
    }
}
