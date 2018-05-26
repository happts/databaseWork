<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>

</head>
<body>

<div>
<h3>添加学生信息</h3>
<form action="<c:url value='/StudentServlet'/>" method="post" role="form" class="form-horizontal" >
    <div class="form-group">
    <input type="hidden" name="method" value="add">
    <table border="0" align="left" width="40%" style="margin-left: 100px" >
        <tr>
            <td width="100px">学生学号</td>
            <td width="50%">
                <input type="text" name="sno" class="form-control" value="${form.sno}"/>${form.error.sno}
            </td>
            <td align="left">
                <label id="snoError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td width="100px">学生名称</td>
            <td width="50%">
                <input type="text" name="sname" class="form-control" value="${form.sname}"/>${form.error.sname}
            </td>
            <td align="left">
                <label id="nameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>学生性别</td>
            <td>
                <input type="radio" name="ssex" value="male" id="male"/>
                <label for="male">男</label>
                <input type="radio" name="ssex" value="female" id="female"/>
                <label for="female">女</label>
                ${form.error.ssex}
            </td>
            <td>
                <label id="ssexError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>学生班级</td>
            <td width="50%">
                <input type="text" name="sclass" id="sclass" class="form-control" value="${form.sclass}" />${form.error.sclass}
            </td>
            <td>
                <label id="sclassError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>学生生日</td>
            <td width="60%">
                <input type="text" name="sbirthday" id="sbirthday" class="form-control" value="${form.sbirthday}" />${form.error.sbirthday}
            </td>
            <td>
                <label id="sbirthdayError"class="error">&nbsp;</label>
            </td>
        </tr>
    </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </div>
</form>
</div>
<div>
<h3>添加成绩表</h3>
<form action="<c:url value='/ScServlet'/>" method="post" class="form-horizontal" >
    <div class="form-group">
    <input type="hidden" name="method" value="add">
    <table border="0" align="left" width="40%" style="margin-left: 100px">
        <tr>
            <td width="100px">学生学号</td>
            <td width="50%">
                <input type="text" name="sno" class="form-control" value="${formsc.sno}"/>${formsc.error.sno}
            </td>
            <td align="left">
                <label id="snooError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td width="100px">课程号</td>
            <td width="50%">
                <input type="text" name="cno" class="form-control" value="${formsc.cno}"/>${formsc.error.cno}
            </td>
            <td align="left">
                <label id="cnoError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>分数</td>
            <td width="60%">
                <input type="number" name="grade" class="form-control" value="null">${formsc.error.sno_cno}
            </td>
            <td>
                <label id="gradeError"class="error">&nbsp;</label>
            </td>
    </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </div>
</form>
</div>
<div>
<h3>添加教师表</h3>
<form action="<c:url value='/TeacherServlet'/>" method="post" class="form-horizontal" >
    <div class="form-group">
    <input type="hidden" name="method" value="add">

    <table border="0" align="left" width="40%" style="margin-left: 100px">

        <tr>
            <td width="100px">教师编号</td>
            <td width="50%">
                <input type="text" name="tno" class="form-control" value="${formt.tno}"/>${formt.error.tno}
            </td>
            <td align="left">
                <label id="tnoError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td width="100px">教师姓名</td>
            <td width="50%">
                <input type="text" name="tname" class="form-control" value="${formt.tname}"/>${formt.error.tname}
            </td>
            <td align="left">
                <label id="tnameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>教师性别</td>
            <td width="50%">
                <input type="radio" name="tsex" value="male" id="tmale"/>
                <label for="male">男</label>
                <input type="radio" name="tsex" value="female" id="tfemale"/>
                <label for="female">女</label>
                ${formt.error.tsex}
            </td>
            <td>
                <label id="tsexError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>教师所在系</td>
            <td>
                <input type="text" name="tdepart" id="tdepart" class="form-control" value="${formt.tdepart}"/>${formt.error.tdepart}
            </td>
            <td>
                <label id="tdepartError"class="error">&nbsp;</label>
            </td>
        </tr>

        <tr>
            <td>职称</td>
            <td>
                <input type="text" name="ttitle" id="ttitle" class="form-control" value="${formt.ttitle}"/>${formt.error.ttitle}
            </td>
            <td>
                <label id="ttitleError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>教师生日</td>
            <td width="60%">
                <input type="text" name="tbirthday" id="tbirthday" class="form-control" value= "${formt.tbirthday}"/>${formt.error.tbirthday}
            </td>
            <td>
                <label id="tbirthdayError"class="error">&nbsp;</label>
            </td>
        </tr>
    </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </div>
</form>
</div>
<div>
<h3>添加课程表</h3>
<form action="<c:url value='/CoServlet'/>" method="post" class="form-horizontal">
    <div class="form-group">
    <input type="hidden" name="method" value="add">
    <table border="0" align="left" width="40%" style="margin-left: 100px">
        <tr>
            <td width="100px">课程号</td>
            <td width="50%">
                <input type="text" name="cno" class="form-control" value="${formc.cno}"/>${formc.error.cno}
            </td>
            <td align="left">
                <label id="cnoorror" class="error">&nbsp;</label>
            </td>
        </tr>

        <tr>
            <td width="100px">课程名</td>
            <td width="40%">
                <input type="text" name="cname" class="form-control" value="${formc.cname}"/>${formc.error.cname}
            </td>
            <td align="left">
                <label id="cnameError" class="error">&nbsp;</label>
            </td>
        </tr>
    </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </div>
</form>
</div>
<div>
<h3>添加TEACH</h3>
<form action="<c:url value='/TeachServlet'/>" method="post" class="form-horizontal">
    <div class="form-group">
    <input type="hidden" name="method" value="add">
    <table border="0" align="left" width="40%" style="margin-left: 100px">
        <tr>
            <td width="100px">教师编号</td>
            <td width="40%">
                <input type="text" name="tno" class="form-control" value="${formteach.tno}"/>${formteach.error.tno}
            </td>
            <td align="left">
                <label id="tnooError" class="error">&nbsp;</label>
            </td>
        </tr>

        <tr>
            <td width="100px">课程号</td>
            <td width="50%">
                <input type="text" name="cno" class="form-control" value="${formteach.cno}"/>${formteach.error.cno}${formteach.error.tno_cno}
            </td>

            <td align="left">
                <label id="cnooorror" class="error">&nbsp;</label>
            </td>
        </tr>
    </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </div>
</form>
</div>

</body>
</html>
