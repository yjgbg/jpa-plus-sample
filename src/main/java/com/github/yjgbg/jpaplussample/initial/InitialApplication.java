package com.github.yjgbg.jpaplussample.initial;

import com.github.yjgbg.jpa.plus.config.JpaPlusAutoConfiguration;
import com.github.yjgbg.jpaplussample.jpa.entity.Permission;
import com.github.yjgbg.jpaplussample.jpa.entity.Role;
import com.github.yjgbg.jpaplussample.jpa.entity.User;
import com.github.yjgbg.jpaplussample.jpa.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitialApplication {
    private final UserRepo userRepo;
    private final JpaPlusAutoConfiguration jpaPlusAutoConfiguration;

    @PostConstruct
    @Transactional
    public void postConstructor() {
        // 判断是否初始化过
        val initialized = userRepo.spec()
                .eq(User::getUsername,"root")
                .exist();
        if (initialized) return;
        //  如果没有初始化过，则初始化
        // 创建root用户
        val user = new User().setUsername("root")
                .setPassword("root")
                .save();
        // 创建superuser角色
        val role = new Role().setName("superuser")
                .setDescription("超级管理员")
                .setCreator(user)
                .save();
        // 创建最高权限
        val permission = new Permission()
                .setName("com.github.yjgbg.jpaplussample")
                .setCreator(user)
                .save();
        // 将最高权限赋予角色superuser
        role.addPermission(permission).save();
        // 设定root用户为superuser角色
        user.addRole(role).save();
    }
}
