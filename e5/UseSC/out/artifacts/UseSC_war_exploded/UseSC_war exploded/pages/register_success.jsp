<%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/3
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
</head>
<body>
<h1>注册结果</h1><br/>
用户：<%=session.getAttribute("userName")%>注册成功！<br/>
点击按钮自动登录<input type="button" value="登录" onclick="window.location.href='pages/welcome.jsp'"/>
</body>
</html>
