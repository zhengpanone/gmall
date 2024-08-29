package com.zp.module.system.service.member;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 16:31
 * Version : v1.0.0
 * Description: Member Service 接口
 */
public interface MemberService {
    /**
     * 获得会员用户的手机号
     *
     * @param memberId 会员用户编号
     * @return 手机号码
     */
    String getMemberUserMobile(String memberId);

    /**
     * 获得会员用户的邮箱
     *
     * @param id 会员用户编号
     * @return 邮箱
     */
    String getMemberUserEmail(String id);
}
