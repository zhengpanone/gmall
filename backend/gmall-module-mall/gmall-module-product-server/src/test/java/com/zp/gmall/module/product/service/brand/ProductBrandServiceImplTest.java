package com.zp.gmall.module.product.service.brand;

import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductBrandServiceImplTest {

    @Autowired
    private IProductBrandService productBrandService;

    @Test
    void getById() {
    }

    @Test
    void getPage() {
    }

    @Test
    void update() {
    }

    @Test
    void create() {
        ProductBrandDTO dto = new ProductBrandDTO().setName("小米");
        productBrandService.create(dto);
    }

    @Test
    void validateBrandNameUnique() {
    }
}