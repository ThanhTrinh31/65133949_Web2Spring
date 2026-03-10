<%@ page import="java.util.*" %>

<%
List<String[]> list = (List<String[]>) session.getAttribute("phonebook");
if (list == null) {
    list = new ArrayList<>();
    session.setAttribute("phonebook", list);
}

// XÓA
String del = request.getParameter("delete");
if (del != null) {
    int i = Integer.parseInt(del);
    list.remove(i);
    response.sendRedirect("danhba.jsp");
    return;
}

// THÊM / SỬA
int index = Integer.parseInt(request.getParameter("index"));
String name = request.getParameter("name");
String phone = request.getParameter("phone");

if (index == -1) {
    list.add(new String[]{name, phone}); // thêm
} else {
    list.set(index, new String[]{name, phone}); // sửa
}

response.sendRedirect("PhoneBook.jsp");
%>
