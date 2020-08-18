<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant Order</title>
</head>
<body>
<h1>Order Food From <%=request.getAttribute("restname") %></h1>
<%@page import="java.util.ArrayList" %>
<%@page import="edu.msrit.restaurant.model.FoodItems" %>
<% if (request.getAttribute("orderid") != null)
	{
	%>
	<p> Your order is placed in <%=request.getAttribute("restname")%>. Order number is <%=request.getAttribute("orderid") %> </p>
<% } else { %>
<form action="RestaurantServlet" method="post">
<input type="hidden" name="operation" value="placeOrder"/>
<input type="hidden" name="restid" value="<%=request.getAttribute("restid")%>"/>
<input type="hidden" name="restname" value="<%=request.getAttribute("restname")%>"/>
<table>
<tr>
<td></td>
<td>Quantity</td>
</tr>
<%
ArrayList<FoodItems> arr = (ArrayList) request.getAttribute("menulist"); 
for (int i=0; i<arr.size(); i++)
{
%>
<tr>
<td><%=arr.get(i).getFoodItem() %></td>
<td><input type="text" name="food<%=arr.get(i).getFoodId() %>" /></td>
</tr>
<%} %>
</table>
<input type="submit" value="Place Order"/>
</form>
<%} %>
</body>
</html>