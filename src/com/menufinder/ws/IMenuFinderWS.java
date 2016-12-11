package com.menufinder.ws;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import database.AccountBean;
import database.MenuBean;
import database.RestaurantBean;



public interface IMenuFinderWS {
	public AccountBean getValidLogin(@FormParam(value = "id")String id, @FormParam(value = "password")String password);
	public List<MenuBean> getMenusByRestaurantId(@PathParam("id")long restaurantId);
	public String addNewMenu(MenuBean menu);
	public List<MenuBean> getMenus();
	public String deleteMenu(@PathParam("id")long menuId);
	public String updateMenu(MenuBean menu);
	
	public RestaurantBean getRestaurantById(@PathParam("id")long restaurantId);
	public String addNewRestaurant(RestaurantBean restaurant);
	public List<RestaurantBean> getRestaurantes();
	public String deleteRestaurant(@PathParam("id") long restaurantId);
	public String updateRestaurant(RestaurantBean restaurant);
	
}
