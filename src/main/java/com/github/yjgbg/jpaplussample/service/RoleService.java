package com.github.yjgbg.jpaplussample.service;

import com.github.yjgbg.jpaplussample.annotations.Security;
import com.github.yjgbg.jpaplussample.exceptions.BizException;
import com.github.yjgbg.jpaplussample.jpa.entity.Role;
import com.github.yjgbg.jpaplussample.jpa.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Security
@RestController
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    @GetMapping("role")
    public Page<Role> getRoleList(Role role, int page, int pageSize) {
        return roleRepo.spec()
                .example(role)
                .findAll(page, pageSize);
    }

    @GetMapping("role/{id}")
    public Role getRole(@PathVariable Long id) {
        return roleRepo.spec()
                .eq(Role::getId, id)
                .findOne()
                .orElseThrow(new BizException(404, "未找到角色"));
    }
}
