<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%! int x=10; int y; int z=0; %>
	<% y=200; 
		z=x+y;
		out.append("Ket qua la:");
		out.append(String.valueOf(z));
	%>
	<h2>HOAC TA CO THE XUAT KIEU EXPRESSION</h2>
	<hr>
	<%="Ket qua la" %>
	<%=z %>
</body>
</html>