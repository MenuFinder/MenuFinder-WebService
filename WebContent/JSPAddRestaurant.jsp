<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Menu Finder - Create Restaurant</title>
</head>
<body>
	<h1>Create Restaurant</h1>
	<form method="post" action="sManageRestaurants">
		<p>
			Name: <input type="text" name="name">
		</p>
		<p>
			CIF: <input type="text" name="cif">
		</p>
		<p>
			Address: <input type="text" name="address">
		</p>
		<p>
			City: <input type="text" name="city">
		</p>
		<p>
			Postal code: <input type="text" name="postalcode">
		</p>
		<p>
			State: <input type="text" name="state">
		</p>
		<p>
			Country: <input type="text" name="country">
		</p>
		<p>
			Email: <input type="email" name="email">
		</p>
		<p>
			Phone: <input type="text" name="phone">
		</p>
		<input type="hidden" name="accountid" value="${accountid}">
		<input type="hidden" name="action" value="addrestaurant">
		<br> <input type="submit" value="Register restaurant">
	</form>
</body>
</html>