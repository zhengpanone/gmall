package com.zp.gmall.module.system.service.notice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.system.entity.notice.NoticeDO;
import com.zp.gmall.module.system.mapper.notice.NoticeMapper;
import com.zp.gmall.module.system.service.notice.INoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

//    // 常用传播行为示例
//    @Transactional(propagation = Propagation.REQUIRED)     // 默认，加入当前事务
//    @Transactional(propagation = Propagation.REQUIRES_NEW)   // 挂起当前事务，开新事务
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)   // 非事务执行
//    @Transactional(propagation = Propagation.NESTED)      // 嵌套事务（可部分回滚）

    // 实战：发送消息失败不影响主事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendNotification(String userId, String message) {
        // 即使这里失败，也不影响调用方的事务
        NoticeDO notification = new NoticeDO();
        baseMapper.insert(notification);
    }
}
