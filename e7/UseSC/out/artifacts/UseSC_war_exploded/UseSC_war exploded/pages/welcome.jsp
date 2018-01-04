<%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/3
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>登录结果</h1><br/>
用户：<%=session.getAttribute("userName")%> 登录成功！<br/>
<br/><input type="button" value="返回主页" onclick="window.location.href='<%=request.getContextPath()%>'" />
</body>
</html>
