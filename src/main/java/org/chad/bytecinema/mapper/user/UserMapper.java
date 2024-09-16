package org.chad.bytecinema.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chad.bytecinema.domain.po.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
