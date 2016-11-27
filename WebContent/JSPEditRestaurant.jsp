<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Menu Finder - Edit Restaurant</title>
</head>
<body>
	<h1>Create Restaurant</h1>
	<form method="post" action="sManageRestaurants">
		<p>
			Name: <input type="text" name="name" value="${restaurant.name}">
		</p>
		<p>
			CIF: <input type="text" name="cif" value="${restaurant.cif}">
		</p>
		<p>
			Address: <input type="text" name="address" value="${restaurant.address}">
		</p>
		<p>
			City: <input type="text" name="city" value="${restaurant.city}">
		</p>
		<p>
			Postal code: <input type="text" name="postalcode" value="${restaurant.postalCode}">
		</p>
		<p>
			State: <input type="text" name="state" value="${restaurant.state}">
		</p>
		<p>
			Country: <input type="text" name="country" value="${restaurant.country}">
		</p>
		<p>
			Email: <input type="email" name="email" value="${restaurant.email}">
		</p>
		<p>
			Phone: <input type="text" name="phone" value="${restaurant.phone}">
		</p>
		<input type="hidden" name="accountid" value="${restaurant.account}">
		<input type="hidden" name="id" value="${restaurant.id}">
		<input type="hidden" name="action" value="editrestaurant">
		<br> <input type="submit" value="Save restaurant">
	</form>
</body>
</html>