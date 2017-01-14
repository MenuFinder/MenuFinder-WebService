<%@page import="java.sql.ResultSet"%>
<%@page import="database.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu Finder - Edit Item</title>
</head>
<body class="blurBg-false" style="background-color: #EBEBEB">
	<%
	long itemId = Long.parseLong(session.getAttribute("itemid").toString());
	ItemBean item = ItemBean.getItemById(itemId);
	long restaurantId = Long.parseLong(session.getAttribute("restaurantid").toString());
	double itemScore = item.getScore();
%>
	<link rel="stylesheet"
		href="additem_files/formoid1/formoid-solid-green.css" type="text/css" />
	<script type="text/javascript"
		src="additem_files/formoid1/jquery.min.js"></script>
	<form class="formoid-solid-green" style="background-color:#1A2223;font-size:14px;font-family:'Roboto',Arial,Helvetica,sans-serif;color:#34495E;max-width:480px;min-width:150px" method="post" action="sManageItems">
	<div class="title">
		<h2>Edit Item</h2>
	</div>
		<input type="hidden" name="itemid" value="<%=itemId%>">
	<div class="element-input" title="Name ">
		<label class="title"></label>
		<div class="item-cont">
			<input class="large" type="text" name="name" placeholder="Name" value="<%=item.getName()%>"/><span class="icon-place"></span>
		</div>
	</div>
	<div class="element-input">
		<label class="title"></label>
		<div class="item-cont">
			<input class="large" type="text" name="description" placeholder="Description" value="<%=item.getDescription()%>"/><span class="icon-place"></span>
		</div>
	</div>
	<div class="element-input">
		<label class="title"></label>
		<div class="item-cont">
			<input class="large" type="text" name="price" value="<%=item.getPrice()%>" placeholder="Price"/><span class="icon-place"></span>
		</div>
	</div>
	<div class="element-rating">
			<label class="title">Score</label>
			<div class="rating">
				<input type="radio" class="rating-input" id="rating-5" name="score"
					value="5" disabled readonly <%if (itemScore >= 5) {%>
					checked="checked" <%}%> /> <label for="rating-5"
					class="rating-star"></label> <input type="radio"
					class="rating-input" id="rating-4" name="score" value="4" disabled
					readonly <%if (itemScore >= 4 && itemScore < 5) {%>
					checked="checked" <%}%> /> <label for="rating-4"
					class="rating-star"></label> <input type="radio"
					class="rating-input" id="rating-3" name="score" value="3" disabled
					<%if (itemScore >= 3 && itemScore < 4) {%> checked="checked" <%}%> />
				<label for="rating-3" class="rating-star"></label> <input
					type="radio" class="rating-input" id="rating-2" name="score"
					value="2" disabled readonly
					<%if (itemScore >= 2 && itemScore < 3) {%> checked="checked" <%}%> />
				<label for="rating-2" class="rating-star"></label> <input
					type="radio" class="rating-input" id="rating-1" name="score"
					value="1" enabled="false" disabled readonly
					<%if (itemScore >= 1 && itemScore < 2) {%> checked="checked" <%}%> />
				<label for="rating-1" class="rating-star"></label>
			</div>
		</div>
		<div class="submit">
			<input type="hidden" name="restaurantid" value="<%=restaurantId%>">
			<input type="hidden" name="action" value="edititem">
			<input type="submit" value="Edit Item"/>
		</div>
	</form>
	<script type="text/javascript" src="additem_files/formoid1/formoid-solid-green.js"></script>
</body>
</html>