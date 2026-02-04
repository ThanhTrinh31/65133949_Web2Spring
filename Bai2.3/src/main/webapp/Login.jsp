<%@ page contentType="text/html;charset=UTF-8" %>

<%
String u = request.getParameter("user");
String p = request.getParameter("pass");

if ("ABC".equals(u) && "MNK".equals(p)) {
    response.sendRedirect("UserProfile.html");
} else {
    response.sendRedirect("Login.html");
}
%>
