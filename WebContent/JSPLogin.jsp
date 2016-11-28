<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Menu Finder - Login</title>
</head>
<body>
	<div class="container">
		<section id="content">
		<p>
			<img src="img/new_logo_android.png" width="112" height="117">
		</p>
		<form method="post" action="sManageAccount">
			<h1>MenuFinder</h1>
			<div>
				<input type="text" placeholder="Username" name="accountid"
					id="username" />
			</div>
			<div>
				<input type="password" placeholder="Password" required=""
					name="password" id="password" />
			</div>
			<div align="center">
				<input type="hidden" name="action" value="login"> <input
					type="submit" value="Log in" />
				<center>
					<a href="sManageAccount?action=addaccount">Register</a>
				</center>
			</div>
		</form>
		</section>
		<!-- content -->
	</div>
	<!-- container -->
</body>
</html>