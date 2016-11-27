package menuFinder;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			showAddRestaurantDialog(request, response, request.getParameter("accountid"));
		} else if (request.getParameter("action").equalsIgnoreCase("editrestaurant")) {
			showEditRestaurantDialog(request, response, Long.parseLong(request.getParameter("restaurantid")));
		} else {
			showAccountRestaurants(request, response, request.getParameter("accountid"));
		}
	}

	private void showAddRestaurantDialog(HttpServletRequest request, HttpServletResponse response, String accountId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("accountid", accountId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jAddRestaurant");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showEditRestaurantDialog(HttpServletRequest request, HttpServletResponse response, long restaurantId) {
		// TODO Auto-generated method stub

	}

	private void showAccountRestaurants(HttpServletRequest request, HttpServletResponse response, String accountId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("restaurants", RestaurantBean.getRestaurantsOfAccount(accountId));
		session.setAttribute("accountid", accountId);
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
		r.save();
		showAccountRestaurants(request, response, request.getParameter("accountid"));
	}

	private void editRestaurant(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
