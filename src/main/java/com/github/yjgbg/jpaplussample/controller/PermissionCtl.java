package com.github.yjgbg.jpaplussample.controller;

import com.github.yjgbg.jpaplussample.annotations.Security;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import com.github.yjgbg.jpaplussample.jpa.entity.Permission;
import com.github.yjgbg.jpaplussample.jpa.repo.PermissionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Security
@RestController
@RequiredArgsConstructor
public class PermissionCtl {
    private final PermissionRepo permissionRepo;

    @GetMapping("permission")
    public Page<Permission> getUserList(Permission permission, int page, int pageSize) {
        return permissionRepo.spec().example(permission).findAll(page, pageSize);
    }

    @GetMapping("permission/{id}")
    public Permission getUser(@PathVariable Long id) {
        return permissionRepo.spec()
                .eq(Permission::getId,id)
                .eager(Permission::getCreator)
                .findOne()
                .orElseThrow(new BizException(404,"未找到该权限"));
    }
}
