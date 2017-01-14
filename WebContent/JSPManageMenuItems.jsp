<%@page import="database.MenuBean"%>
<%@page import="java.util.Map"%>
<%@page import="database.ItemCategoryBean"%>
<%@page import="database.ItemBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	long menuId = Long.parseLong(session.getAttribute("menuid").toString());
	MenuBean menu = MenuBean.getMenuById(menuId);
	List<ItemBean> restaurantItems = ItemBean.getRestaurantItems(menu.getRestaurant());
	List<ItemCategoryBean> categories = ItemCategoryBean.getAllBeans();
%>
<title><%=menu.getName()%> Items</title>
</head>
<body>
	<h1><%=menu.getName()%> Items</h1>
	<c:forEach var="menuitem" items="${menuitems}">
		<table>
			<tr>
				<th colspan="2"><%=ItemCategoryBean.getItemCategoryById(
						((Map.Entry<Long, List<ItemBean>>) pageContext.
								getAttribute("menuitem")).getKey()).getName()%></th>
			</tr>
			<c:forEach var="item" items="${menuitem.value}">
				<tr>
					<td>${item.name}</td>
					<td><form method="post" action="sManageMenus">
							<input type="hidden" name="action" value="deletemenuitem">
							<input type="hidden" name="itemid" value="${item.id}">
							<input type="hidden" name="menuid" value="<%=menuId%>">
							<input type="hidden" name="categoryid" value="${menuitem.key}">
							<input type="submit" value="Delete item" onclick="return confirm('Do you want to delete this item of this menu?')">
						</form></td>
				</tr>
			</c:forEach>
		</table>
		<br>
	</c:forEach>
	<h2>Add item to menu</h2>
	<form method="post" action="sManageMenus">
		<input type="hidden" name="action" value="addmenuitem">
		<input type="hidden" name="menuid" value="<%=menuId%>">
		<p>
			Item: <select name="itemid">
				<c:forEach var="item" items="<%=restaurantItems%>">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			Category: <select name="categoryid">
				<c:forEach var="category" items="<%=categories%>">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>
		</p>
		<input type="submit" value="Add item">
	</form>
</body>
</html>