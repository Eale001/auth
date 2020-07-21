package com.eale.auth.service.impl;

import com.eale.auth.entity.Role;
import com.eale.auth.mapper.TbRoleMapper;
import com.eale.auth.service.TbRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
@Service
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, Role> implements TbRoleService {

}
