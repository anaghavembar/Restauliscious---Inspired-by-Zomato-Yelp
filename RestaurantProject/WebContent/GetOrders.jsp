<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RestauLicious!!</title>
</head>
<body>
<%
String restname = request.getAttribute("restname").toString();
%>
<script>
function deleteOrder(restid,orderid){
	//alert(restid);
	document.forms["submitForm"].elements["operation"].value = "deleteOrder";
	document.forms["submitForm"].elements["restid"].value = restid;
	document.forms["submitForm"].elements["orderid"].value = orderid;

	document.forms["submitForm"].submit();
}
</script>
 	<form action="RestaurantServlet" method="post" name="submitForm">
 	 <input type="hidden" name="operation" value="deleteOrder" />
 	 <input type="hidden" name="restid" value="0"/>
 	 <input type="hidden" name="restname" value="<%=restname%>"/>
 	  	 <input type="hidden" name="orderid" value="0"/>
 	 
     </form>
<h1>Orders at <%=request.getAttribute("restname") %></h1>
<%@page import="java.util.ArrayList" %>
<div>
<div>

<%
ArrayList<ArrayList<String>> arr = (ArrayList) request.getAttribute("menulist");
String restid = request.getAttribute("restid").toString();

String orderid = "0";
for (int i=0; i<arr.size(); i++)
{
%>

<%
	ArrayList<String> items = arr.get(i);
	for(int j=0; j<items.size(); j++) {
%>

<%if (j==0) {
	         if (!(items.get(j).equals(orderid)) ){
	        	 orderid = items.get(j);
%>
	</div></div>
	<div class="panel panel-default">
    <div class="panel-heading">
    		<table width="50%"><tr>
    		    <td>Order #: <%=items.get(j) %></td>
    			<td align="right"> <input type="button" value="Complete Order" onClick="deleteOrder('<%=restid%>','<%=orderid%>')"/></td>
    			</tr>
    		</table>
    </div>
    <div class="panel-body">
<%	        	 
	        } 
		}else {
%>

<%
			if (j ==1) out.write("<li>");
			out.write(items.get(j));
			if (j ==2){
				%></li>
				
				<%
			}
		}  %>



<%} %>

<%} %>
</div>
</div>
</body>
</html>