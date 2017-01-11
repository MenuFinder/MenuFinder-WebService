package com.menufinder.soap;

import java.sql.SQLException;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import database.AccountBean;
import database.AccountSubscriptionBean;
import database.ItemBean;
import database.ItemCategoryBean;
import database.ItemRatingBean;
import database.MenuBean;
import database.MenuItemBean;
import database.RestaurantBean;
import database.ReviewBean;
import database.ReviewItemBean;
import database.ReviewMenuBean;
import database.ReviewRestaurantBean;
import useful.Notifications;

@WebService()
public class MenuFinderSOAP {

	private static final Notifications notification = new Notifications();

	@WebMethod(action = "getValidLogin")
	public AccountBean getValidLogin(@WebParam(name = "account") AccountBean account) {
		AccountBean login = AccountBean.getValidLogin(account.getId(), account.getPassword());
		return login != null ? login : new AccountBean();
	}

	@WebMethod(action = "registerAccount")
	public String registerAccount(@WebParam(name = "account") AccountBean account) {
		try {
			account.insert();
			return "Account '" + account.getId() + "' created successfully!";
		} catch (Exception e) {
			return "Error trying to create the new account: " + e.getMessage();
		}
	}

	@WebMethod(action = "updateAccountToken")
	public String updateAccountToken(@WebParam(name = "account") AccountBean account) {
		try {
			AccountBean a = AccountBean.getAccountById(account.getId());
			a.setToken(account.getToken());
			a.update();
			return "Account '" + account.getId() + "' token updated successfully!";
		} catch (Exception e) {
			return "Error trying to update the token of account: " + e.getMessage();
		}
	}

	@WebMethod(action = "getMenusByRestaurantId")
	public List<MenuBean> getMenusByRestaurantId(@WebParam(name = "restaurantId") long restaurantId) {
		return MenuBean.getMenusOfRestaurant(restaurantId);
	}

	@WebMethod(action = "getMenuById")
	public MenuBean getMenuById(@WebParam(name = "menuId") long menuId) {
		try {
			return MenuBean.getMenuById(menuId);
		} catch (SQLException e) {
			return new MenuBean();
		}
	}

	@WebMethod(action = "addMenu")
	public String addMenu(@WebParam(name = "menu") MenuBean menu) {
		try {
			menu.insert();
			return "Menu '" + menu.getName() + "' added successfully!";
		} catch (Exception e) {
			return "Error trying to add new menu: " + e.getMessage();
		}
	}

	@WebMethod(action = "getMenus")
	public List<MenuBean> getMenus() {
		return MenuBean.getAllBeans();
	}

	@WebMethod(action = "deleteMenu")
	public String deleteMenu(@WebParam(name = "menuId") long menuId) {
		try {
			MenuBean menu = new MenuBean();
			menu.setId(menuId);
			menu.delete();
			return "Menu " + menuId + " deleted successfully!";
		} catch (Exception e) {
			return "Error trying to delete the menu " + menuId + e.getMessage();
		}
	}

	@WebMethod(action = "updateMenu")
	public String updateMenu(@WebParam(name = "menu") MenuBean menu) {
		try {
			menu.update();
			return "Menu '" + menu.getId() + "' updated successfully!";
		} catch (Exception e) {
			return "Error trying to update menu " + e.getMessage();
		}
	}

	@WebMethod(action = "getRestaurantById")
	public RestaurantBean getRestaurantById(@WebParam(name = "restaurantId") long restaurantId) {
		try {
			return RestaurantBean.getRestaurantById(restaurantId);
		} catch (SQLException e) {
			return new RestaurantBean();
		}
	}

	@WebMethod(action = "getRestaurantsOfAccount")
	public List<RestaurantBean> getRestaurantsOfAccount(@WebParam(name = "accountId") String accountId) {
		return RestaurantBean.getRestaurantsOfAccount(accountId);
	}

	@WebMethod(action = "addRestaurant")
	public String addRestaurant(@WebParam(name = "restaurant") RestaurantBean restaurant) {
		try {
			restaurant.insert();
			return "Restaurant '" + restaurant.getName() + "' added successfully!";
		} catch (Exception e) {
			return "Error trying to add new restaurant: " + e.getMessage();
		}
	}

	@WebMethod(action = "getRestaurants")
	public List<RestaurantBean> getRestaurants() {
		return RestaurantBean.getAllBeans();
	}

	@WebMethod(action = "deleteRestaurant")
	public String deleteRestaurant(@WebParam(name = "restaurantId") long restaurantId) {
		try {
			RestaurantBean restaurant = new RestaurantBean();
			restaurant.setId(restaurantId);
			restaurant.delete();
			return "Restaurant " + restaurantId + " deleted successfully!";
		} catch (Exception e) {
			return "Error trying to delete restaurant " + restaurantId + e.getMessage();
		}
	}

