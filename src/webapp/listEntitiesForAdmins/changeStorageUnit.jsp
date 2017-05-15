<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<html>
<head>
    <title>Change storage unit</title>
</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div style="position: fixed; top: 0%; right: 0;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="button" value="Home" onclick="location.href = '${baseURL}'">
        <input type="button" value="List" onclick="location.href = '${baseURL}/listEntitiesForAdmins'">
        <input type="submit" value="Logout" />
    </form>
</div>
<h1>Change storage unit</h1>
<form method="post">
    <p>Author of text:</p>
    <p style="color: red;">${changeAuthor}</p>
    <input type="text" name="author" maxlength="50" value="${author}"/>
    <p>Title:</p>
    <p style="color: red;">${changeTitle}</p>
    <input type="text" name="title" maxlength="50" value="${title}"/>
    <p>Publishing house:</p>
    <p style="color: red;">${changePublishingHouse}</p>
    <input type="text" name="publishingHouse" maxlength="50" value="${publishingHouse}"/>
    <p>City:</p>
    <p style="color: red;">${changeCity}</p>
    <input type="text" name="city" maxlength="50" value="${city}"/>
    <p>Year:</p>
    <p style="color: red;">${changeYear}</p>
    <input type="text" name="year" maxlength="4" value="${year}"/>
    <p>Pages count:</p>
    <p style="color: red;">${changePagesCount}</p>
    <input type="text" name="pagesCount" maxlength="4" value="${pagesCount}"/>
    <p>ISN of storage unit:</p>
    <p style="color: red;">${changeIsn}</p>
    <input type="text" name="isn" maxlength="50" value="${isn}"/>
    <p>Text:</p>
    <p style="color: red;">${changeText}</p>
    <p></p>
    <input type="hidden" name="isnOld" value="${isnOld}"/>
    <textarea rows="20" cols="100" maxlength="100000" name="text">${text}</textarea>
    <p></p>
    <input type="submit" value="ok"/>
</form>
</body>
</html>