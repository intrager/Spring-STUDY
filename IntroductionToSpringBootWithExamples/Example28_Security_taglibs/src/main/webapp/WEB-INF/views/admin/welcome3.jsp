<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Welcome</title>
    </head>

    <body>
        welcome : Admin
        <hr>

        <sec:authorize access="isAuthenticated()">
            <p> Log-In</p>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
            <p> Log-Out</p>
        </sec:authorize>
        USER ID : <sec:authentication property="name" /><br />

        <c:url value="/logout" var="logoutUrl" />
        <a href="${logoutUrl}">Log Out</a> <br />
    </body>
</html>