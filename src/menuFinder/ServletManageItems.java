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

/**
 * Servlet implementation class ServletManageItems
 */
@WebServlet({ "/ServletManageItems", "/sManageItems" })
public class ServletManageItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletManageItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("additem")) {
			showAddItemDialog(request, response, Long.parseLong(request.getParameter("restaurantid")));
		} else if(request.getParameter("action").equalsIgnoreCase("edititem")){ 
			showEditItemDialog(request, response, Long.parseLong(request.getParameter("itemid")));
		} else {
			showRestaurantItems(request, response, Long.parseLong(request.getParameter("restaurantid")));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action").equalsIgnoreCase("additem")) {
			addItem(request, response);
		} else if(request.getParameter("action").equalsIgnoreCase("edititem")){
			System.out.println("itemid " + request.getParameter("itemid"));
			editItem(request, response);
		} else if(request.getParameter("action").equalsIgnoreCase("deleteitem")){
			deleteItem(request, response);
		}
			
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("Creating item: " + name);
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		ItemBean i = new ItemBean(name, description, price, 0, restaurant);
		try {
			i.save();
		} catch (SQLException e) {
			System.err.println("Error creating item!");
			e.printStackTrace();
		}
		showRestaurantItems(request, response, restaurant);
	}

	private void showRestaurantItems(HttpServletRequest request, HttpServletResponse response, long restaurantId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("items", ItemBean.getRestaurantItems(restaurantId));
			session.setAttribute("restaurantid", restaurantId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jManageItems");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showAddItemDialog(HttpServletRequest request, HttpServletResponse response, long restaurantId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("restaurantid", restaurantId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jAddItem");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void showEditItemDialog(HttpServletRequest request, HttpServletResponse response, long itemId) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("itemid", itemId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jEditItem");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void editItem(HttpServletRequest request, HttpServletResponse response) {
		long itemid = Long.parseLong(request.getParameter("itemid"));
		String name = request.getParameter("name");
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		ItemBean i = ItemBean.getItemById(itemid);
		i.setId(itemid);
		i.setName(name);
		i.setDescription(description);
		i.setPrice(price);
		try {
			i.save();
		} catch (SQLException e) {
			System.err.println("Error updating item!");
			e.printStackTrace();
		}
		showRestaurantItems(request, response, restaurant);
	}
	
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		long itemid = Long.parseLong(request.getParameter("itemid"));
		System.out.println("Borrando item: " +itemid );
		String name = request.getParameter("name");
		long restaurant = Long.parseLong(request.getParameter("restaurantid"));
		String description = request.getParameter("description");
		Double price = new Double(request.getParameter("price"));
		Double score = new Double(request.getParameter("score"));
		ItemBean i = new ItemBean(itemid, name, description, price, score,restaurant);
		try {
			i.delete();
		} catch (SQLException e) {
			System.err.println("Error deleting item!");
			e.printStackTrace();
		}
		showRestaurantItems(request, response, restaurant);
	}
}
