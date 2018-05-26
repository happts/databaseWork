<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/5/4
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>29个题目</title>
</head>
<body>
<h3 align="center" >学生列表</h3>
<table border="1" width="70%" align="center">
    <tr>
        <th>学号</th>
        <th>学生姓名</th>
        <th>学生性别</th>
        <th>学生班级</th>
        <th>学生生日</th>
        <th>所选课程号</th>
        <th>课程名称</th>
        <th>分数</th>
        <th>教师编号</th>
        <th>教师姓名</th>
        <th>教师性别</th>
        <th>教师生日</th>
        <th>教师部门</th>
        <th>教师职称</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="stud">
        <tr>
            <td>${stud.SNO}</td>
            <td>${stud.SNAME}</td>
            <td>${stud.SSEX}</td>
            <td>${stud.SCLASS}</td>
            <td>${stud.SBIRTHDAY}</td>
            <td>${stud.CNO}</td>
            <td>${stud.CNAME}</td>
            <td>${stud.GRADE}</td>
            <td>${stud.TNO}</td>
            <td>${stud.TNAME}</td>
            <td>${stud.TSEX}</td>
            <td>${stud.TBIRTHDAY}</td>
            <td>${stud.TDEPART}</td>
            <td>${stud.TTITLE}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
