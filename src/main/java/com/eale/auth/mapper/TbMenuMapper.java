package com.eale.auth.mapper;

import com.eale.auth.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
@Component
public interface TbMenuMapper extends BaseMapper<Menu> {


    int selectAuthByUserIdAndMenuCode(Long userId, String menuCode);
}
