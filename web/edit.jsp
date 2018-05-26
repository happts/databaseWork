<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">编辑学生</h3>
<form action="<c:url value='/StudentServlet'/>" method="post" >
    <input type="hidden" name="method" value="edit"/>
    <input type="hidden" name="sno" value="${student.sno}"/>
    <table border="0" align="center" width="40%" style="margin-left: 100px">
        <tr>
            <td>学生学号</td>
            <td align="left">${student.sno}</td>

        </tr>
        <tr>
            <td width="100px">学生名称</td>
            <td width="40%">
                <input type="text" name="sname" value="${student.sname}"/>
            </td>
            <td align="left">
                <label id="nameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>学生性别</td>
            <td>
                <input type="radio" name="ssex" value="male" id="male" <c:if test="${student.ssex eq 'male'}"/>checked="checked"/>
                <label for="male">男</label>
                <input type="radio" name="ssex" value="female" id="female" <c:if test="${student.ssex eq 'female'}"/>checked="checked"/>
                <label for="female">女</label>
            </td>
            <td>
                <label id="ssexError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>学生班级</td>
            <td>
                <input type="text" name="sclass" id="sclass" value="${student.sclass}"/>
            </td>
            <td>
                <label id="sclassError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>学生生日</td>
            <td>
                <input type="date" name="sbirthday" id="sbirthday" value="${student.sbirthday}"/>
            </td>
            <td>
                <label id="sbirthdayError"class="error">&nbsp;</label>
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
