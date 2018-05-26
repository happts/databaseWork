<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/23
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">编辑成绩表</h3>
<form action="<c:url value='/ScServlet'/>" method="post" >
    <input type="hidden" name="method" value="edit"/>
    <input type="hidden" name="sno" value="${sc.sno}"/>
    <input type="hidden" name="cno" value="${sc.cno}"/>
    <table border="0" align="center" width="40%" style="margin-left: auto">
        <tr>
            <td>学生学号</td>
            <td>${sc.sno}</td>
        </tr>
        <tr>
            <td>课程号</td>
            <td>${sc.cno}</td>
        </tr>
        <tr>
            <td width="100px">成绩</td>
            <td width="40%">
                <input type="text" name="grade" value="${sc.grade}"/>
            </td>
            <td align="left">
                <label id="gradeError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="submit" value="完成"/>
                <input type="reset" name="reset"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
