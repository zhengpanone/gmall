package com.zp.gmall.framework.common.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 01:06
 * Version : v1.0.0
 * Description:
 */
@Data
public class Ids {

    @NotEmpty(message = "ids不能为空")
    private Collection<? extends Serializable> ids;
}
