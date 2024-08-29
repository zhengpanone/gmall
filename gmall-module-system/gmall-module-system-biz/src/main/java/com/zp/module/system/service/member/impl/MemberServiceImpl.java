package com.zp.module.system.service.member.impl;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.zp.module.system.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Author : zhengpanone
 * Date : 2024/7/31 16:33
 * Version : v1.0.0
 * Description: Member Service 实现类
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Value("${gmall.info.base-package}")
    private String basePackage;

    private volatile Object memberUserApi;

    /**
     * 获得会员用户的手机号
     *
     * @param memberId 会员用户编号
     * @return 手机号码
     */
    @Override
    public String getMemberUserMobile(String memberId) {

        return "";
    }

    /**
     * 获得会员用户的邮箱
     *
     * @param id 会员用户编号
     * @return 邮箱
     */
    @Override
    public String getMemberUserEmail(String id) {
        Object user = getMemberUser(id);
        if (user == null) {
            return null;
        }
        return ReflectUtil.invoke(user, "getEmail");
    }

    private Object getMemberUser(String id) {
        if (id == null) {
            return null;
        }
        return ReflectUtil.invoke(getMemberUserApi(), "getUser", id);
    }

    private Object getMemberUserApi() {
        if (memberUserApi == null) {
            memberUserApi = SpringUtil.getBean(ClassUtil.loadClass(String.format("%s.module.member.api.user.MemberUserApi", basePackage)));
        }
        return memberUserApi;
    }
}
