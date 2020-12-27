package com.github.yjgbg.jpaplussample.service;

import com.github.yjgbg.jpa.plus.annotations.ReturnPropsSetNull;
import com.github.yjgbg.jpaplussample.annotations.Security;
import com.github.yjgbg.jpaplussample.ctl.R;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import com.github.yjgbg.jpaplussample.jpa.entity.Role;
import com.github.yjgbg.jpaplussample.jpa.entity.User;
import com.github.yjgbg.jpaplussample.jpa.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Security
@RestController
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    @GetMapping("user")
    @ReturnPropsSetNull("roles")
    public R<Page<User>> getUserList(User user, int page, int pageSize) {
        val res = userRepo.spec().example(user).findAll(page, pageSize);
        return R.code(0).withData(res);
    }

    @GetMapping("user/{username}")
    @ReturnPropsSetNull({"roles","password"})
    public User getUser(@PathVariable String username) {
        return userRepo.spec()
                .eq(User::getUsername,username)
                .findOne()
                .orElseThrow(new BizException(404,"未找到用户"));
    }

    @GetMapping("user/{username}/roles")
    @ReturnPropsSetNull({"permissions","creator"})
    public Set<Role> getUserRoles(@PathVariable String username) {
        return userRepo.spec()
                .eq(User::getUsername,username)
                .eager(User::getRoles)
                .findOne()
                .orElseThrow(new BizException(404,"未找到用户"))
                .getRoles();
    }
}
