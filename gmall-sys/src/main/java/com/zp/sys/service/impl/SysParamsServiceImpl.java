/*


package com.zp.sys.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.zp.common.service.impl.FlexBaseServiceImpl;
import com.zp.common.utils.ConvertUtils;
import com.zp.sys.dto.SysParamsDTO;
import com.zp.sys.entity.SysParamsEntity;
import com.zp.sys.mapper.SysParamsMapper;
import com.zp.sys.service.SysParamsService;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.BusinessException;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.JsonUtils;
import io.renren.modules.sys.redis.SysParamsRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

*/
/**
 * 参数管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 *//*

@Service
public class SysParamsServiceImpl extends FlexBaseServiceImpl<SysParamsMapper, SysParamsEntity> implements SysParamsService {
    @Autowired
    private SysParamsRedis sysParamsRedis;


    @Override
    public List<SysParamsDTO> list(Map<String, Object> params) {
        List<SysParamsEntity> entityList = mapper.selectListByMap(params);

        return ConvertUtils.sourceToTarget(entityList, SysParamsDTO.class);
    }


    @Override
    public SysParamsDTO get(Long id) {
        SysParamsEntity entity = mapper.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysParamsDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysParamsDTO dto) {
        SysParamsEntity entity = ConvertUtils.sourceToTarget(dto, SysParamsEntity.class);
        insert(entity);

        sysParamsRedis.set(entity.getParamCode(), entity.getParamValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysParamsDTO dto) {
        SysParamsEntity entity = ConvertUtils.sourceToTarget(dto, SysParamsEntity.class);
        updateById(entity);

        sysParamsRedis.set(entity.getParamCode(), entity.getParamValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除Redis数据
        List<String> paramCodeList = mapper.getParamCodeList(ids);
        String[] paramCodes = paramCodeList.toArray(new String[paramCodeList.size()]);
        sysParamsRedis.delete(paramCodes);

        //删除
        deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String paramCode) {
        String paramValue = sysParamsRedis.get(paramCode);
        if (paramValue == null) {
            paramValue = mapper.getValueByCode(paramCode);

            sysParamsRedis.set(paramCode, paramValue);
        }
        return paramValue;
    }

    @Override
    public <T> T getValueObject(String paramCode, Class<T> clazz) {
        String paramValue = getValue(paramCode);
        if (StringUtils.isNotBlank(paramValue)) {
            return JsonUtils.parseObject(paramValue, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.PARAMS_GET_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateValueByCode(String paramCode, String paramValue) {
        int count = mapper.updateValueByCode(paramCode, paramValue);
        sysParamsRedis.set(paramCode, paramValue);
        return count;
    }

}*/
