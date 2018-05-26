<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>学生列表</title>
</head>
<body>
<div class="container">
    <h3 align="center" >学生列表</h3>
    <table border="1" width="70%" align="center"  class="table table-hover">
        <tr>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>学生性别</th>
            <th>学生班级</th>
            <th>学生生日</th>
            <th></th>
        </tr>
        <c:forEach items="${requestScope.studlist}" var="stud">
        <tr>
            <td>${stud.sno}</td>
            <td>${stud.sname}</td>
            <td>${stud.ssex}</td>
            <td>${stud.sclass}</td>
            <td>${stud.sbirthday}</td>
            <td>
                <a href="<c:url value='/StudentServlet?method=preEdit&sno=${stud.sno}'/> ">编辑</a>
                <a href="<c:url value='/StudentServlet?method=delete&sno=${stud.sno}'/> ">删除</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
