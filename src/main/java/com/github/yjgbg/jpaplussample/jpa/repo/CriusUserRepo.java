package com.github.yjgbg.jpaplussample.jpa.repo;

import com.github.yjgbg.jpa.plus.repository.StdRepository;
import com.github.yjgbg.jpaplussample.jpa.entity.CriusUser;
import org.springframework.stereotype.Repository;

@Repository
public interface CriusUserRepo extends
        StdRepository<CriusUser,Long> {
}
