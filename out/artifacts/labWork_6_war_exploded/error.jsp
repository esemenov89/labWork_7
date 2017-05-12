<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 02.05.2017
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h1>${errorName}</h1
<h2>${errorMessage}</h2>
<div style="position: fixed; top: 0%; right: 0;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="button" value="Home" onclick="location.href = '/'">
        <input type="submit" value="Logout" />
    </form>
</div>
</body>
</html>
