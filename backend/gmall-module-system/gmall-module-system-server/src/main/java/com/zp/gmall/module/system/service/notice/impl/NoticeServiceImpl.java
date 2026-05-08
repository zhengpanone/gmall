package com.zp.gmall.module.system.service.notice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.system.entity.notice.NoticeDO;
import com.zp.gmall.module.system.mapper.notice.NoticeMapper;
import com.zp.gmall.module.system.service.notice.INoticeService;
import org.springframework.stereotype.Service;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, NoticeDO> implements INoticeService {
}
