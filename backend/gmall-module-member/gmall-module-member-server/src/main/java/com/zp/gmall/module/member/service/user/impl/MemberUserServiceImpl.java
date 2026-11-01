package com.zp.gmall.module.member.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.member.entity.user.MemberUserDO;
import com.zp.gmall.module.member.mapper.user.MemberUserMapper;
import com.zp.gmall.module.member.service.user.IMemberUserService;
import org.springframework.stereotype.Service;

@Service
public class MemberUserServiceImpl extends ServiceImpl<MemberUserMapper, MemberUserDO> implements IMemberUserService {

}
