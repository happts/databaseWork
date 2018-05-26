<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/23
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>成绩列表</title>
</head>
<body>
<div class="container">
<h3 align="center" >成绩列表</h3>
<table border="1" width="70%" align="center" class="table table-hover">
    <tr>
        <th>学生学号</th>
        <th>课程号</th>
        <th>学生成绩</th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.scList}" var="sc">
        <tr>
            <td>${sc.sno}</td>
            <td>${sc.cno}</td>
            <td>${sc.grade}</td>
            <td>
                <a href="<c:url value='/ScServlet?method=preEdit&sno=${sc.sno}&cno=${sc.cno}'/> ">编辑</a>
                <a href="<c:url value='/ScServlet?method=delete&sno=${sc.sno}&cno=${sc.cno}'/> ">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>

</html>
