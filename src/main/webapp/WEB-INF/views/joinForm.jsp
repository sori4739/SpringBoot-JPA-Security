<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form action="/user/create" method = "POST">
	<input type="text" name="username" placeholder="Username"/><br/>
	<input type="password" name="password" placeholder="Password"/><br/>
	
	<input type="email" name="email" placeholder="Email"/><br/>
	<input type="submit" value="전송"/>
</form>
</body>
</html>