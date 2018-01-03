<%@ page import="sc.ustc.dao.Conversation" %>
<%--
  Created by IntelliJ IDEA.
  User: lzj
  Date: 17/12/28
  Time: 下午4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modify pass</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String pass = request.getParameter("pass");
    if(Conversation.update(id,"userpass",pass))
        out.println("修改成功");
    else {
        out.println("修改失败");
    }
%>
<br/>
<input type="button" value="返回管理页面" onclick="window.location.href='<%=request.getContextPath()%>/pages/admin/admin.jsp'" />
</body>
</html>
