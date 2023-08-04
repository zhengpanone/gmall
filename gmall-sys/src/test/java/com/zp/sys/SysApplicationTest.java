package com.zp.sys;

import cn.hutool.core.lang.Opt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.zp.sys.entity.User;
import com.zp.sys.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;

/**
 * SysApplicationTest
 *
 * @author zhengpanone
 * @since 2022-07-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysApplicationTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void testMapper(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        User user = new User();
        user.setUsername("王五");
        user.setPassword("654321");

        userMapper.insert(user);
        Optional<List<User>> users1 = Optional.ofNullable(userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername,"aaa")));
        //Optional<List<User>> users = userMapper.list(null);
//        Optional<List<User>> users = userMapper.selectList(null);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(users1);
        stopWatch.stop();
        System.out.println("耗时："+ stopWatch.getTotalTimeSeconds());
    }
}
