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
 * Servlet implementation class ServletRestaurantDashboard
 */
@WebServlet({ "/ServletRestaurantDashboard", "/sRestaurantDashboard" })
public class ServletRestaurantDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRestaurantDashboard() {
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
		session.setAttribute("restaurants", RestaurantBean.getAllBeans());
		try {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/jRestaurantDashboard");
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
		String name = request.getParameter("name");
		System.out.println("Creating restaurant: " + name);
		RestaurantBean r = new RestaurantBean(name, request.getParameter("cif"), request.getParameter("address"),
				request.getParameter("city"), request.getParameter("postalcode"), request.getParameter("state"),
				request.getParameter("country"), request.getParameter("email"), request.getParameter("phone"),
				request.getParameter("account"));
		r.save();
		doGet(request, response);
	}

}
