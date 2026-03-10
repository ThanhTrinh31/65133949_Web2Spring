<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%
List<String[]> list = (List<String[]>) session.getAttribute("phonebook");
if (list == null) {
    list = new ArrayList<>();
    session.setAttribute("phonebook", list);
}

String editIndex = request.getParameter("edit");
String nameEdit = "";
String phoneEdit = "";

if (editIndex != null) {
    int i = Integer.parseInt(editIndex);
    nameEdit = list.get(i)[0];
    phoneEdit = list.get(i)[1];
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Danh bạ</title>
</head>

<body>

<h2>Danh bạ điện thoại</h2>

<form action="danhba_xuly.jsp" method="post">
    <input type="hidden" name="index" value="<%= editIndex == null ? -1 : editIndex %>">

    Tên:
    <input type="text" name="name" value="<%= nameEdit %>" required>

    SĐT:
    <input type="text" name="phone" value="<%= phoneEdit %>" required>

    <button type="submit">Lưu</button>
</form>

<br>

<table border="1" cellpadding="5">
<tr>
    <th>STT</th>
    <th>Tên</th>
    <th>SĐT</th>
    <th>Hành động</th>
</tr>

<%
for (int i = 0; i < list.size(); i++) {
%>
<tr>
    <td><%= i+1 %></td>
    <td><%= list.get(i)[0] %></td>
    <td><%= list.get(i)[1] %></td>
    <td>
        <a href="danhba.jsp?edit=<%=i%>">Sửa</a> |
        <a href="danhba_xuly.jsp?delete=<%=i%>">Xóa</a>
    </td>
</tr>
<%
}
%>

</table>

</body>
</html>
