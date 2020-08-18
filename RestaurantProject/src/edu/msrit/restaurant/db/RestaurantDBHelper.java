package edu.msrit.restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.msrit.restaurant.model.FoodItems;
import edu.msrit.restaurant.model.Menu;
import edu.msrit.restaurant.model.OrderItems;
import edu.msrit.restaurant.model.RestaurantType;
import edu.msrit.restaurant.model.Restaurants;

public class RestaurantDBHelper {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public RestaurantDBHelper(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
	
	protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
	 protected void disconnect() throws SQLException {
	        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
	            jdbcConnection.close();
	        }
	    }
	
	public ArrayList<Restaurants> listRestaurants()throws SQLException{
		ArrayList<Restaurants>restList=new ArrayList<>();
		String sql = "SELECT * FROM restaurants";
        
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	Restaurants rest=new Restaurants();
        	int restid=(int)resultSet.getInt("RestId");
            rest.setRestId(resultSet.getInt("RestId"));
            rest.setName(resultSet.getString("Name"));
            rest.setArea(resultSet.getString("Area"));
            rest.setCity(resultSet.getString("City"));
            rest.setPendingOrders(resultSet.getInt("PendingOrders"));
            
            String sql2="SELECT Type FROM restaurantType WHERE RestId="+restid;
            Statement statement2 = jdbcConnection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            ArrayList<RestaurantType> arrlist=new ArrayList<>();
            while (resultSet2.next()) {
            	RestaurantType obj=new RestaurantType();
            	String type=(String)resultSet2.getString("type");
            	obj.setType(type);
            	arrlist.add(obj);
            	
            }
            resultSet2.close();
            rest.setTypes(arrlist);
            restList.add(rest);
            
            
        }        
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return restList;	
	}
	
	
	
	public ArrayList<Restaurants> filterByArea(String area)throws SQLException{
		ArrayList<Restaurants>restList=new ArrayList<>();
		String sql = "SELECT * FROM restaurants";
		if(area!=null&&area!="")
		{sql += " WHERE Area='"+area+"'";
		}
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	Restaurants rest=new Restaurants();
        	int restid=(int)resultSet.getInt("RestId");
            rest.setRestId(resultSet.getInt("RestId"));
            rest.setName(resultSet.getString("Name"));
            rest.setArea(resultSet.getString("Area"));
            rest.setCity(resultSet.getString("City"));
            rest.setPendingOrders(resultSet.getInt("PendingOrders"));
            
            String sql2="SELECT Type FROM restaurantType WHERE RestId="+restid;
            Statement statement2 = jdbcConnection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            ArrayList<RestaurantType> arrlist=new ArrayList<>();
            while (resultSet2.next()) {
            	RestaurantType obj=new RestaurantType();
            	String type=(String)resultSet2.getString("type");
            	obj.setType(type);
            	arrlist.add(obj);
            	
            }
            resultSet2.close();
            rest.setTypes(arrlist);
            restList.add(rest);
            
            
        }
        
        
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return restList;
    
		
	}
	
	public ArrayList<FoodItems> getMenu(int restid) throws SQLException
	{
		ArrayList<FoodItems>restList=new ArrayList<>();
		String sql = "SELECT fooditems.* FROM menu, fooditems";
		sql += " WHERE menu.FoodId = fooditems.FoodId and menu.RestId="+restid;
		
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	FoodItems fooditem=new FoodItems();
        	fooditem.setFoodId(resultSet.getInt("FoodId"));
        	fooditem.setFoodItem(resultSet.getString("FoodItem"));
        	
        	restList.add(fooditem);         
            
        }        
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return restList;
    

	}
	
	public String createOrder(int restid, ArrayList<OrderItems> fooditems)throws SQLException
	{

		int orderid = 0;
		String sql = "insert into OrderInfo values (?,?)";
		
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet rs = statement.executeQuery("select max(orderId) from OrderInfo");
        while(rs.next())
        {
        	 orderid = rs.getInt(1);
        }
        rs.close();
        statement.close();
        int neworderid = orderid+1;
        PreparedStatement pstmt = jdbcConnection.prepareStatement(sql);
        pstmt.setInt(1, neworderid);
        pstmt.setInt(2, restid);
        int updCount = pstmt.executeUpdate();
         
       
         
        pstmt.close();
        
        PreparedStatement pstmt1 = jdbcConnection.prepareStatement("insert into OrderItems values (?,?,?)");
        for (int i=0; i< fooditems.size(); i++) 
        {
        	pstmt1.setInt(1, neworderid);
        	pstmt1.setInt(2, fooditems.get(i).getFoodId());

        	pstmt1.setInt(3, fooditems.get(i).getQuantity());
        	pstmt1.executeUpdate();
        }
        pstmt1.close();
        
        disconnect();
         
        return neworderid+"";		
		
	}
	
	public ArrayList<ArrayList<String>> getOrders(int restid) throws SQLException
	{
		ArrayList<ArrayList<String>>restList=new ArrayList<>();
		String sql = "SELECT OrderInfo.orderid, fooditems.foodItem, OrderItems.quantity FROM OrderInfo, OrderItems, fooditems";
		sql += " WHERE OrderInfo.restid = "+restid+
				" and Orderinfo.orderid = Orderitems.orderId" +
				" and orderitems.foodid = fooditems.foodid";
		
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	ArrayList<String> orderdet = new ArrayList<>();
        	orderdet.add(resultSet.getInt("orderid") + "");
        	orderdet.add(resultSet.getString("FoodItem"));
        	orderdet.add(resultSet.getInt("quantity")+"");
        	
        	restList.add(orderdet);         
            
        }        
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return restList;
    

	}
	public void deleteOrder(int orderid) throws SQLException
	{
		String sql = "Delete FROM Orderinfo where orderid = "+ orderid;
		
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.executeUpdate();
         
        
        statement.close();
         
        disconnect();
         
       
    

	}
}
