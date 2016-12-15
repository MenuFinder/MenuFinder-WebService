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

import database.AccountBean;
import database.RestaurantBean;

/**
 * Servlet implementation class ServletManageRestaurants
 */
@WebServlet({ "/ServletManageRestaurants", "/sManageRestaurants" })
public class ServletManageRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletManageRestaurants() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("addrestaurant")) {
			showAddRestaurantDialog(request, response);
		} else if (request.getParameter("action").equalsIgnoreCase("editrestaurant")) {
			showEditRestaurantDialog(request, response, Long.parseLong(request.getParameter("restaurantid")));
		} else {
			showAccountRestaurants(request, response);
		}
	}

	private void showAddRestaurantDialog(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);
			AccountBean loggedAccount = (AccountBean)request.getSession().getAttribute("loggedUser");
			session.setAttribute("accountid", loggedAccount.getId());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jAddRestaurant");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showEditRestaurantDialog(HttpServletRequest request, HttpServletResponse response, long restaurantId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("restaurant", RestaurantBean.getRestaurantById(restaurantId));
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jEditRestaurant");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showAccountRestaurants(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		AccountBean loggedAccount = (AccountBean)request.getSession().getAttribute("loggedUser");
		session.setAttribute("accountid", loggedAccount.getId());
		session.setAttribute("restaurants", RestaurantBean.getRestaurantsOfAccount(loggedAccount.getId()));
		session.setAttribute("accountid", loggedAccount.getId());
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jManageRestaurants");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("addrestaurant")) {
			addRestaurant(request, response);
		} else if (request.getParameter("action").equalsIgnoreCase("editrestaurant")) {
			editRestaurant(request, response);
		} else if (request.getParameter("action").equalsIgnoreCase("deleterestaurant")) {
			deleteRestaurant(request, response);
		}
	}

	private void addRestaurant(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("Creating restaurant: " + name);
		RestaurantBean r = new RestaurantBean(name, request.getParameter("cif"), request.getParameter("address"),
				request.getParameter("city"), request.getParameter("postalcode"), request.getParameter("state"),
				request.getParameter("country"), request.getParameter("email"), request.getParameter("phone"),
				request.getParameter("accountid"));
		try {
			r.insert();
		} catch (SQLException e) {
			System.err.println("Error adding restaurant!");
			e.printStackTrace();
		}
		showAccountRestaurants(request, response);
	}

	private void editRestaurant(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("Updating restaurant: " + name);
		RestaurantBean r = new RestaurantBean(Long.parseLong(request.getParameter("id")), name,
				request.getParameter("cif"), request.getParameter("address"), request.getParameter("city"),
				request.getParameter("postalcode"), request.getParameter("state"), request.getParameter("country"),
				request.getParameter("email"), request.getParameter("phone"), request.getParameter("accountid"));
		try {
			r.update();
		} catch (SQLException e) {
			System.err.println("Error updating restuarant!");
			e.printStackTrace();
		}
		showAccountRestaurants(request, response);
	}

	private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response) {
		long restaurantId = Long.parseLong(request.getParameter("restaurantid"));
		System.out.println("Deleting restaurant: " + restaurantId);
		RestaurantBean r = new RestaurantBean();
		r.setId(restaurantId);
		try {
			r.delete();
		} catch (SQLException e) {
			System.err.println("Error deleting restaurant!");
			e.printStackTrace();
		}
		showAccountRestaurants(request, response);
	}

}
