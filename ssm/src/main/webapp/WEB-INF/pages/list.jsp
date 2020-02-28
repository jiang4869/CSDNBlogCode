<%--
  Created by IntelliJ IDEA.
  User: jiangxiaoju
  Date: 2020/2/27
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>查询了所有账户信息</h3>

<c:forEach items="${list}" var="account">
    ${account}<br/>
</c:forEach>
</body>
</html>
