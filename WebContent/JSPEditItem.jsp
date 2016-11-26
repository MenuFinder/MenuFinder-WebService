<%@page import="java.sql.ResultSet"%>
<%@page import="database.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Edit Item</title>
</head>
<body>
	<%
	long itemId =(long) session.getAttribute("itemid");
	ItemBean item = ItemBean.getItemById(itemId);
	long restaurantId =(long) session.getAttribute("restaurantid");
%>
	<h1>Edit Item</h1>
	<form method="post" action="sManageItems">
		<input type="hidden" name="itemid" value="<%=itemId%>">
		<p>
			Name: <input type="text" name="name" value="<%=item.getName()%>">
		</p>
		<p>
			Description: <input type="text" name="description" value="<%=item.getDescription()%>">
		</p>
		<p>
			Price: <input type="text" name="price" value="<%=item.getPrice()%>">
		</p>
		<p>
			Score: <input type="text" name="score" value="<%=item.getScore()%>">
		</p>
		
		<input type="hidden" name="restaurantid" value="<%=restaurantId%>">
		<input type="hidden" name="action" value="edititem">
		<br> <input type="submit" value="Edit Item">
	</form>
</body>
</html>