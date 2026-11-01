package com.zp.gmall.module.system.mapper.permission;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.gmall.framework.mybatis.config.MybatisPlusConfiguration;
import com.zp.gmall.module.system.SystemServerApplication;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.enums.permission.RoleTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:50
 * Version : v1.0.0
 * Description:
 */
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 创建一个基于内存的数据库环境
@Rollback(value = true)    // 自动回滚，不写入数据库
@ActiveProfiles("test")
@Import(MybatisPlusConfiguration.class)
@ContextConfiguration(classes = SystemServerApplication.class)
@Slf4j
class RoleMapperTest {

    @Resource
    RoleMapper roleMapper;

    @Test
    public void testInsert() {
        RoleDO role = RoleDO.builder().id("1").name("管理员").code("admin").type(RoleTypeEnum.SYSTEM.getType()).build();
        int insert = roleMapper.insert(role);
        log.info("是否插入成功={}", insert == 1);
    }

    @Test
    public void testSelectById() {
        RoleDO role = roleMapper.selectById("1");
        log.info("角色信息={}", role);
    }

    @Test
    public void testSelectList() {
        List<RoleDO> roleList = roleMapper.selectList(null);
        log.info("角色列表={}", roleList);
    }

}
