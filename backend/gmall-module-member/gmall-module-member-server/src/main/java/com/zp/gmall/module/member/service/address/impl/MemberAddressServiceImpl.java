package com.zp.gmall.module.member.service.address.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.member.entity.address.MemberAddressDO;
import com.zp.gmall.module.member.mapper.address.MemberAddressMapper;
import com.zp.gmall.module.member.service.address.IMemberAddressService;
import org.springframework.stereotype.Service;

@Service
public class MemberAddressServiceImpl extends ServiceImpl<MemberAddressMapper, MemberAddressDO> implements IMemberAddressService {

}
