package com.github.yjgbg.jpaplussample.controller;

import com.github.yjgbg.jpa.plus.annotations.ReturnPropsSetNull;
import com.github.yjgbg.jpaplussample.controller.message.R;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import com.github.yjgbg.jpaplussample.jpa.entity.Role;
import com.github.yjgbg.jpaplussample.jpa.entity.User;
import com.github.yjgbg.jpaplussample.jpa.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserCtl {
    private final UserRepo userRepo;

    @GetMapping("user")
    @ReturnPropsSetNull("roles")
    public R<Page<User>> getUserList(User user, int page, int pageSize) {
        val res = userRepo.spec().example(user).findAll(page, pageSize);
        return R.code(0).withData(res);
    }

    @GetMapping("user/{username}")
    @ReturnPropsSetNull({"roles","password"}) // 将该字段置空
    public User getUser(@PathVariable String username) {
        return userRepo.spec()
                .eq(User::getUsername,username)
                .findOne()
                .orElseThrow(new BizException(404,"未找到用户"));
    }

    @GetMapping("user/{username}/roles")
    @ReturnPropsSetNull({"permissions","creator"})
    public List<Role> getUserRoles(@PathVariable String username) {
        return userRepo.spec()
                .eq(User::getUsername,username)
                .eager(User::getRoles) // eager 指定该字段为饥饿加载模式，一条sql级联查出role信息
                // 如果不启用eager加载，则会在第一次调用到getRoles的时候，在数据库再发一条sql，从而拖慢性能
                .findOne()
                .orElseThrow(new BizException(404,"未找到用户"))
                .getRoles();
    }
}
