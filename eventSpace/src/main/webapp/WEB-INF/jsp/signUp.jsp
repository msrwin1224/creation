<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザー新規登録</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<h1>ユーザー新規登録</h1>
		<% if (errorMsg != null) { %>
			<p><%= errorMsg %></p>
		<% } %>
		<% if (msg != null) { %>
			<p><%= msg %></p>
		<% } %>
		<form action="/eventSpace/SignUp" method="post">
			ユーザー名 : <input type="text" name="name" placeholder="ユーザー名" required><br>
			パスワード : <input type="password" name="pass" placeholder="パスワード" required><br>
			メールアドレス : <input type="email" name="mail" placeholder="メールアドレス" required><br>
			ユーザータイプ : <select name="userType" required>
				<option value="user">一般</option>
				<option value="admin">管理者</option>	
			</select> <br>
			<input type="submit" value="送信">
		</form>
		<br>
		<a href="/eventSpace/">トップへ</a>
	</body>
</html>