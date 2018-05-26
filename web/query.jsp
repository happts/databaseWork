<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
</style>
<%--每一个查询的相对位置--%>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>单表搜索</title>
</head>
<body>
    <h3 align="center">单表搜索</h3>
    <h4 class="student">学生表</h4>
    <form action="<c:url value="/StudentServlet"/>" method="post">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="20%" style="margin-left: 100px">
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
                <td>性别</td>
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
        </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    <h4 class="score">成绩表</h4>
    <form action="<c:url value="/ScServlet"/>" method="post" class="score">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="20%" style="margin-left: 100px">

            <tr>
                <td width="100px">学生学号</td>
                <td width="40%">
                    <input type="text" name="sno">
                </td>
            </tr>
            <tr>
                <td width="100px">课程号</td>
                <td width="40%">
                    <input type="text" name="cno">
                </td>
            </tr>
            <tr>
                <td width="100px">分数</td>
                <td width="40%">
                    <input type="text" name="grade">
                </td>
            </tr>
        </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    <h4 class="teacher">教师表</h4>
    <form action="<c:url value="/TeacherServlet"/>" method="post" class="teacher">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="20%" style="margin-left: 100px">

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
        </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    <h4 class="course">课程表</h4>
    <form action="<c:url value="/CoServlet"/>" method="post" class="course">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="20%" style="margin-left: 100px">

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
        </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    <h4 class="teach">TEACH</h4>
    <form action="<c:url value="/TeachServlet"/>" method="post" class="teach">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="20%" style="margin-left: 100px">
            <tr>
                <td width="100px">教师编号</td>
                <td width="40%">
                    <input type="text" name="tno">
                </td>
            </tr>
            <tr>
                <td width="100px">课程号</td>
                <td width="40%">
                    <input type="text" name="cno">
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
