package com.zp.framework.test.core.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;

import com.github.javafaker.Faker;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 11:31
 * Version : v1.0.0
 * Description: 随机工具类
 */
public class RandomUtils {
    private static final PodamFactory PODAM_FACTORY = new PodamFactoryImpl();
    private static final int RANDOM_STRING_LENGTH = 10;

    // 创建一个 Faker 实例，支持多语言
    private static final Faker faker = new Faker(new Locale("zh-CN"));

    @SafeVarargs
    public static <T> T randomPojo(Class<T> clazz, Consumer<T>... consumers) {
        T pojo = PODAM_FACTORY.manufacturePojo(clazz);
        // 非空时，通过它实现Pojo的进一步处理
        if (ArrayUtil.isNotEmpty(consumers)) {
            Arrays.stream(consumers).forEach(consumer -> consumer.accept(pojo));
        }
        return pojo;
    }

    public static String randomString() {
        return RandomUtil.randomString(RANDOM_STRING_LENGTH);
    }

    /**
     * 生成随机用户名
     *
     * @return 随机用户名
     */
    public static String generateUsername() {
        return faker.name().username();
    }

    /**
     * 生成随机手机号码
     *
     * @return 随机手机号码
     */
    public static String generatePhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    /**
     * 生成随机昵称
     *
     * @return 随机昵称
     */
    public static String generateNickname() {
        return faker.funnyName().name();
    }

    /**
     * 生成随机邮箱地址
     *
     * @return 随机邮箱地址
     */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * 生成随机地址
     *
     * @return 随机地址
     */
    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    /**
     * 生成随机公司名称
     *
     * @return 随机公司名称
     */
    public static String generateCompanyName() {
        return faker.company().name();
    }

    // 更多生成数据的方法可以添加在这里

    // 主函数用于测试
    public static void main(String[] args) {
        System.out.println("随机用户名: " + generateUsername());
        System.out.println("随机手机号码: " + generatePhoneNumber());
        System.out.println("随机昵称: " + generateNickname());
        System.out.println("随机邮箱: " + generateEmail());
        System.out.println("随机地址: " + generateAddress());
        System.out.println("随机公司名称: " + generateCompanyName());
    }


}
