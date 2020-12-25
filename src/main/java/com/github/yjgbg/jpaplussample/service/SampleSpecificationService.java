package com.github.yjgbg.jpaplussample.service;

import com.github.yjgbg.jpaplussample.jpa.entity.CriusUser;
import com.github.yjgbg.jpaplussample.jpa.repo.CriusUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@EnableJpaAuditing
@RequiredArgsConstructor
public class SampleSpecificationService {
    private final CriusUserRepo criusUserRepo;

    /**
     * 链式查询+数据处理范例
     * 查询到用户，以及用户的角色信息
     */

    @Transactional(rollbackOn = Exception.class)
    public Optional<CriusUser>  findByEq(Long id) { // 根据指定属性是否相等查询
        return criusUserRepo.spec()
                .eq(CriusUser::getId, id) // 查询条件
                .findOne(); // 如果找不到符合条件的结果，将会返回Optional.empty()，而不是null;
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<CriusUser>  findByLike(String email) {  //根据like查询，只适用于String类型
        return criusUserRepo.spec()
                .like(CriusUser::getEmail, "%"+email+"%") // 查询条件
                .findOne(); // 如果找不到符合条件的结果，将会返回Optional.empty()，而不是null;
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<CriusUser> findByExample(CriusUser criusUser) { // 根据样例查询
        return criusUserRepo.spec()
                .example(criusUser) // criusUser中为NULL的字段会被忽略，其他字段会用`等于`作为查询条件
                .findOne(); // 如果找不到符合条件的结果，将会返回Optional.empty()，而不是null;
    }

    @Transactional(rollbackOn = Exception.class)
    public Page<CriusUser> findPage(int page, int pageSize) { // 分页
        return criusUserRepo.spec()
                .findAll(page,pageSize); // 如果找不到符合条件的结果，将会返回空集合，而不是null
    }

    @Transactional(rollbackOn = Exception.class)
    public List<CriusUser> findByBetween(Long lower, Long upper) {
        return criusUserRepo.spec()
                .between(CriusUser::getId,lower,upper) //闭区间
                .findAll(); // 如果找不到符合条件的结果，将会返回空集合，而不是null
    }

    public List<CriusUser> greatThanOrEqualsTo(Long lower) {
        return criusUserRepo.spec()
                .ge(CriusUser::getId,lower) // 大于等于,`G`reatThanOr`E`qualsTo: GE
                .findAll(); // 如果找不到符合条件的结果，将会返回空集合，而不是null
    }
    public List<CriusUser> littleThanOrEqualsTo(Long upper) {
        return criusUserRepo.spec()
                .ge(CriusUser::getId,upper) // 小于等于,`G`reatThanOr`E`qualsTo: GE
                .findAll(); // 如果找不到符合条件的结果，将会返回空集合，而不是null
    }

    public List<CriusUser> condition(Long id) { // 根据condition判断是否应当加入该条查询条件
        return criusUserRepo.spec()
                .cond(id!=null,x -> x.eq(CriusUser::getId,id)) // 小于等于,`G`reatThanOr`E`qualsTo: GE
                .eq(id!=null,CriusUser::getId,id) // 如果condition成立，则添加此条eq查询条件，如果不成立，则不添加此条查询条件
                // 上面两行是等效的
                .findAll(); // 如果找不到符合条件的结果，将会返回空集合，而不是null
    }
}