<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="300" height="200" cellpadding="0" cellspacing="0" style=" table-layout:fixed; float:left; margin:0 25px 0 0;">
    <form action='${pageContext.servletContext.getContextPath()}/pet/search' method='post'>
        Owner : <input type='text' name='owner'>
        <input type='submit' name='search' value='Submit'>
    </form>
    <tr><td>ID</td><td>Name</td><td>Owner</td><td>Действия</td></tr>
    <c:forEach items="${pets}" var="pet" varStatus="status">
        <tr valign="top">
            <td>${pet.getId()}</td><td>${pet.getName()}</td><td>${pet.getOwner()}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/pet/edit?id=${pet.getId()}">Редактировать</a>
                <a href="${pageContext.servletContext.contextPath}/pet/delete?id=${pet.getId()}">Удалить</a>
            </td>
        </tr>
    </c:forEach>

</table>

<table border="1" cellpadding="0" cellspacing="0">
    <tr><td>ID</td><td>Name</td><td>Owner</td><td>Действия</td></tr>
    <form action='${pageContext.servletContext.getContextPath()}/user/search' method='post'>
        Owner : <input type='text' name='owner'>
        <input type='submit' name='search' value='Submit'>
    </form>
    <c:forEach items="${users}" var="user" varStatus="status">
    <tr valign="top">
        <td>${user.getId()}</td><td>${user.getName()}</td><td>${pet.getOwner()}</td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/user/edit?id=${user.getId()}">Редактировать</a>
            <a href="${pageContext.servletContext.contextPath}/user/delete?id=${user.getId()}">Удалить</a>
        </td>
    </tr>
    </c:forEach>
    1234
</table>
</body>
</html>

</body>
</html>

