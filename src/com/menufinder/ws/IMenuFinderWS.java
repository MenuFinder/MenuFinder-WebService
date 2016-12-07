package com.menufinder.ws;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import database.AccountBean;
import database.MenuBean;



public interface IMenuFinderWS {
	public AccountBean getValidLogin(@FormParam(value = "id")String id, @FormParam(value = "password")String password);
	public List<MenuBean> getMenusByRestaurantId(@PathParam("id")long restaurantId);
	public String addNewMenu(MenuBean menu);
	public List<MenuBean> getMenus();
	public String deleteMenu(MenuBean menu);
	public String updateMenu(MenuBean menu);
}
