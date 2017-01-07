package com.menufinder.ws;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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



@Path("/menufinderws")
public class MenuFinderWs implements IMenuFinderWS {

	//@Inject
	//private EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus() {
		return Response.ok(
				"{\"status\":\"MenuFinder Service is running...\"}").build();
	}

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("login")
	public AccountBean getValidLogin(AccountBean account) {
		AccountBean login = AccountBean.getValidLogin(account.getId(), account.getPassword());
		return login != null ? login : new AccountBean();
	}

	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/register")
	public String registerAccount(AccountBean account) {
		try {
			account.insert();
			return "Account created successfully!";
		} catch (Exception e) {
			return "Error trying to create the new account " + e.getMessage();
		}
	}

	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateAccountToken")
	public String updateAccountToken(AccountBean account) {
		try {
			AccountBean a = AccountBean.getAccountById(account.getId());
			a.setToken(account.getToken());
			a.update();
			return "Account "+ account.getId() +" token updated successfully!";
		} catch (Exception e) {
			return "Error trying to update the token of account: " + e.getMessage();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("restaurantMenus/{id}")
	public List<MenuBean> getMenusByRestaurantId(@PathParam("id") long restaurantId) {
		return MenuBean.getMenusOfRestaurant(restaurantId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getMenu/{id}")
	public MenuBean getMenuById(@PathParam("id") long menuId) {
		try {
			return MenuBean.getMenuById(menuId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new MenuBean();
		}
	}
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addMenu")
	public String addMenu(MenuBean menu){
		try {
			menu.insert();
			return "Menu added successfully!";
		} catch (Exception e) {
			return "Error trying to add the new Menu " + e.getMessage();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getMenus")
	public List<MenuBean> getMenus() {		 
		return MenuBean.getAllBeans(); 		
	}

	@DELETE	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteMenu/{id}")
	public String deleteMenu(@PathParam("id") long id) {
		try {
			MenuBean entity = MenuBean.getMenuById(id);
			entity.delete();
						
			return "Menu "+id +" deleted  successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to delete the Menu " + e.getMessage();
		}
	}

	@PUT	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateMenu")
	public String updateMenu(MenuBean menu) {
		try {
			menu.update();
			return "Menu  "+menu.getId() +"  updated successfully!";
		} catch (Exception e) {
			return "Error trying to update the  Menu " + e.getMessage();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("restaurant/{id}")
	public RestaurantBean getRestaurantById(@PathParam("id") long restaurantId) {
		try {
			return RestaurantBean.getRestaurantById(restaurantId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new RestaurantBean();
		}
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/restaurantsOfAccount/{id}")
	public List<RestaurantBean> getRestaurantsOfAccount(@PathParam("id") String accountId) {
		return RestaurantBean.getRestaurantsOfAccount(accountId);
	}

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addRestaurant")
	public String addRestaurant(RestaurantBean restaurant) {
		try {			
			restaurant.insert();
			return "Restaurant added successfully!";
		} catch (Exception e) {
			return "Error trying to add the new Restaurant " + e.getMessage();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAllRestaurants")
	public List<RestaurantBean> getRestaurants() {
		return RestaurantBean.getAllBeans();
	}

	@DELETE	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteRestaurant/{id}")
	public String deleteRestaurant(@PathParam("id") long restaurantId) {
		try {
			RestaurantBean entity = RestaurantBean.getRestaurantById(restaurantId);
			entity.delete();
						
			return "Restaurant  "+restaurantId +" deleted successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to delete the Restaurant " + e.getMessage();
		}
	}

	@PUT	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateRestaurant")
	public String updateRestaurant(RestaurantBean restaurant) {
		try {
			restaurant.update();

			return "Restaurant  "+restaurant.getId() +"  updated successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to update the  Restaurant " + e.getMessage();
		}
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/review/{id}")
	public ReviewBean getReviewById(@PathParam("id")long reviewId) {
		try {
			return ReviewBean.getReviewById(reviewId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReviewBean();
		}
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/itemReviews/{id}")
	public List<ReviewBean> getReviewsOfItem(@PathParam("id")long itemId) {
		return ReviewItemBean.getReviewsOfItem(itemId);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/menuReviews/{id}")
	public List<ReviewBean> getReviewsOfMenu(@PathParam("id")long menuId) {
		return ReviewMenuBean.getReviewsOfMenu(menuId);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/restaurantReviews/{id}")
	public List<ReviewBean> getReviewsOfRestaurant(@PathParam("id")long restaurantId) {
		return ReviewRestaurantBean.getReviewsOfRestaurant(restaurantId);
	}

	@Override
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateReview")
	public String updateReview(ReviewBean review) {
		try {
			review.update();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error updating review: "+ review.getId();
		}
		return "Review: "+ review.getId() + " updated successfully!";
	}

	@Override
	@DELETE
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteReview/{id}")
	public String deleteReview(@PathParam("id")long reviewId) {
		ReviewBean review = new ReviewBean();
		review.setId(reviewId);
		try {
			review.delete();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error deleting review: "+ reviewId;
		}
		return "Review: "+ reviewId + " deleted successfully!";
	}

	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addReview")
	public String addReview(ReviewBean review) {
		try {
			review.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding review!";
		}
		return "Review added successfully!";
	}

	@Override
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateItem")
	public String updateItem(ItemBean item) {
		try {
			item.update();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error updating item: "+ item.getId();
		}
		return "Item: "+ item.getId() + " updated successfully!";
	}

	@Override
	@DELETE
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteItem/{id}")
	public String deleteItem(@PathParam("id")long itemId) {
		ItemBean item = new ItemBean();
		item.setId(itemId);
		try {
			item.delete();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error deleting item: "+ itemId;
		}
		return "Item: "+ itemId + " deleted successfully!";
	}

	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addItem")
	public String addItem(ItemBean item) {
		try {
			item.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding item!";
		}
		return "Item added successfully!";
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/item/{id}")
	public ItemBean getItemById(@PathParam("id")long itemId) {
		try {
			return ItemBean.getItemById(itemId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ItemBean();
		}
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/restaurantItems/{id}")
	public List<ItemBean> getRestaurantItems(@PathParam("id")long restaurantId) {
		return ItemBean.getRestaurantItems(restaurantId);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/menuItemsByCategory/{id}")
	public Map<Long, List<ItemBean>> getMenuItemsByCategory(@PathParam("id")long menuId) {
		return ItemBean.getMenuItemsByCategory(menuId);
	}
	
	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addMenuItem")
	public String addMenuItem(MenuItemBean menuItem) {
		try {
			menuItem.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding item to menu!";
		}
		return "Item added successfully to the menu!";
	}

	@Override
	@DELETE	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteMenuItem")
	public String deleteMenuItem(MenuItemBean menuItem) {
		try {
			menuItem.delete();
						
			return "Item  "+ menuItem.getItem() +" deleted successfully from menu " + menuItem.getMenu() + " !";
 
		} catch (Exception e) {
 
			return "Error trying to delete the Item from Menu " + e.getMessage();
		}
	}

	
	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addItemCategory")
	public String addItemCategory(ItemCategoryBean itemCategory) {
		try {
			itemCategory.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding item category!";
		}
		return "Category added successfully!";
	}

	@Override
	@DELETE	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteItemCategory/{id}")
	public String deleteItemCategory(@PathParam("id") long id) {
		try {
			ItemCategoryBean entity = ItemCategoryBean.getItemCategoryById(id);
			entity.delete();
						
			return "Category  "+id +" deleted successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to delete the Restaurant " + e.getMessage();
		}
	}

	@Override
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateItemCategory")
	public String updateItemCategory(ItemCategoryBean itemCategory) {
		try {
			itemCategory.update();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error updating category: "+ itemCategory.getId();
		}
		return "Category: "+ itemCategory.getId() + " updated successfully!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("itemCategory/{id}")
	public ItemCategoryBean getItemCategoryById(@PathParam("id") long id) {
		try {
			return ItemCategoryBean.getItemCategoryById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ItemCategoryBean();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("itemCategories")
	public List<ItemCategoryBean> getItemCategories() {		 
		return ItemCategoryBean.getAllBeans(); 		
	}

	@Override
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateItemRating")
	public String updateItemRating(ItemRatingBean itemRating) {
		try {
			itemRating.update();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error updating rating: "+ itemRating.getId();
		}
		return "Rating: "+ itemRating.getId() + " updated successfully!";
	}

	@Override
	@DELETE	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteItemRating")
	public String deleteItemRating(ItemRatingBean itemRating) {
		try {
			itemRating.delete();
						
			return "Rating  "+itemRating.getId() +" deleted successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to delete the rating " + e.getMessage();
		}
	}

	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addItemRating")
	public String addItemRating(ItemRatingBean itemRating) {
		try {
			itemRating.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding item category!";
		}
		return "Rating added successfully!";
	}

	@Override
	@DELETE	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteAccountSubscription")
	public String deleteAccountSubscription(AccountSubscriptionBean accountSubscription) {
		try {
			accountSubscription.delete();
						
			return "Feed of account "+ accountSubscription.getAccount() +" deleted successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to delete the feed " + e.getMessage();
		}
	}

	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addAccountSubscription")
	public String addAccountSubscription(AccountSubscriptionBean accountSubscription) {
		try {
			accountSubscription.insert();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding account subscription!";
		}
		return "Feed added successfully!";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("itemRating/{id}")
	public List<ItemRatingBean> getRatingsOfItem(@PathParam("id") long itemId) {
		return ItemRatingBean.getRatingsOfItem(itemId);
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("itemRatingItem/{id}")
	public String getItemRatingOfItem(@PathParam("id") long itemId) {
		return String.valueOf(ItemRatingBean.getItemRatingOfItem(itemId));
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("subscribedRestaurantsOfAccount/{id}")
	public List<RestaurantBean> getSubscribedRestaurantsOfAccount(@PathParam("id") String accountId) {
		return RestaurantBean.getSubscribedRestaurantsOfAccount(accountId);
	}

}
