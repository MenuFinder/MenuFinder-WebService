<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Manage Account</title>
</head>
<body class="blurBg-false" style="background-color:#EBEBEB">

<!-- Start Formoid form-->
<link rel="stylesheet" href="createaccount_files/formoid1/formoid-solid-green.css" type="text/css" />
<script type="text/javascript" src="createaccount_files/formoid1/jquery.min.js"></script>
<form class="formoid-solid-green" style="background-color:#1A2223;font-size:14px;font-family:'Roboto',Arial,Helvetica,sans-serif;color:#34495E;max-width:480px;min-width:150px" method="post"  action="sManageAccount">
<div class="title">
<h2>Create Account</h2>
</div>
	<div class="element-input"><label class="title">
	<span class="required">*</span></label>
	<div class="item-cont">
	<input class="large" type="text" name="accountid" required="required" placeholder="User"/><span class="icon-place"></span></div></div>
	<div class="element-password"><label class="title"></label><div class="item-cont">
	<input class="large" type="password" name="password" value="" placeholder="Password"/><span class="icon-place"></span></div></div>
	<div class="element-select"><label class="title"></label><div class="item-cont"><div class="large"><span><select name="select" >
		<option name="type" value="restaurant">Restaurant</option>
	</select><i></i><span class="icon-place"></span></span></div></div></div>
	<div class="element-recaptcha"><label class="title"></label><script type="text/javascript">var RecaptchaOptions = {theme : "clean"};</script>
<script type="text/javascript" src="http://www.google.com/recaptcha/api/challenge?k=6LdpLw8UAAAAAGGK4ONQKkkjfinzMKJBdITuPtwe&theme=clean"></script>
<noscript><iframe src="http://www.google.com/recaptcha/api/noscript?k=6LdpLw8UAAAAAGGK4ONQKkkjfinzMKJBdITuPtwe&hl=en" height="300" width="500" frameborder="0"></iframe></br>
<textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea><input type="hidden" name="recaptcha_response_field" value="manual_challenge"></noscript>
<script type="text/javascript">if (/#invalidcaptcha$/.test(window.location)) (document.getElementById("recaptcha_widget_div")).className += " error"</script></div>
<div class="submit"><input type="submit" value="Register account"/></div></form><p class="frmd">

</body>
</html>