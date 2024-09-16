package org.chad.bytecinema.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chad.bytecinema.domain.po.RolePermission;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
