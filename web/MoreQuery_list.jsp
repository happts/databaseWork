<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MJWmj
  Date: 2018/4/24
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>多表查询结果</title>
</head>
<body>
<div class="container">

    <c:if test="${empty requestScope.list}">
        <p align="center">没有符合条件的数据</p>
    </c:if>
    <c:if test="${!empty requestScope.list}">
        <h3 align="center" >列表</h3>
        <table border="1" width="70%" align="center" class="table table-hover">
        <c:forEach items="${requestScope.list.get(0)}" var="subMap">
            <th>${subMap.key}</th>
        </c:forEach>

        <c:forEach items="${requestScope.list}" var="Map">
            <tr >
                <c:forEach items="${Map}" var="entry">
                    <td>${entry.value}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </c:if>


</table>
</div>
</body>
</html>
