package com.zp.gmall.framework.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.util.List;

@Schema(description = "可排序的分页参数")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SortablePageParam extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "排序字段")
    private List<SortingField> sortingFields;

}