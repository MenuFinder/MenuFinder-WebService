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
}
