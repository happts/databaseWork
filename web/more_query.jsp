<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/24
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
    <title>Title</title>
</head>
<body>
<h3 align="center">高级搜索</h3>
<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="query2">
    <table border="0" align="center" width="40%" style="margin-left: 100px">
        <tr>
            <td width="100px">学生学号</td>
            <td width="40%">
                <input type="text" name="sno">
            </td>
        </tr>
        <tr>
            <td width="100px">学生名称</td>
            <td width="40%">
                <input type="text" name="sname">
            </td>
        </tr>
        <tr>
            <td>学生性别</td>
            <td>
                <select name="ssex">
                    <option value="">==请选择性别==</option>
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>学生班级</td>
            <td>
                <input type="text" name="sclass"/>
            </td>
        </tr>
        <tr>
            <td>学生生日</td>
            <td>
                <input type="date" name="sbirthday"/>
            </td>
        </tr>
        <tr>
            <td width="100px">课程号</td>
            <td width="40%">
                <input type="text" name="cno">
            </td>
        </tr>
        <tr>
            <td width="100px">课程名</td>
            <td width="40%">
                <input type="text" name="cname">
            </td>
        </tr>
        <tr>
            <td width="100px">分数</td>
            <td width="40%">
                <input type="text" name="grade">
            </td>
        </tr>

        <tr>
            <td width="100px">教师编号</td>
            <td width="40%">
                <input type="text" name="tno">
            </td>
        </tr>
        <tr>
            <td width="100px">教师姓名</td>
            <td width="40%">
                <input type="text" name="tname">
            </td>
        </tr>
        <tr>
            <td>教师性别</td>
            <td>
                <select name="tsex">
                    <option value="">==请选择性别==</option>
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>教师所在系</td>
            <td>
                <input type="text" name="tdepart"/>
            </td>
        </tr>
        <tr>
            <td>职称</td>
            <td>
                <input type="text" name="ttitle"/>
            </td>
        </tr>
        <tr>
            <td>教师生日</td>
            <td>
                <input type="date" name="tbirthday"/>
            </td>
        </tr>
        <tr>
            <td width="100px">教师编号</td>
            <td width="40%">
                <input type="text" name="tno">
            </td>
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </div>
</form>

</body>
</html>
