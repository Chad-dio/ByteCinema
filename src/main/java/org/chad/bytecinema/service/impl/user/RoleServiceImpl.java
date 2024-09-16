package org.chad.bytecinema.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chad.bytecinema.domain.po.Role;
import org.chad.bytecinema.mapper.user.RoleMapper;
import org.chad.bytecinema.service.user.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
