<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 20.05.2021
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% for (int i = 0; i < 5; i++) {%>
<h1>Hello from JSP<%=i%>
</h1>
<%}%>
</body>
</html>
