package com.zp.product;

import com.zp.product.dto.BrandDTO;
import com.zp.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ProductApplicationTest
 *
 * @author zhengpanone
 * @since 2022-06-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTest {

    @Autowired
    BrandService brandService;

    @Test
    public void contextLoads(){
        BrandDTO brandDTO = new BrandDTO();
        /*brandDTO.setName("小米");
        brandDTO.setDescript("小米手机");
        brandService.save(brandDTO);
        System.out.println("保存成功");*/
        brandDTO.setId(1L);
        brandDTO.setName("红米");
        brandService.update(brandDTO);
    }
}
