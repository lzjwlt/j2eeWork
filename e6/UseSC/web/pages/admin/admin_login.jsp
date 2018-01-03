<%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/4
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h2>验证身份：</h2>
<h4>账号admin,密码admin</h4>
<form action="<%=request.getContextPath()%>/admin.sc" method="post">
    <input type="text" name="adminName"/><br/>
    <input type="password" name="password"/><br/>
    <input type="submit"/>
</form>

</body>
</html>
