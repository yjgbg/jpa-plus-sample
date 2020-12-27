package com.github.yjgbg.jpaplussample.jpa.repo;

import com.github.yjgbg.jpa.plus.repository.StdRepository;
import com.github.yjgbg.jpaplussample.jpa.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends StdRepository<User, Long> {
}
