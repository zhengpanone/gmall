package com.zp.gmall.module.system.mapper.permission;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.gmall.framework.common.util.tree.TreeJson;
import com.zp.gmall.framework.common.util.tree.TreeUtils;
import com.zp.gmall.framework.mybatis.config.MybatisPlusConfiguration;
import com.zp.gmall.module.system.SystemServerApplication;
import com.zp.gmall.module.system.convert.permission.MenuConvert;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 创建一个基于内存的数据库环境
@Rollback(value = true)    // 自动回滚，不写入数据库
@ActiveProfiles("test")
@Import(MybatisPlusConfiguration.class)
@ContextConfiguration(classes = SystemServerApplication.class)
@Slf4j
class MenuMapperTest {
    @Resource
    MenuMapper menuMapper;

    @Test
    void selectMenuByUserId() {
    }

    @Test
    void selectPermsByUserId() {
    }

    @Test
    void checkMenuKeyUnique() {
    }

    @Test
    void testSelectList() {
        List<MenuDO> menuList = menuMapper.selectList(null);
        log.info("菜单列表={}", menuList);
        List<TreeJson> treeJsons = MenuConvert.INSTANCE.convertTreeJson(menuList);
        List<TreeJson> tree = TreeUtils.toTree(treeJsons);
        log.info("菜单树={}", tree);
    }
}