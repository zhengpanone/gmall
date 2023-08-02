package com.zp.product.mapstruct.struct;

import com.zp.common.convert.BaseConvert;
import com.zp.product.dto.CategoryDTO;
import com.zp.product.entity.CategoryEntity;
import com.zp.product.vo.CategoryTreeVO;
import com.zp.product.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryConvert extends BaseConvert<CategoryDTO, CategoryEntity, CategoryVO> {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    CategoryDTO toDTO(CategoryEntity categoryEntity);

    CategoryVO toVO(CategoryEntity categoryEntity);

    List<CategoryVO> toVOs(List<CategoryEntity> categoryEntity);

    @Mappings(
            @Mapping(target = "sort", ignore = true)
    )
    List<CategoryTreeVO> toTreeVOs(List<CategoryEntity> list);


}
