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

		HttpSession session = request.getSession(true);
		try {
			ServletContext context = getServletContext();
			RequestDispatcher rd;
			if (request.getParameter("action").equalsIgnoreCase("addmenu")) {
				rd = context.getRequestDispatcher("/jAddMenu");
			} else {
				session.setAttribute("menus", MenuBean.getMenusOfRestaurant(Long.parseLong(request.getParameter("restaurantid"))));
				rd = context.getRequestDispatcher("/jManageMenus");
			}
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
		// TODO Auto-generated method stub
		// doGet(request, response);

		String name = request.getParameter("name");
		System.out.println("Creating menu: " + name);
		long restaurant = new Long(request.getParameter("restaurant"));
		Double price = new Double(request.getParameter("price"));
		Double score = new Double(request.getParameter("score"));
		MenuBean r = new MenuBean(restaurant, name, request.getParameter("description"), price, score);
		r.save();
		doGet(request, response);
	}

}
