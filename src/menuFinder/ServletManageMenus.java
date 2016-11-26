package menuFinder;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.RestaurantBean;
import database.MenuBean;

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
		}
	}
	
	private void addMenu(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("Creating menu: " + name);
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		Double score = new Double(request.getParameter("score"));
		MenuBean r = new MenuBean(restaurant, name, description, price, score);
		r.save();
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
		ResultSet m;
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
		Double score = new Double(request.getParameter("score"));
		MenuBean r = new MenuBean(menuid, restaurant, name, description, price, score);
		r.save();
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
		r.delete();
		showRestaurantMenus(request, response, restaurant);
	}
}
