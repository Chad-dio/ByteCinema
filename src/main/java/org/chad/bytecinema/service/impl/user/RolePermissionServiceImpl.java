package org.chad.bytecinema.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chad.bytecinema.domain.po.RolePermission;
import org.chad.bytecinema.mapper.user.RolePermissionMapper;
import org.chad.bytecinema.mapper.user.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
