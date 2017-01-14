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
import useful.MD5;

/**
 * Servlet implementation class ServletManageAccount
 */
@WebServlet({ "/ServletManageAccount", "/sManageAccount" })
public class ServletManageAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletManageAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action").equalsIgnoreCase("addaccount")) {
			showAddAccountDialog(request, response);
		} else if (request.getParameter("action").equalsIgnoreCase("logout")) {
			logout(request, response);
		} else if (request.getParameter("action").equalsIgnoreCase("login")) {
			showLoginDialog(request, response);
		}
	}
	
	private void showAddAccountDialog(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jAddAccount");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void showLoginDialog(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jLogin");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action").equalsIgnoreCase("login")) {
			login(request, response);
		} else if (request.getParameter("action").equalsIgnoreCase("addaccount")) {
			addaccount(request, response);
		} 
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		//ServletManageRestaurant list = new ServletManageRestaurant();
		try{
			String accountId = request.getParameter("accountid");
			String password = MD5.md5(request.getParameter("password"));
			AccountBean loggedAccount = AccountBean.getValidLogin(accountId, password);
			if(loggedAccount != null && loggedAccount.getType().equals("restaurant")){
				HttpSession session = request.getSession();
				session.setAttribute("loggedUser", loggedAccount);
				System.out.println("Looged: " + session.getAttribute("loggedUser"));
				showAccountRestaurants(request, response);
			}
			else
				response.sendRedirect("index.html");
				//System.out.println("Invalid password");
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			 session.invalidate();
			response.sendRedirect("index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addaccount(HttpServletRequest request, HttpServletResponse response) {
		String accountid = request.getParameter("accountid");
		System.out.println("Creating account: " + accountid);
		String password = MD5.md5(request.getParameter("password"));
		String type = request.getParameter("type");
		AccountBean a = new AccountBean(accountid, password, type);
		try {
			a.insert();
		} catch (SQLException e) {
			System.err.println("Error creating account!");
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("loggedUser", a);
		showAccountRestaurants(request, response);
	}

	private void showAccountRestaurants(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		AccountBean loggedAccount = (AccountBean)request.getSession().getAttribute("loggedUser");
		session.setAttribute("restaurants", RestaurantBean.getRestaurantsOfAccount(loggedAccount.getId()));
		session.setAttribute("accountid", loggedAccount.getId());
		
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jManageRestaurants");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
