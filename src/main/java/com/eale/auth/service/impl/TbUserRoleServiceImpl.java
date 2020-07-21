package com.eale.auth.service.impl;

import com.eale.auth.entity.UserRole;
import com.eale.auth.mapper.TbUserRoleMapper;
import com.eale.auth.service.TbUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
@Service
public class TbUserRoleServiceImpl extends ServiceImpl<TbUserRoleMapper, UserRole> implements TbUserRoleService {

}
