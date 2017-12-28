<%@ page import="ustc.lzj.UserBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/4
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%
    String authority = (String)session.getAttribute("Authority");
    if(authority==null || !(authority.equals("admin"))){
        session.setAttribute("ErrorInfo","非法进入");
        request.getRequestDispatcher("/pages/failure.jsp").forward(request,response);
        return;
    }
%>
<h3>当前已注册账号：</h3>
<%
    out.println("\n" +
            "<table border=\"1\">\n" +
            "  <tr>\n" +
            "    <th> &nbsp; id &nbsp; </th>\n" +
            "    <th> &nbsp; UserName &nbsp; </th>\n" +
            "    <th> &nbsp; Password &nbsp; </th>\n" +
            "  </tr>\n");

    UserBean userBean = new UserBean();
    try {
        Connection connection = userBean.getUserDAO().openDBConnection();
        String sql="SELECT * FROM USER";
        Statement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            out.println("<tr>\n"+
                    "<td>"+resultSet.getInt(1)+"</td>\n"+
                    "<td>"+resultSet.getString(2)+"</td>\n"+
                    "<td>"+resultSet.getString(3)+"</td>\n"+
                    "</tr>\n");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        userBean.getUserDAO().closeDBConnection();
    }
    out.println("</table>");
%>
<br/>
<h3>删除用户</h3>
<form action="pages/admin/deluser.jsp" method="post">
    请输入要删除的账号<input type="number" name="id"/>
    <input type="submit" value="删除"/>
</form>
<br/>
<h3>修改密码</h3>
<form action="pages/admin/modifypass.jsp" method="post">
    ID:<input type="number" name="id"/>
    新密码：<input type="password" name="pass"/>
    <input type="submit" value="修改密码"/>
</form>
<br/>
<input type="button" value="返回主页" onclick="window.location.href='<%=request.getContextPath()%>'" />
</body>
</html>
