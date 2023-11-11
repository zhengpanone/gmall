/*
package com.zp.common.service.impl;


import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.constant.SqlConsts;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryOrderBy;
import com.mybatisflex.core.query.QueryOrderByBuilder;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.zp.common.constant.Constant;
import com.zp.common.page.PageData;
import com.zp.common.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

*/
/**
 * 基础服务类，所有Service都要继承
 *
 * @author
 *//*

public abstract class FlexBaseServiceImpl<M extends BaseMapper<T>, T>  implements IService<T> {
    @Autowired
    protected M mapper;

    public FlexBaseServiceImpl(){

    }

    public BaseMapper<T> getMapper() {
        return this.mapper;
    }
    protected Log log = LogFactory.getLog(getClass());

    */
/**
     * 获取分页对象
     * @param params      分页查询参数
     * @param defaultOrderField  默认排序字段
     * @param isAsc              排序方式
     *//*

    */
/*protected Page<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        int curPage = 1;
        int limit = 10;

        if(params.get(Constant.PAGE) != null){
            curPage = Integer.parseInt((String)params.get(Constant.PAGE));
        }
        if(params.get(Constant.LIMIT) != null){
            limit = Integer.parseInt((String)params.get(Constant.LIMIT));
        }

        //分页对象
        Page<T> page = Page.of(curPage, limit);

        //分页参数
        params.put(Constant.PAGE, page);

        //排序字段
        String orderField = (String)params.get(Constant.ORDER_FIELD);
        String order = (String)params.get(Constant.ORDER);

        //前端字段排序
        if(StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)){
            QueryWrapper queryWrapper = QueryWrapper.create();
            if(Constant.ASC.equalsIgnoreCase(order)) {
//                queryWrapper.orderBy()
                QueryColumn queryColumn = new QueryColumn(orderField);
                QueryOrderBy queryOrderBy = new QueryOrderBy(queryColumn, SqlConsts.ASC);


                return   page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }*//*


    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target){
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);

        return new PageData<>(targetList, total);
    }

   */
/* protected <T> PageData<T> getPageData(IPage page, Class<T> target){
        return getPageData(page.getRecords(), page.getTotal(), target);
    }*//*


    protected void paramsToLike(Map<String, Object> params, String... likes){
        for (String like : likes){
            String val = (String)params.get(like);
            if (StringUtils.isNotBlank(val)){
                params.put(like, "%" + val + "%");
            }else {
                params.put(like, null);
            }
        }
    }














}*/
