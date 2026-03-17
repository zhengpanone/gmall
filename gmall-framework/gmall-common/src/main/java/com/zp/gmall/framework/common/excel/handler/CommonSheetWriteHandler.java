package com.zp.gmall.framework.common.excel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.SheetWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : zhengpanone
 * Date : 2025/8/13 09:50
 * Version : v1.0.0
 * Description:
 */
@Slf4j
public class CommonSheetWriteHandler implements SheetWriteHandler {
    private Class<?> clazz;
    public static final String VERSION = "version";
    private String template;

    public CommonSheetWriteHandler(Class<?> clazz) {
        this.clazz = clazz;
    }

    public CommonSheetWriteHandler(Class<?> clazz, String template) {
        this.clazz = clazz;
        this.template = template;
    }


    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        try {
            if (StringUtils.isNoneBlank(template) && clazz != null) {
                // TODO
            }
        } catch (Exception e) {
            log.error("CommonSheetWriteHandler beforeSheetCreate error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterSheetCreate(SheetWriteHandlerContext context) {
        SheetWriteHandler.super.afterSheetCreate(context);
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        SheetWriteHandler.super.afterSheetCreate(writeWorkbookHolder, writeSheetHolder);
    }
}
