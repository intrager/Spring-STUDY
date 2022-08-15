<%--
  Created by IntelliJ IDEA.
  User: intra
  Date: 2022-08-15
  Time: 오후 8:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/board/register" method="get">
        <input type="submit" value="register(GET)"/>
    </form>

    <form action="/board/register" method="post">
        <input type="submit" value="register(POST)"/>
    </form>

    <form action="/board/modify" method="get">
        <input type="submit" value="modify(GET)" />
    </form>

    <form action="/board/modify" method="post">
        <input type="submit" value="modify(POST)" />
    </form>

    <form action="/board/remove" method="post">
        <input type="submit" value="remove(POST)" />
    </form>

    <form action="/board/list" method="get">
        <input type="submit" value="list(GET)" />
    </form>
</body>
</html>
