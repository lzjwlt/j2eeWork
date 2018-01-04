<%@ page import="sc.ustc.dao.Conversation" %><%--
  Created by IntelliJ IDEA.
  User: lzj
  Date: 2017/12/28
  Time: 下午3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>delete user</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    if( Conversation.delete(id))
        out.println("删除账号 "+id+" 成功");
    else
        out.println("删除失败");
%>
<br/>
<input type="button" value="返回管理页面" onclick="window.location.href='<%=request.getContextPath()%>/pages/admin/admin.jsp'" />

</body>
</html>
