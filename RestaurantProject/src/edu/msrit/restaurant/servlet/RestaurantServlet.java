package edu.msrit.restaurant.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.msrit.restaurant.db.RestaurantDBHelper;
import edu.msrit.restaurant.model.FoodItems;
import edu.msrit.restaurant.model.Menu;
import edu.msrit.restaurant.model.OrderItems;
import edu.msrit.restaurant.model.Restaurants;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//String action = request.getServletPath();
		 String action=(String)request.getParameter("operation");
		 if(action==null) {
			 action="";
		 }
		 System.out.println(action);
        try {
            switch (action) {
            case "search":
                filter(request, response);
                break;
            case "showOrder":
                showOrders(request, response);
                break;                
            case "placeOrder":
                createOrder(request, response);
                break;
            case "showMenu":
                showMenu(request, response);
                break;
            case "getOrders":
                getOrders(request, response);
                break;
            case "deleteOrder":
                deleteOrder(request, response);
                break;
            default:
                listRestaurants(request, response);
                break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
		
		
		
		
		
	
	public void listRestaurants(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		ArrayList<Restaurants> restlist=helper.listRestaurants();
		request.setAttribute("restlist", restlist);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("ListRestaurants.jsp");
	    dispatcher.forward(request, response);
	}
	
	
	public void filter(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		String area=(String)request.getParameter("area");
		ArrayList<Restaurants> restlist=helper.filterByArea(area);
		request.setAttribute("restlist", restlist);
		request.setAttribute("area", area);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("ListRestaurants.jsp");
	    dispatcher.forward(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	void showOrders(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException
	{
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		String restid=(String)request.getParameter("restid");
		int id = Integer.parseInt(restid);
		ArrayList<FoodItems> restlist=helper.getMenu(id);
		request.setAttribute("menulist", restlist);
		request.setAttribute("restid", id);
		request.setAttribute("restname", (String)request.getParameter("restname"));

	    RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
	    dispatcher.forward(request, response);
		
	}
	
	void createOrder(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException
	{
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		String restid=(String)request.getParameter("restid");
		int id = Integer.parseInt(restid);
		ArrayList<OrderItems> orderitems = new ArrayList<>();
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements())
		{
			String param = params.nextElement();
			if (!param.equals("restid")&& !param.equals("operation") && param.startsWith("food")) {
				OrderItems item = new OrderItems();
				item.setFoodId(Integer.parseInt(param.substring(4)));
				item.setQuantity(Integer.parseInt((String)request.getParameter(param)));
				orderitems.add(item);
			}
		}
			
		String orderid =helper.createOrder(id, orderitems);
		request.setAttribute("orderid", orderid);
		request.setAttribute("restid", id);
		request.setAttribute("restname", (String)request.getParameter("restname"));

	    RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
	    dispatcher.forward(request, response);
		
	}
	
	void showMenu(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException
	{
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		String restid=(String)request.getParameter("restid");
		int id = Integer.parseInt(restid);
		ArrayList<FoodItems> restlist=helper.getMenu(id);
		request.setAttribute("menulist", restlist);
		request.setAttribute("restid", id);
		request.setAttribute("restname", (String)request.getParameter("restname"));
	    RequestDispatcher dispatcher = request.getRequestDispatcher("ShowMenu.jsp");
	    dispatcher.forward(request, response);
		
	}

	
	void getOrders(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException
	{
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		String restid=(String)request.getParameter("restid");
		int id = Integer.parseInt(restid);
		ArrayList<ArrayList<String>> restlist=helper.getOrders(id);
		request.setAttribute("menulist", restlist);
		request.setAttribute("restid", id);
		request.setAttribute("restname", (String)request.getParameter("restname"));
	    RequestDispatcher dispatcher = request.getRequestDispatcher("GetOrders.jsp");
	    dispatcher.forward(request, response);
		
	}
	
	void deleteOrder(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException
	{
		RestaurantDBHelper helper=new RestaurantDBHelper("jdbc:mysql://localhost:3306/restdb","root","");
		String restid=(String)request.getParameter("orderid");
		int id = Integer.parseInt(restid);
		helper.deleteOrder(id);
		request.setAttribute("restid", request.getParameter("restid"));
		request.setAttribute("restname", (String)request.getParameter("restname"));
		getOrders(request,response);
//	    RequestDispatcher dispatcher = request.getRequestDispatcher("GetOrders.jsp");
//	    dispatcher.forward(request, response);
		
	}
}
