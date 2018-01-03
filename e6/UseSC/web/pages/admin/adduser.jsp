<%--
  Created by IntelliJ IDEA.
  User: lzj
  Date: 18/1/3
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="sc.ustc.dao.Conversation" %>
<%@ page import="ustc.lzj.UserBean" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add user</title>
</head>
<body>

<jsp:useBean id="userBean"  class="ustc.lzj.UserBean"/>

<%
    String user = request.getParameter("user");
    String pass = request.getParameter("pass");
    userBean.setUserName(user);
    userBean.setUserPass(pass);
    if(Conversation.insert(userBean,false))
        out.println("添加成功");
    else {
        out.println("修改失败<br/>");
    }
%>
<br/>
<input type="button" value="返回管理页面" onclick="window.location.href='<%=request.getContextPath()%>/pages/admin/admin.jsp'" />
</body>
</html>