	@WebMethod(action = "updateRestaurant")
	public String updateRestaurant(@WebParam(name = "restaurant") RestaurantBean restaurant) {
		try {
			String message = "Restaurant " + restaurant.getName() + " updated!";
			restaurant.update();
			notification.sendNotification(AccountBean.getAccountById(restaurant.getAccount()), message);
			notification.sendNotificationToTopic(String.valueOf(restaurant.getId()), message);
			return message;
		} catch (Exception e) {
			return "Error trying to update restaurant: " + e.getMessage();
		}
	}

	@WebMethod(action = "getFilteredRestaurants")
	public List<RestaurantBean> getFilteredRestaurants(@WebParam(name = "filter") String filter) {
		return RestaurantBean.getFilteredRestaurants(filter);
	}

	@WebMethod(action = "getReviewById")
	public ReviewBean getReviewById(@WebParam(name = "reviewId") long reviewId) {
		try {
			return ReviewBean.getReviewById(reviewId);
		} catch (SQLException e) {
			return new ReviewBean();
		}
	}

	@WebMethod(action = "getReviewsOfItem")
	public List<ReviewBean> getReviewsOfItem(@WebParam(name = "itemId") long itemId) {
		return ReviewItemBean.getReviewsOfItem(itemId);
	}

	@WebMethod(action = "getReviewsOfMenu")
	public List<ReviewBean> getReviewsOfMenu(@WebParam(name = "menuId") long menuId) {
		return ReviewMenuBean.getReviewsOfMenu(menuId);
	}

	@WebMethod(action = "getReviewsOfRestaurant")
	public List<ReviewBean> getReviewsOfRestaurant(@WebParam(name = "restaurantId") long restaurantId) {
		return ReviewRestaurantBean.getReviewsOfRestaurant(restaurantId);
	}

	@WebMethod(action = "updateReview")
	public String updateReview(@WebParam(name = "review") ReviewBean review) {
		try {
			review.update();
		} catch (SQLException e) {
			return "Error updating review: " + e.getMessage();
		}
		return "Review: " + review.getId() + " updated successfully!";
	}

	@WebMethod(action = "deleteReview")
	public String deleteReview(@WebParam(name = "reviewId") long reviewId) {
		ReviewBean review = new ReviewBean();
		review.setId(reviewId);
		try {
			review.delete();
		} catch (SQLException e) {
			return "Error deleting review: " + reviewId + e.getMessage();
		}
		return "Review: " + reviewId + " deleted successfully!";
	}

	@WebMethod(action = "addReview")
	public String addReview(@WebParam(name = "review") ReviewBean review) {
		try {
			review.insert();
		} catch (SQLException e) {
			return "Error adding review: " + e.getMessage();
		}
		return "Review added successfully!";
	}

	@WebMethod(action = "updateItem")
	public String updateItem(@WebParam(name = "item") ItemBean item) {
		try {
			item.update();
		} catch (SQLException e) {
			return "Error updating item: " + e.getMessage();
		}
		return "Item: " + item.getId() + " updated successfully!";
	}

	@WebMethod(action = "deleteItem")
	public String deleteItem(@WebParam(name = "itemId") long itemId) {
		ItemBean item = new ItemBean();
		item.setId(itemId);
		try {
			item.delete();
		} catch (SQLException e) {
			return "Error deleting item: " + itemId + e.getMessage();
		}
		return "Item: " + itemId + " deleted successfully!";
	}

	@WebMethod(action = "addItem")
	public String addItem(@WebParam(name = "item") ItemBean item) {
		try {
			item.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding item: " + e.getMessage();
		}
		return "Item '" + item.getName() + "' added successfully!";
	}

	@WebMethod(action = "getItemById")
	public ItemBean getItemById(@WebParam(name = "itemId") long itemId) {
		try {
			return ItemBean.getItemById(itemId);
		} catch (SQLException e) {
			return new ItemBean();
		}
	}

	@WebMethod(action = "getRestaurantItems")
	public List<ItemBean> getRestaurantItems(@WebParam(name = "restaurantId") long restaurantId) {
		return ItemBean.getRestaurantItems(restaurantId);
	}

	@WebMethod(action = "deleteMenuItem")
	public String deleteMenuItem(@WebParam(name = "menuItem") MenuItemBean menuItem) {
		try {
			menuItem.delete();
			return "Item " + menuItem.getItem() + " deleted successfully from menu " + menuItem.getMenu() + " !";
		} catch (Exception e) {
			return "Error trying to delete the Item from Menu: " + e.getMessage();
		}
	}

