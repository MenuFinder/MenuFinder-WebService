package com.menufinder.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import database.AccountBean;
import database.ItemBean;
import database.MenuBean;
import database.RestaurantBean;
import database.ReviewBean;



public interface IMenuFinderWS {
	public AccountBean getValidLogin(@FormParam(value = "id")String id, @FormParam(value = "password")String password);
	public List<MenuBean> getMenusByRestaurantId(@PathParam("id")long restaurantId);
	public MenuBean getMenuById(@PathParam("id") long menuId);
	public String addNewMenu(MenuBean menu);
	public List<MenuBean> getMenus();
	public String deleteMenu(@PathParam("id")long menuId);
	public String updateMenu(MenuBean menu);
	
	public RestaurantBean getRestaurantById(@PathParam("id")long restaurantId);
	public String addNewRestaurant(RestaurantBean restaurant);
	public List<RestaurantBean> getRestaurantes();
	public String deleteRestaurant(@PathParam("id") long restaurantId);
	public String updateRestaurant(RestaurantBean restaurant);

	public ReviewBean getReviewById(@PathParam("id")long reviewId);
	public List<ReviewBean> getReviewsOfItem(@PathParam("id")long itemId);
	public List<ReviewBean> getReviewsOfMenu(@PathParam("id")long menuId);
	public List<ReviewBean> getReviewsOfRestaurant(@PathParam("id")long restaurantId);
	public String updateReview(ReviewBean review);
	public String deleteReview(@PathParam("id")long reviewId);
	public String addReview(ReviewBean review);

	public String updateItem(ItemBean item);
	public String deleteItem(@PathParam("id")long itemId);
	public String addItem(ItemBean item);
	public ItemBean getItemById(@PathParam("id")long itemId);
	public List<ItemBean> getRestaurantItems(@PathParam("id")long restaurantId);
	public Map<Long, List<ItemBean>> getMenuItemsByCategory(@PathParam("id")long menuId);
	
}
