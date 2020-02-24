<%--
  Created by IntelliJ IDEA.
  User: jiangxiaoju
  Date: 2020/2/24
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<form method="post" action="${pageContext.request.contextPath}/loginServlet">
  <label>username:</label>
  <input type="text" name="username">
  <br/>
  <label>password:</label>
  <input type="text" name="password">
  <br/>
  <button type="submit">确定</button>
</form>
  </body>
</html>
