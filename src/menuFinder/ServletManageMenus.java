package menuFinder;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ItemBean;
import database.MenuBean;
import database.MenuItemBean;

/**
 * Servlet implementation class ServletManageMenus
 */
@WebServlet({ "/ServletManageMenus", "/sManageMenus" })
public class ServletManageMenus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletManageMenus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("addmenu")) {
			showAddMenuDialog(request, response, Long.parseLong(request.getParameter("restaurantid")));
		} else if(request.getParameter("action").equalsIgnoreCase("editmenu")){ 
			showEditMenuDialog(request, response, Long.parseLong(request.getParameter("menuid")));
		} else if (request.getParameter("action").equalsIgnoreCase("managemenuitems")) {
			showManageMenuItemsDialog(request, response, Long.parseLong(request.getParameter("menuid")));
		} else {
			showRestaurantMenus(request, response, Long.parseLong(request.getParameter("restaurantid")));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("addmenu")) {
			addMenu(request, response);
		} else if(request.getParameter("action").equalsIgnoreCase("editmenu")){
			System.out.println("menuid " + request.getParameter("menuid"));
			editMenu(request, response);
		} else if(request.getParameter("action").equalsIgnoreCase("deletemenu")){
			deleteMenu(request, response);
		} else if(request.getParameter("action").equalsIgnoreCase("deletemenuitem")) {
			deleteMenuItem(request, response);
		} else if(request.getParameter("action").equalsIgnoreCase("addmenuitem")) {
			addMenuItem(request, response);
		}
	}
	
	private void addMenu(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("Creating menu: " + name);
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		MenuBean r = new MenuBean(restaurant, name, description, price, 0);
		try {
			r.save();
		} catch (SQLException e) {
			System.err.println("Error adding menu!");
			e.printStackTrace();
		}
		showRestaurantMenus(request, response, restaurant);
	}

	private void showRestaurantMenus(HttpServletRequest request, HttpServletResponse response, long restaurantId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("menus", MenuBean.getMenusOfRestaurant(restaurantId));
			session.setAttribute("restaurantid", restaurantId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jManageMenus");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showAddMenuDialog(HttpServletRequest request, HttpServletResponse response, long restaurantId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("restaurantid", restaurantId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jAddMenu");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void showEditMenuDialog(HttpServletRequest request, HttpServletResponse response, long menuId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("menuid", menuId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jEditMenu");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void editMenu(HttpServletRequest request, HttpServletResponse response) {
		long menuid = Long.parseLong(request.getParameter("menuid"));
		String name = request.getParameter("name");
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		try {
			MenuBean r = MenuBean.getMenuById(menuid);
			r.setId(menuid);
			r.setName(name);
			r.setDescription(description);
			r.setPrice(price);
			r.save();
		} catch (SQLException e) {
			System.err.println("Error updating menu!");
			e.printStackTrace();
		}
		showRestaurantMenus(request, response, restaurant);
	}
	
	private void deleteMenu(HttpServletRequest request, HttpServletResponse response) {
		long menuid = Long.parseLong(request.getParameter("menuid"));
		System.out.println("Borrando menu: " +menuid );
		String name = request.getParameter("name");
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		Double score = new Double(request.getParameter("score"));
		MenuBean r = new MenuBean(menuid,restaurant, name, description, price, score);
		try {
			r.delete();
		} catch (SQLException e) {
			System.err.println("Error deleting menu!");
			e.printStackTrace();
		}
		showRestaurantMenus(request, response, restaurant);
	}

	private void showManageMenuItemsDialog(HttpServletRequest request, HttpServletResponse response, long menuId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("menuid", menuId);
			session.setAttribute("menuitems", ItemBean.getMenuItemsByCategory(menuId));
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jManageMenuItems");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response) {
		long itemId =Long.parseLong(request.getParameter("itemid"));
		long menuId = Long.parseLong(request.getParameter("menuid"));
		long categoryId = Long.parseLong(request.getParameter("categoryid"));
		System.out.println("Deleting item '" + itemId + "' of menu '" + menuId + "'." );
		MenuItemBean mi = new MenuItemBean(menuId, itemId, categoryId);
		try {
			mi.delete();
		} catch (SQLException e) {
			System.err.println("Error deleting menu item!");
			e.printStackTrace();
		}
		showManageMenuItemsDialog(request, response, menuId);
	}

	private void addMenuItem(HttpServletRequest request, HttpServletResponse response) {
		long itemId =Long.parseLong(request.getParameter("itemid"));
		long menuId = Long.parseLong(request.getParameter("menuid"));
		long categoryId = Long.parseLong(request.getParameter("categoryid"));
		System.out.println("Adding item '" + itemId + "' to menu '" + menuId + "'." );
		MenuItemBean mi = new MenuItemBean(menuId, itemId, categoryId);
		try {
			mi.save();
		} catch (SQLException e) {
			System.err.println("Error adding item to menu!");
			e.printStackTrace();
		}
		showManageMenuItemsDialog(request, response, menuId);
	}
}
