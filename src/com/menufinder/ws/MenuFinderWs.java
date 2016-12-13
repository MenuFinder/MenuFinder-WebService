package com.menufinder.ws;

import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.ApplicationPath;
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
import database.MenuBean;
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public AccountBean getValidLogin(@FormParam(value = "id") String id, @FormParam(value = "password")String password) {
		// TODO Auto-generated method stub
		return null;
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
		return MenuBean.getMenuById(menuId);
	}
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addMenu")
	public String addNewMenu(MenuBean menu){		
 
		try {
			MenuBean entity = new MenuBean(menu.getRestaurant(), menu.getName(), menu.getDescription(),menu. getPrice(), 0);
			entity.save();
 
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
			MenuBean entity = new MenuBean(menu.getId(),menu.getRestaurant(), menu.getName(), menu.getDescription(),menu.getPrice(), menu.getScore());
			entity.save();
 
			return "Menu  "+menu.getId() +"  updated successfully!";
 
		} catch (Exception e) {
 
			return "Error trying to update the  Menu " + e.getMessage();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("restaurant/{id}")
	public RestaurantBean getRestaurantById(@PathParam("id") long restaurantId) {
		return RestaurantBean.getRestaurantById(restaurantId);
	}

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addRestaurant")
	public String addNewRestaurant(RestaurantBean restaurant) {
		try {			
			restaurant.save();
			return "Restaurant added successfully!";
		} catch (Exception e) {
			return "Error trying to add the new Restaurant " + e.getMessage();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAllRestaurants")
	public List<RestaurantBean> getRestaurantes() {
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
			restaurant.save();

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
		return ReviewBean.getReviewById(reviewId);
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
		review.save();
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
		review.delete();
		return "Review: "+ reviewId + " deleted successfully!";
	}

	@Override
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addReview")
	public String addReview(ReviewBean review) {
		review.save();
		return "Review added successfully!";
	}
}
