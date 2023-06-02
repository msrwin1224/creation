
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Reserve,java.util.List" %>
<%
	User loginUser = (User) session.getAttribute("loginUser");
	@SuppressWarnings("unchecked")
	List<Reserve> reserveList = (List<Reserve>) application.getAttribute("reserveList");
	String errorMsg = (String) request.getAttribute("errorMsg");
	String delMsg = (String) session.getAttribute("delMsg");
	String updateMsg = (String) session.getAttribute("updateMsg");
	String okMsg = (String) request.getAttribute("okMsg");
	String tPDMsg = (String) session.getAttribute("tPDMsg");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>イベントスペース予約管理表</title>
	</head>
	<body>
		<font size="6" color="teal"><strong>イベントスペース予約管理表</strong></font><br>
		ユーザー名: <font size="5" color="green"><strong><%= loginUser.getName() %></strong></font> でログインしています。<br>
		<script>
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth()+1;
			var week = today.getDay();
			var day = today.getDate();
			var dayOfWeek= new Array("日","月","火","水","木","金","土");
			var time= new Date();
			var hour = time.getHours();
			var minute = time.getMinutes();
			document.write("現在は "+year+"年"+month+"月"+day+"日 "+dayOfWeek[week]+"曜日 "+hour+"時",+minute+"分 です。");
		</script>
		<br>
		<a href="/eventSpace/Logout"><button  onclick="return confirm('ログアウトします。よろしいですか？')">ログアウト</button></a>&nbsp;
		<a href="/eventSpace/Main"><button onclick=>ページ更新</button></a>
		<br> <br>
		<font size="5" color="navy"><strong>☆ 新規登録 ☆</strong></font><br>
		<form action="/eventSpace/Main" method="post">
			<table>
				<tr>
					<td>日 付</td><td> 開始時間 </td><td> 終了時間 </td><td>使用理由</td>
				</tr>
				<tr>
					<td><input type="date" name="date" required></td>
					<td><input type="time" name="timeStart" required></td>
					<td><input type="time" name="timeEnd" required></td>
					<td><input type="text" name="purpose" placeholder="理由を記載してください" required></td>
					<td><input type="submit" value="予約する"></td>
				</tr>
			</table>
		</form>	
		<br>
		<font size="5" color="navy"><strong>★予約済み一覧★</strong></font> &emsp;
		<a href="/eventSpace/TimePassedDelete"><button  onclick="return confirm('終了した予約を削除します')">最適化</button></a>
		<% if (tPDMsg != null) { %>
			<font color="blue"><%= tPDMsg %></font>
			<% session.removeAttribute("tPDMsg"); %>
		<% } %>
		<% if (errorMsg != null) { %>
			<font color="red"><%= errorMsg %></font>
		<% } %>
		<% if (okMsg != null) { %>
			<font color="blue"><%= okMsg %></font>
		<% } %>
		<% if (delMsg != null) { %>
			<font color="red"><%= delMsg %></font>
			<% session.removeAttribute("delMsg"); %>
		<% } %>
		<% if (updateMsg != null) { %>
			<font color="blue"><%= updateMsg %></font>
			<% session.removeAttribute("updateMsg"); %>
		<% } %>
		
		<table>
			<tr> <th>申請者</th><th>日 付</th><th>&emsp;開始時間&emsp;</th>
				<th>&emsp;終了時間&emsp;</th><th>使用理由</th></tr>
		
			<% for (Reserve reserve : reserveList) { %>
			<tr><td>&emsp;<%= reserve.getUserName() %>&emsp;</td> <td>&emsp;<%= reserve.getDate() %>&emsp;</td> <td><%= reserve.getTimeStart() %>
				</td> <td><%= reserve.getTimeEnd() %></td> <td>&emsp;<%= reserve.getPurpose() %>&emsp;</td>
				
				<% if (loginUser.getId() == reserve.getUserId() || loginUser.getId() == 1){ %>
					<td><a href="/eventSpace/UpdateReserve?id=<%= reserve.getId() %>">
						<button onclick=>編集</button></a>
						
					<a href="/eventSpace/DeleteReserve?id=<%= reserve.getId() %>">
						<button onclick="return confirm('削除します。よろしいですか？')">削除</button> </a></td></tr>
				<% } %>
			<% } %>
			</table>

	</body>
</html>