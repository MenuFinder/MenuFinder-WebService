<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Manage Account</title>
</head>
<body>
	<h1>Create Account</h1>
	<form method="post" action="sManageAccount">
		<p>
			User: <input type="text" name="accountid">
		</p>
		<p>
			Password: <input type="password" name="password">
		</p>
		<p>
			Type: <input type="text" name="type" value="restaurant">
		</p>
		<input type="hidden" name="action" value="addaccount">
		<br> <input type="submit" value="Register account">
	</form>
</body>
</html>