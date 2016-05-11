<%@ page import="java.util.*,redis.clients.jedis.*" %>

<html>
	<head>
	</head>
	<body>

		<form>
			String: <input type="text" name="string">
			<br>
			Score: <input type="text" name="number">
			<br>
			<br>
			<input text="Add an entry" type=submit>
		</form>

<br>

<table border=1>
<tr><th>String</th><th>Score</th></tr>
<%
for (Tuple tuple:((Set<Tuple>)request.getAttribute("model"))) {
	%><tr><td><%=tuple.getElement()%></td><td><%=tuple.getScore()%></td></tr><%
}
%>
</table>

<p>

model = ${model}
	</body>
</html>
