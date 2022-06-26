package com.zp.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ProductApplication
 *
 * @author zhengpanone
 * @since 2022-06-25
 */
@SpringBootApplication
@MapperScan("com.zp.product.dao")
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
