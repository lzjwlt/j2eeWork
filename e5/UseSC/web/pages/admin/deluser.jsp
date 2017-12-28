<%@ page import="ustc.lzj.UserBean" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="ustc.lzj.UserDAO" %><%--
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
    String id = request.getParameter("id");
    String sql = "DELETE FROM user WHERE ID="+id;
    UserBean userBean = new UserBean();
    try {
        Connection connection = userBean.getUserDAO().openDBConnection();
        Statement statement = connection.prepareStatement(sql);
        statement.execute(sql);
        out.print(id+"删除成功");
    }
    catch (Exception e){
        e.printStackTrace();
        out.print("删除失败");
    }
%>
<br/>
<input type="button" value="返回管理页面" onclick="window.location.href='<%=request.getContextPath()%>/pages/admin/admin.jsp'" />

</body>
</html>
