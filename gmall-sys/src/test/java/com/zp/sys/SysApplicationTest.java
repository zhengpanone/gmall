package com.zp.sys;

import com.zp.sys.entity.User;
import com.zp.sys.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123456");
        userMapper.insert(user);
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
