package com.eale.auth.service.impl;

import com.eale.auth.entity.User;
import com.eale.auth.mapper.TbUserMapper;
import com.eale.auth.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, User> implements TbUserService {

}
