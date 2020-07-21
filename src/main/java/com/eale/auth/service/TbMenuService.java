package com.eale.auth.service;

import com.eale.auth.Vo.MenuVo;
import com.eale.auth.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
public interface TbMenuService extends IService<Menu> {


    void addMenu(Menu menu);

    List<MenuVo> queryMenuTree();

    List<MenuVo> queryMenus(Long userId);


}
