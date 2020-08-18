<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Restaulicious!!</title>
</head>
<body >
<h2>RestauLicious!!</h2>
<%@page import="java.util.ArrayList" %>
<%@page import="edu.msrit.restaurant.model.Restaurants" %>
<script>
function showOrder(restid, restname){
	//alert(restid);
	document.forms["submitForm"].elements["operation"].value = "showOrder";
	document.forms["submitForm"].elements["restid"].value = restid;
	document.forms["submitForm"].elements["restname"].value = restname;

	document.forms["submitForm"].submit();
}

function getOrders(restid, restname){
	//alert(restid);
	document.forms["submitForm"].elements["operation"].value = "getOrders";
	document.forms["submitForm"].elements["restid"].value = restid;
	document.forms["submitForm"].elements["restname"].value = restname;

	document.forms["submitForm"].submit();
}
function showMenu(restid,restname){
	//alert(restid);
	document.forms["submitForm"].elements["operation"].value = "showMenu";
	document.forms["submitForm"].elements["restid"].value = restid;
	document.forms["submitForm"].elements["restname"].value = restname;

	document.forms["submitForm"].submit();
}
</script>
<%
ArrayList<Restaurants> arr = (ArrayList) request.getAttribute("restlist"); 
String area=(String) request.getAttribute("area");
if(area==null){
	area="";
}
%>
 <div align="center">
 	<form action="RestaurantServlet" method="post" name="submitForm">
 	 <input type="text" name="area" size="20" value="<%=area %>" placeholder="Filter by area"/>
 	 <input type="hidden" name="operation" value="search" />
 	 <input type="hidden" name="restid" value="0"/>
 	 <input type="hidden" name="restname" value="0"/>
 	 
     <input type="submit" value="Apply Filter" class="btn btn-success"/>
     </form>
  </div>
 <div >
        

<%
		for(int i=0; i < arr.size(); i++){
			Restaurants rest = arr.get(i);
		%>	
	<div class="panel panel-default">
    <div class="panel-heading"><h4><%=rest.getName() %></h4></div>
    <div class="panel-body"><table border=0 width=100%><tr><td colspan=3><%=rest.getArea() %>, <%=rest.getCity() %></td> </tr>
    <tr><td width=30%>Dine In Option Available</td><td align="center"><input type="button" name="showMenu" value="Show Menu" class="btn btn-primary" onClick="showMenu('<%=rest.getRestId() %>', '<%=rest.getName() %>');"/></td><td align="right"> <input type="button" value="Show Orders" onClick="getOrders('<%=rest.getRestId() %>','<%=rest.getName() %>');" class="btn btn-secondary"/> <input type="button" name="order" class="btn btn-secondary" value="Place Order" onClick="showOrder('<%=rest.getRestId() %>','<%=rest.getName() %>');"  />
    </td></tr></table></div>
  </div>

	<% } %>
</div>	  
</body>
</html>