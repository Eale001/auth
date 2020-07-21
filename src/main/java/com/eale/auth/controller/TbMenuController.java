package com.eale.auth.controller;


import com.eale.auth.Vo.MenuVo;
import com.eale.auth.service.TbMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
@Controller
@RequestMapping("/tb-menu")
public class TbMenuController {

    @Autowired
    TbMenuService menuService;


    @PostMapping(value = "/queryMenuTree")
    public List<MenuVo> queryTreeMenu(){
        return menuService.queryMenuTree();
    }

    @PostMapping(value = "/queryMenus")
    public List<MenuVo> queryMenus(Long userId){
        //查询当前用户下的菜单权限
        return menuService.queryMenus(userId);
    }

}
