package com.zp.sys;

import com.zp.sys.entity.SysUserEntity;
import com.zp.sys.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private SysUserMapper sysUserMapper;

    @Test
    public void insert() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername("aaaa");
        sysUserMapper.insertSelective(sysUserEntity);
    }

}
