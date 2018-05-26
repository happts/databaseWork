<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/5/6
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>TEACH list</title>
</head>
<body>
<div class="container">
<h3 align="center" >学生列表</h3>
<table border="1" width="70%" align="center" class="table table-hover">

    <tr>
        <th>教师编号</th>
        <th>课程号</th>
        <th></th>
    </tr>

    <c:forEach items="${requestScope.list}" var="teach">
        <tr >
            <td>${teach.tno}</td>
            <td>${teach.cno}</td>
            <td><a href="<c:url value='/TeachServlet?method=delete&id=${teach.id}'/> ">删除</a>
            </td>
        </tr>
    </c:forEach>



</table>
</div>
</body>
</html>
