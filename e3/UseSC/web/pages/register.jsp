<%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/3
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1><br/>
<form action="<%=request.getContextPath()%>/register.sc" method="post">
    用户名:<input type="text" name="name"/><br/><br/>
    密&nbsp;码:<input type="password" name="password"/><br/><br/>
    <input type="submit"/>
</form>

</body>
</html>
