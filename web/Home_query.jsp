<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/5/7
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>基本查询</title>
</head>
<body>
<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="0">
    <h4>1.	能查询学生姓名、性别和班级信息</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="1">
    <h4>2.	能查询教师所有的单位</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="2">
    <h4>3、 能查询学生的所有信息</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="3">
    <h4>4、 查询出成绩在一定区间内的学生的信息</h4>
    <table>
    <tr>
        <td>分数</td>
        <td>
            <input type="text" name="low">
        </td>
    </tr>
    <tr>
        <td>分数</td>
        <td>
            <input type="text" name="high">
        </td>
    </tr>


    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="4">
    <h4>5、 能查询某个成绩的学生的信息</h4>
    <tr>
        <td>分数</td>
        <td>
            <input type="text" name="low">
        </td>
    </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="5">
    <h4>6、 能按照班级或性别来查询学生信息</h4>
    <table>
    <tr>
        <td>班级</td>
        <td>
            <input type="text" name="class">
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


    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post" name="form1">
    <script>
        //定义了字段的二维数组，里面的顺序跟表的顺序是相同的。通过selectedIndex获得表的下标值来得到相应的字段数组
        var fields=[
            ["sno","sname","ssex","sbirthday","sclass"],
            ["tno","tname","tsex","tbirthday","ttitle","tdepart"],
            ["tno","cno"],
            ["sno","cno","grade"],
            ["cno","cname"]
        ];

        function getFields(){
            //获得表名下拉框的对象
            var sltTables=document.form1.tables;
            //获得字段名下拉框的对象
            var sltFields=document.form1.fields1;
            var sltFields2=document.form1.fields2;

            //得到对应表的字段数组
            var tablesFields=fields[sltTables.selectedIndex-1];

            //清空字段下拉框，仅留提示选项
            sltFields.length=1;
            sltFields2.length=1;

            //将字段数组中的值填充到字段下拉框中
            for(var i=0;i<tablesFields.length;i++){
                //Option(选项描述，选项值) 等价于 <option value="选项值" >选项描述</option>;
                sltFields[i+1]=new Option(tablesFields[i],tablesFields[i]);
                sltFields2[i+1]=new Option(tablesFields[i],tablesFields[i]);
            }
        }
    </script>
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="6">
    <h4>7.能以某个字段的升序，同时以某个字段的降序来查询某张表（任选某个表）</h4>
    <table>
    <tr>
        <td width="-10%" align="left">
            <select name="tables" onchange="getFields()">
                <option value="">请选择表</option>
                <option value="STUDENT">学生表</option>
                <option value="TEACHER">教师表</option>
                <option value="TEACH">TEACH表</option>
                <option value="SCORE">成绩表</option>
                <option value="COURSE">课程表</option>
            </select>
            <select name="fields1">
                <option value="">请选择字段</option>
            </select>

            <select name="fields2">
                <option value="">请选择字段</option>
            </select>
        </td>
    </tr>



    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="7">
    <h4>8、 能统计某个班的人数</h4>
    <table>
    <tr>
        <td>班级</td>
        <td>
            <input type="text" name="class">
        </td>
    </tr>


    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="8">
    <h4>9、 能查询最高分的学生学号和课程号</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="9">
    <h4>10.能查询每门课的平均成绩</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post" name="form10">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="10">
    <h4>11、能查询至少有n名学生选修的并以m开头的课程的平均分数。</h4>
    <table>
        <tr>同学人数</tr>
        <tr>
            <input type="number" name="n1">
        </tr>
        <tr>
            <script type="text/javascript" src="jquery-3.3.1.js"></script>
            <script type="text/javascript">//为模糊查询增加%
                $(document).ready(function(){
                    $("button").click(function(){
                        var param=$("#n2").val();
                        $("#n2").val(param+"%");
                    });
                });
            </script>
            <p>课程号 <input type="text" name="n2" id="n2" /></p>
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="11">
    <h4>12、 能查询某个班学生的平均分</h4>
    <table>
        <tr>班级</tr>
        <tr>
            <input type="number" name="sclass">
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="12">
    <h4>13、假设建立了一个grade表,现查询所有同学的Sno、Cno和rank列</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="13">
    <h4>14、能查询选修某个课程的成绩高于某个学号同学成绩的所有同学的记录</h4>
    <table>
        <tr>课程</tr>
        <tr>
            <input type="text" name="cno">
        </tr>
        <tr>学号</tr>
        <tr>
            <input type="text" name="sno">
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="14">
    <h4>15、能查询跟某学号的同学同年出生的所有学生的学生信息</h4>
    <table>
        <tr>学号</tr>
        <tr>
            <input type="text" name="sno">
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="15">
    <h4>16、能查询某位教师任课的学生成绩</h4>
    <table>
        <tr>教师姓名</tr>
        <tr>
            <input type="text" name="tname">
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="16">
    <h4>17、能查询选修某课程的同学人数多于n人的教师姓名</h4>
    <table>
        <tr>人数</tr>
        <tr>
            <input type="text" name="number">
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="17">
    <h4>18、能查询存在有某个分数（比如85分）以上成绩的课程号</h4>
    <table>
        <tr>分数</tr>
        <tr>
            <input type="text" name="number">
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="18">
    <h4>19、能查询出某个系教师所教课程的成绩表</h4>
    <table>
        <tr>系名</tr>
        <tr>
            <input type="text" name="tdepart">
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="19">
    <h4>20、能查询某个选修编号的课程且成绩至少高于另一个选修编号课程的同学的学号、课程号以及成绩，并按成绩从高到低次序排序</h4>
    <table>
        <tr>课程名1</tr>
        <tr>
            <input type="text" name="cno1">
        </tr>
        <tr>课程名2</tr>
        <tr>
            <input type="text" name="cno2">
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="20">
    <h4>21、能查询所有某性别教师和某性别同学的姓名、性别和出生年月</h4>
    <table>
        <tr>
            <td>教师性别</td>
            <td>
                <select name="ssex">
                    <option value="">==请选择性别==</option>
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>学生性别</td>
            <td>
                <select name="ssex2">
                    <option value="">==请选择性别==</option>
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
            </td>
        </tr>

    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="21">
    <h4>22、 能查询成绩比该课程平均成绩低的同学的成绩表</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="22">
    <h4>23、能查询所有任课教师的姓名、部门和职称</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="23">
    <h4>24 、能查询所有未讲课的教师的姓名和部门</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="24">
    <h4>25、查询不姓“?”的同学记录</h4>
    <table>
        <tr>
            <script type="text/javascript" src="jquery-3.3.1.js"></script>
            <script type="text/javascript">//为模糊查询增加%
            $(document).ready(function(){
                $("button").click(function(){
                    var param1=$("#sname").val();
                    $("#sname").val(param1+"%");
                });
            });
            </script>
            <p>姓 <input type="text" name="sname" id="sname" /></p>
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="25">
    <h4>26、查询学生最大和最小的出生日期值</h4>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="26">
    <h4>27、查询某性别教师及其所上的课程</h4>
    <table>
        <tr>
            <td>教师性别</td>
            <td>
                <select name="ssex">
                    <option value="">==请选择性别==</option>
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
            </td>
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="27">
    <h4>28、查询和某同学相同性别的所有同学的姓名</h4>
    <table>
        <tr>
            <td>学生姓名</td>
            <td>
                <input type="text" name="sname">
            </td>
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div><br>
    </div>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="28">
    <h4>28.2查询和“李军”同性别并同班的同学姓名</h4>
    <table>
        <tr>
            <td>学生姓名</td>
            <td>
                <input type="text" name="sname">
            </td>
        </tr>
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </div><br>
</form>

<form action="<c:url value="/MoreServlet"/>" method="post">
    <input type="hidden" name="method" value="queryHome">
    <input type="hidden" name="index" value="29">
    <h4>29、查询所有选修某课程的某性别同学的成绩表</h4>
    <table>
        <tr>
            <td>课程名</td>
            <td>
                <input type="text" name="cname">
            </td>
        </tr>
        <tr>
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
    </table>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </div><br>
</form>

</body>
</html>
