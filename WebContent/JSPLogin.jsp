<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Create Restaurant</title>
</head>
<body>
	<h1>Menu Finder</h1>
	<form method="post" action="sManageAccount">
		<p>
			Username: <input type="text" name="accountid">
		</p>
		<p>
			Password: <input type="password" name="password">
		</p>
		
		<input type="hidden" name="action" value="login">
		<br> <input type="submit" value="Login">
	</form>
	<form method="get" action="sManageAccount">
		<input type="hidden" name="action" value="addaccount">
		<input type="submit" value="Register">
	</form>
</body>
</html>