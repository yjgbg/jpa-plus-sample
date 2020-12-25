package com.github.yjgbg.jpaplussample;

import com.github.yjgbg.jpa.plus.annotations.EnableJpaPlusRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring AOP: order越小越先执行，但执行结束越迟（同心圆，order越小越外圈）
 * Spring事务在结束的时候向数据库提交所有对Entity实体的修改
 * ReturnPropsSetNull会在执行结束的时候，将指定属性置空，目的是清除掉返回对象中的敏感字段，而不是修改数据库数据
 * 所以应当保证ReturnPropsSetNull的后置处理迟于事务的自动提交，
 * 所以此处需要修改事务管理AOP order 低于 ReturnPropsSetNull的Order
 **/
@SpringBootApplication
@EnableJpaPlusRepositories
public class JpaPlusSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPlusSampleApplication.class, args);
    }
}
