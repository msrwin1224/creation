<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id = request.getParameter("id"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>予約編集画面</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<h1>予約編集画面</h1>
		<form action="/eventSpace/UpdateReserve" method="post">
			<input type="hidden" name="id" value= <%= id %>>
			<input type="date" name="date" required>
			<input type="time" name="timeStart" required>
			<input type="time" name="timeEnd" required>
			<input type="text" name="purpose" placeholder="理由を記載してください" required>
			<input type="submit" value="編集">
		</form>
		<h4>編集する登録IDは <%= id %> です</h4>
		<p><a href="/eventSpace/Main">戻る</a></p>
	</body>
</html>