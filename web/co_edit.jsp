<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/27
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">编辑课程表</h3>
<form action="<c:url value='/CoServlet'/>" method="post" >
    <input type="hidden" name="method" value="edit"/>
    <input type="hidden" name="cno" value="${co.cno}"/>
    <table border="0" align="center" width="40%" style="margin-left: auto">
        <tr>
            <td>课程号</td>
            <td>${sc.cno}</td>
        </tr>
        <tr>
            <td width="100px">课程名</td>
            <td width="40%">
                <input type="text" name="cname" value="${co.cname}"/>
            </td>
            <td align="left">
                <label id="cnameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td width="100px">教师编号</td>--%>
            <%--<td width="40%">--%>
                <%--<input type="text" name="tno" value="${co.tno}"/>--%>
            <%--</td>--%>
            <%--<td align="left">--%>
                <%--<label id="tnoError" class="error">&nbsp;</label>--%>
            <%--</td>--%>
        <%--</tr>--%>
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

