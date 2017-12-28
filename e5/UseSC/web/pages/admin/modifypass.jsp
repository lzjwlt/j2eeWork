<%@ page import="ustc.lzj.UserDAO" %>
<%@ page import="ustc.lzj.UserBean" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %><%--
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
    String id = request.getParameter("id");
    String pass = request.getParameter("pass");
    String sql = String.format("UPDATE USER SET userpass=\'%s\' WHERE id=\'%s\'",pass,id);
    UserDAO userDAO =new UserBean().getUserDAO();
    try{
        Connection connection = userDAO.openDBConnection();
        Statement statement = connection.prepareStatement(sql);
        statement.execute(sql);
        out.println("修改成功");
    }
    catch (SQLException e){
        out.println("修改失败");
        e.printStackTrace();
    }
    finally {
        userDAO.closeDBConnection();
    }

%>
<br/>
<input type="button" value="返回管理页面" onclick="window.location.href='<%=request.getContextPath()%>/pages/admin/admin.jsp'" />
</body>
</html>
