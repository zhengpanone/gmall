package com.zp.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDao
 *
 * @author zhengpanone
 * @since 2022-06-30
 */
@Mapper
public interface UserMapper extends OptionalBaseMapper<User> {
}
