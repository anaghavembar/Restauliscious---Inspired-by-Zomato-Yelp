<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RestaulLicious!!</title>
</head>
<body >
<h2>RestauLicious!!</h2>
<h1>Menu at <%=request.getAttribute("restname") %></h1>
<%@page import="java.util.ArrayList" %>
<%@page import="edu.msrit.restaurant.model.FoodItems" %>
<table>

<%
ArrayList<FoodItems> arr = (ArrayList) request.getAttribute("menulist"); 
for (int i=0; i<arr.size(); i++)
{
%>
<tr>
<td><li><%=arr.get(i).getFoodItem() %></li></td>
</tr>
<%} %>
</table>


</body>
</html>