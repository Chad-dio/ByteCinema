package org.chad.bytecinema.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chad.bytecinema.domain.po.Permission;
import org.chad.bytecinema.mapper.user.PermissionMapper;
import org.chad.bytecinema.mapper.user.PermissionService;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
