package com.github.yjgbg.jpaplussample.jpa.repo;

import com.github.yjgbg.jpa.plus.repository.StdRepository;
import com.github.yjgbg.jpaplussample.jpa.entity.CriusRole;
import org.springframework.stereotype.Repository;

@Repository
public interface CriusRoleRepo extends
        StdRepository<CriusRole,Long> {
}
