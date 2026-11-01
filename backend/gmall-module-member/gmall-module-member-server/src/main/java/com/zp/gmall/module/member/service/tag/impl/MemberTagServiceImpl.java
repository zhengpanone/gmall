package com.zp.gmall.module.member.service.tag.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.member.entity.tag.MemberTagDO;
import com.zp.gmall.module.member.mapper.tag.MemberTagMapper;
import com.zp.gmall.module.member.service.tag.IMemberTagService;
import org.springframework.stereotype.Service;

@Service
public class MemberTagServiceImpl extends ServiceImpl<MemberTagMapper, MemberTagDO> implements IMemberTagService {

}
