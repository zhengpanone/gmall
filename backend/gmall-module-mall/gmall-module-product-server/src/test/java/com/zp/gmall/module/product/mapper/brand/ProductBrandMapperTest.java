package com.zp.gmall.module.product.mapper.brand;

import com.zp.gmall.module.product.entity.brand.ProductBrandDO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("local")
@MapperScan(basePackages = "com.zp.gmall.module.product.mapper")
class ProductBrandMapperTest {

    @Autowired
    private ProductBrandMapper productBrandMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBrandMapperTest.class);

    @Test
    void selectByName() {
        ProductBrandDO brandDO = productBrandMapper.selectByName("小米");
        LOGGER.info("brandDO:{}", brandDO);
        assertEquals("小米", brandDO.getName());
    }
}