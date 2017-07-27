<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/views/pet/CreatePet.jsp">Add pet information</a>
<form action='${pageContext.servletContext.getContextPath()}/pet/search' method='post'>
    Owner : <input type='text' name='owner'>
    <input type='submit' name='search' value='Submit'>
</form>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Owner</td>
        <td>Действия</td>
    </tr>
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
</body>
</html>