	@WebMethod(action = "addMenuItem")
	public String addMenuItem(@WebParam(name = "menuItem") MenuItemBean menuItem) {
		try {
			menuItem.insert();
		} catch (SQLException e) {
			return "Error adding item to menu: " + e.getMessage();
		}
		return "Item " + menuItem.getItem() + " added successfully to menu " + menuItem.getMenu() + " !";
	}

	@WebMethod(action = "getItemCategoryById")
	public ItemCategoryBean getItemCategoryById(@WebParam(name = "itemCategoryId") long id) {
		try {
			return ItemCategoryBean.getItemCategoryById(id);
		} catch (SQLException e) {
			return new ItemCategoryBean();
		}
	}

	@WebMethod(action = "updateItemCategory")
	public String updateItemCategory(@WebParam(name = "itemCategory") ItemCategoryBean itemCategory) {
		try {
			itemCategory.update();
		} catch (SQLException e) {
			return "Error updating category: " + e.getMessage();
		}
		return "Category: " + itemCategory.getId() + " updated successfully!";
	}

	@WebMethod(action = "deleteItemCategory")
	public String deleteItemCategory(@WebParam(name = "itemCategoryId") long id) {
		try {
			ItemCategoryBean entity = new ItemCategoryBean();
			entity.setId(id);
			entity.delete();
			return "Category " + id + " deleted successfully!";
		} catch (Exception e) {
			return "Error trying to delete the ItemCategory " + id + e.getMessage();
		}
	}

	@WebMethod(action = "addItemCategory")
	public String addItemCategory(@WebParam(name = "itemCategory") ItemCategoryBean itemCategory) {
		try {
			itemCategory.insert();
		} catch (SQLException e) {
			return "Error adding item category: " + e.getMessage();
		}
		return "Category '" + itemCategory.getName() + "' added successfully!";
	}

	@WebMethod(action = "getItemCategories")
	public List<ItemCategoryBean> getItemCategories() {
		return ItemCategoryBean.getAllBeans();
	}

	@WebMethod(action = "updateItemRating")
	public String updateItemRating(@WebParam(name = "itemRating") ItemRatingBean itemRating) {
		try {
			itemRating.update();
		} catch (SQLException e) {
			return "Error updating rating: " + e.getMessage();
		}
		return "Rating: " + itemRating.getId() + " updated successfully!";
	}

	@WebMethod(action = "deleteItemRating")
	public String deleteItemRating(@WebParam(name = "itemRating") ItemRatingBean itemRating) {
		try {
			itemRating.delete();
			return "Rating " + itemRating.getId() + " deleted successfully!";
		} catch (Exception e) {
			return "Error trying to delete the rating: " + e.getMessage();
		}
	}

	@WebMethod(action = "addItemRating")
	public String addItemRating(@WebParam(name = "itemRating") ItemRatingBean itemRating) {
		try {
			itemRating.insert();
		} catch (SQLException e) {
			return "Error adding item category: " + e.getMessage();
		}
		return "Rating added successfully!";
	}

	@WebMethod(action = "getRatingsOfItem")
	public List<ItemRatingBean> getRatingsOfItem(@WebParam(name = "itemId") long itemId) {
		return ItemRatingBean.getRatingsOfItem(itemId);
	}

	@WebMethod(action = "getItemRatingOfItem")
	public String getItemRatingOfItem(@WebParam(name = "itemId") long itemId) {
		return String.valueOf(ItemRatingBean.getItemRatingOfItem(itemId));
	}

	@WebMethod(action = "deleteAccountSubscription")
	public String deleteAccountSubscription(
			@WebParam(name = "accountSubscription") AccountSubscriptionBean accountSubscription) {
		try {
			accountSubscription.delete();
			return "Feed of account " + accountSubscription.getAccount() + " deleted successfully!";
		} catch (Exception e) {
			return "Error trying to delete feed of account: " + e.getMessage();
		}
	}

	@WebMethod(action = "addAccountSubscription")
	public String addAccountSubscription(
			@WebParam(name = "accountSubscription") AccountSubscriptionBean accountSubscription) {
		try {
			accountSubscription.insert();
		} catch (SQLException e) {
			return "Error adding account subscription: " + e.getMessage();
		}
		return "Feed added successfully!";
	}

	@WebMethod(action = "getSubscribedRestaurantsOfAccount")
	public List<RestaurantBean> getSubscribedRestaurantsOfAccount(@WebParam(name = "accountId") String accountId) {
		return RestaurantBean.getSubscribedRestaurantsOfAccount(accountId);
	}

}
