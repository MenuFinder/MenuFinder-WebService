package com.menufinder.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

import database.AccountBean;
import database.AccountSubscriptionBean;
import database.ItemBean;
import database.ItemCategoryBean;
import database.ItemRatingBean;
import database.MenuBean;
import database.MenuItemBean;
import database.RestaurantBean;
import database.ReviewBean;



public interface IMenuFinderWS {
	public AccountBean getValidLogin(AccountBean account);
	public String registerAccount(AccountBean account);
	public List<MenuBean> getMenusByRestaurantId(@PathParam("id")long restaurantId);
	public MenuBean getMenuById(@PathParam("id") long menuId);
	public String addMenu(MenuBean menu);
	public List<MenuBean> getMenus();
	public String deleteMenu(@PathParam("id")long menuId);
	public String updateMenu(MenuBean menu);
	
	public RestaurantBean getRestaurantById(@PathParam("id")long restaurantId);
	public String addRestaurant(RestaurantBean restaurant);
	public List<RestaurantBean> getRestaurants();
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
	
	public String deleteMenuItem(MenuItemBean menuItem);
	public String addMenuItem(MenuItemBean menuItem);
	
	public ItemCategoryBean getItemCategoryById(@PathParam("id")long id);
	public String updateItemCategory(ItemCategoryBean itemCategory);
	public String deleteItemCategory(@PathParam("id")long id);
	public String addItemCategory(ItemCategoryBean itemCategory);
	public List<ItemCategoryBean> getItemCategories();
	
	public String updateItemRating(ItemRatingBean itemRating);
	public String deleteItemRating(ItemRatingBean itemRating);
	public String addItemRating(ItemRatingBean itemRating);
	public List<ItemRatingBean> getRatingsOfItem(@PathParam("id")long itemId);
	public String getItemRatingOfItem(@PathParam("id")long itemId);
	
	public String deleteAccountSubscription(AccountSubscriptionBean accountSubscription);
	public String addAccountSubscription(AccountSubscriptionBean accountSubscription);
	public List<RestaurantBean> getSubscribedRestaurantsOfAccount(@PathParam("id") String accountId);
	
}
