package org.chad.bytecinema.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chad.bytecinema.domain.po.UserRole;
import org.chad.bytecinema.mapper.user.UserRoleMapper;
import org.chad.bytecinema.mapper.user.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
