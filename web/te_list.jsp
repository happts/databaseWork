<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/25
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>教师列表</title>
</head>
<body>
<div class="container">
<h3 align="center">教师列表</h3>
<table border="1" width="70%" align="center" class="table table-hover">
    <tr>
        <th>教师编号</th>
        <th>教师姓名</th>
        <th>教师性别</th>
        <th>教师生日</th>
        <th>所在系</th>
        <th>职称</th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.teaList}" var="tea">
        <tr>
            <td>${tea.tno}</td>
            <td>${tea.tname}</td>
            <td>${tea.tsex}</td>
            <td>${tea.tbirthday}</td>
            <td>${tea.tdepart}</td>
            <td>${tea.ttitle}</td>
            <td>
                <a href="<c:url value='/TeacherServlet?method=preEdit&tno=${tea.tno}'/> ">编辑</a>
                <a href="<c:url value='/TeacherServlet?method=delete&tno=${tea.tno}'/> ">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

</div>
</body>
</html>
