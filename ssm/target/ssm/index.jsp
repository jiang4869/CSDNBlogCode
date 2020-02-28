<%--
  Created by IntelliJ IDEA.
  User: jiangxiaoju
  Date: 2020/2/27
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="account/findAll">测试</a>

<br>

<form action="account/transfer" method="post">
    姓名：<input type="text" name="sourceName" /><br/>
    姓名：<input type="text" name="targetName" /><br/>
    金额：<input type="text" name="money" /><br/>
    <input type="submit" value="保存"/><br/>
</form>

</body>
</html>
