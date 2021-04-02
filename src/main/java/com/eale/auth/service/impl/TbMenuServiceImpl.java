package com.eale.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.eale.auth.Vo.MenuVo;
import com.eale.auth.entity.Menu;
import com.eale.auth.entity.RoleMenu;
import com.eale.auth.entity.UserRole;
import com.eale.auth.mapper.TbMenuMapper;
import com.eale.auth.service.TbMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eale.auth.service.TbRoleMenuService;
import com.eale.auth.service.TbUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author eale
 * @since 2020-07-21
 */
@Service
public class TbMenuServiceImpl extends ServiceImpl<TbMenuMapper, Menu> implements TbMenuService {


    @Autowired
    TbUserRoleService userRoleService;

    @Autowired
    TbRoleMenuService roleMenuService;

    @Override
    public void addMenu(Menu menu) {
        // 如果插入的当前节点为根节点，则parentId为0
        if (menu.getParentId() == 0) {
            menu.setLevel(1);
            menu.setPath(null);
        }else {
            Menu parentMenu = baseMapper.selectById(menu.getParentId());
            if (parentMenu == null) {
                throw new RuntimeException("没有找到父节点");
            }
            menu.setLevel(parentMenu.getLevel()+1);
            if (StringUtils.isNoneEmpty(parentMenu.getPath())){
                menu.setPath(parentMenu.getPath()+","+menu.getId());
            }else {
                menu.setPath(parentMenu.getParentId().toString());
            }

        }
    }

    @Override
    public List<MenuVo> queryMenuTree() {
        Wrapper queryObj = new QueryWrapper<>().orderByAsc("level","sort");
        List<Menu> allMenu = super.list(queryObj);
        // 0L：表示根节点的父ID
        return transferMenuVo(allMenu, 0L);
    }

    @Override
    public List<MenuVo> queryMenus(Long userId) {
        //1、先查询当前用户对应的角色
        Wrapper queryUserRoleObj = new QueryWrapper<>().eq("user_id", userId);
        List<UserRole> userRoles = userRoleService.list(queryUserRoleObj);
        if(!CollectionUtils.isEmpty(userRoles)){
            //2、通过角色查询菜单（默认取第一个角色）
            Wrapper queryRoleMenuObj = new QueryWrapper<>().eq("role_id", userRoles.get(0).getRoleId());
            List<RoleMenu> roleMenus = roleMenuService.list(queryRoleMenuObj);


            if(!CollectionUtils.isEmpty(roleMenus)){
                Set<Long> menuIds = new HashSet<>();
                for (RoleMenu roleMenu : roleMenus) {
                    menuIds.add(roleMenu.getMenuId());
                }
                //查询对应的菜单
                Wrapper queryMenuObj = new QueryWrapper<>().in("id", new ArrayList<>(menuIds));
                List<Menu> menus = super.list(queryMenuObj);
                if(!CollectionUtils.isEmpty(menus)){
                    //将菜单下对应的父节点也一并全部查询出来
                    Set<Long> allMenuIds = new HashSet<>();
                    for (Menu menu : menus) {
                        allMenuIds.add(menu.getId());
                        if(StringUtils.isNotEmpty(menu.getPath())){
                            String[] pathIds = StringUtils.split(",", menu.getPath());
                            for (String pathId : pathIds) {
                                allMenuIds.add(Long.valueOf(pathId));
                            }
                        }
                    }
                    //3、查询对应的所有菜单,并进行封装展示
                    List<Menu> allMenus = super.list(new QueryWrapper<Menu>().in("id", new ArrayList<>(allMenuIds)));
                    List<MenuVo> resultList = transferMenuVo(allMenus, 0L);
                    return resultList;
                }
            }

        }
        return null;
    }

    /**
     * 封装菜单视图
     * @param allMenu
     * @param parentId
     * @return
     */
    private List<MenuVo> transferMenuVo(List<Menu> allMenu, Long parentId){
        List<MenuVo> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(allMenu)){
            for (Menu source : allMenu) {
                if(parentId.equals(source.getParentId())){
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(source, menuVo);
                    //递归查询子菜单，并封装信息
                    List<MenuVo> childList = transferMenuVo(allMenu, source.getId());
                    if(!CollectionUtils.isEmpty(childList)){
                        menuVo.setChildMenu(childList);
                    }
                    resultList.add(menuVo);
                }
            }
        }
        return resultList;
    }
}
