package com.zp.gmall.module.infra.convert.codegen;

import com.zp.gmall.framework.common.util.collection.CollectionUtils;
import com.zp.gmall.module.infra.controller.admin.codegen.vo.CodegenPreviewVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface CodegenConvert {
    CodegenConvert INSTANCE = Mappers.getMapper(CodegenConvert.class);

    default List<CodegenPreviewVO> convert(Map<String, String> codes) {
        return CollectionUtils.convertList(codes.entrySet(),
                entry -> new CodegenPreviewVO().setFilePath(entry.getKey()).setCode(entry.getValue()));
    }
}
