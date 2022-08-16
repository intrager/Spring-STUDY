<%--
  Created by IntelliJ IDEA.
  User: intra
  Date: 2022-08-16
  Time: 오후 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/board/get" method="get">
        <button type="submit" name="modify">Modify</button>
        <button type="submit" name="remove">Remove</button>
    </form>

    <a href="/board/get?list">List</a>
</body>
</html>
