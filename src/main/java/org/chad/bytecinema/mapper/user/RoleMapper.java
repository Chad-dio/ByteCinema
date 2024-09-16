package org.chad.bytecinema.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chad.bytecinema.domain.po.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
