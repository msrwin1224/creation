<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>イベントスペース予約管理</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<font size="6" color="teal"><strong>イベントスペース予約管理ログイン</strong></font><br>
		<% if(loginUser != null) { %>
		<p>ログインに成功しました</p>
		<p>ようこそ<font color="green"><strong><%= loginUser.getName() %></strong></font>さん</p>
		<a href="/eventSpace/Main">予約・編集画面へ</a><br>
		<% } else { %>
		<p>ログインに失敗しました</p>
		<a href="/eventSpace/">TOPへ</a>
		<% } %>
		<br>
		<a href="/eventSpace/SignUp">新規登録</a><br>
		<a href="/eventSpace/Logout">ログアウト</a>
	</body>
</html>