<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%String loginErr = (String) session.getAttribute("loginErr"); %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>イベントスペース予約管理</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<font size="6" color="teal"><strong>イベントスペース予約管理</strong></font><br>
		<form action="/eventSpace/Login" method="post">
			ユーザー名：<input type="text" name="name" placeholder="ユーザー名" required><br>
			パスワード：<input type="password" name="pass" placeholder="パスワード" required><br>
			<input type="submit" value="ログイン">
			<br><br>
			<% if (loginErr != null) { %>
			<font color="red"><%= loginErr %></font>
			<% session.removeAttribute("loginErr"); %>
		<% } %>
		</form>
	</body>
</html>