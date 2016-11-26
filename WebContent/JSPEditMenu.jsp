<%@page import="java.sql.ResultSet"%>
<%@page import="database.MenuBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Edit Menu</title>
</head>
<body>
<%
	long menuId =(long) session.getAttribute("menuid");
	MenuBean menu = MenuBean.getMenuById(menuId);
	long restaurantId =(long) session.getAttribute("restaurantid");
%>
	<h1>Edit Menu</h1>
	<form method="post" action="sManageMenus">
		<input type="hidden" name="menuid" value="<%=menuId%>">
		<p>
			Name: <input type="text" name="name" value="<%=menu.getName()%>">
		</p>
		<p>
			Description: <input type="text" name="description" value="<%=menu.getDescription()%>">
		</p>
		<p>
			Price: <input type="text" name="price" value="<%=menu.getPrice()%>">
		</p>
		<p>
			Score: <input type="text" name="score" value="<%=menu.getScore()%>">
		</p>
		
		<input type="hidden" name="restaurantid" value="<%=restaurantId%>">
		<input type="hidden" name="action" value="editmenu">
		<br> <input type="submit" value="Edit Menu">
	</form>
</body>
</html>