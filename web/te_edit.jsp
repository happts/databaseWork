<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/25
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑教师</title>
</head>
<body>
<h3 align="center" >编辑教师</h3>
<form action="<c:url value='/TeacherServlet'/>" method="post">
    <input type="hidden" name="method" value="edit">
    <input type="hidden" name="tno" value="${teacher.tno}"/>
    <table border="0" align="center" width="40%" style="margin-left: auto">
        <tr>
            <td>教师编号</td>
            <td align="left">${teacher.tno}</td>

        </tr>
        <tr>
            <td width="100px">教师姓名</td>
            <td width="40%">
                <input type="text" name="tname" value="${teacher.tname}"/>
            </td>
            <td align="left">
                <label id="tnameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>教师性别</td>
            <td>
                <input type="radio" name="tsex" value="male" id="male" <c:if test="${teacher.tsex eq 'male'}"/>checked="checked"/>
                <label for="male">男</label>
                <input type="radio" name="tsex" value="female" id="female" <c:if test="${teacher.tsex eq 'female'}"/>checked="checked"/>
                <label for="female">女</label>
            </td>
            <td>
                <label id="tsexError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>教师生日</td>
            <td>
                <input type="text" name="tbirthday" id="tbirthday" value="${teacher.tbirthday}"/>
            </td>
            <td>
                <label id="tbirthdayError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>教师所在系</td>
            <td>
                <input type="text" name="tdepart" id="tdepart" value="${teacher.tdepart}"/>
            </td>
            <td>
                <label id="tdepartError"class="error">&nbsp;</label>
            </td>
        </tr>

        <tr>
            <td>教师职称</td>
            <td>
                <input type="text" name="ttitle" id="ttitle" value="${teacher.ttitle}"/>
            </td>
            <td>
                <label id="ttitleError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td> </td>
            <td>
                <input type="submit" name="submit" value="完成"/>
                <input type="reset" name="reset"/>
            </td>
        </tr>
    </table>
</form>


</body>
</html>
