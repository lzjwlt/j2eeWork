<%@ page import="ustc.lzj.UserBean" %>
<%@ page import="sc.ustc.controller.Path" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sc.ustc.dao.Conversation" %><%--
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

    List<UserBean> userBeans = new ArrayList<>();
    int maxId = 200;
    for(int i=1; i<=maxId; ++i){
        UserBean userBean = Conversation.query(i);
        if(userBean == null)
            continue;
        userBeans.add(userBean);
    }
    for(UserBean userBean : userBeans){
            out.println("<tr>\n"+
                    "<td>"+userBean.getId()+"</td>\n"+
                    "<td>"+userBean.getUserName()+"</td>\n"+
                    "<td>"+userBean.getUserPass()+"</td>\n"+
                    "</tr>\n");
        }
    out.println("</table>");
%>
<br/>
<h3>删除用户</h3>
<form action="<%=Path.getInstance().getContextPath()%>/pages/admin/deluser.jsp" method="post">
    请输入要删除的账号ID:<input type="number" name="id"/>
    <input type="submit" value="删除"/>
</form>
<br/>
<h3>修改密码</h3>
<form action="<%=Path.getInstance().getContextPath()%>/pages/admin/modifypass.jsp" method="post">
    ID:<input type="number" name="id"/>
    新密码：<input type="password" name="pass"/>
    <input type="submit" value="修改密码"/>
</form>
<br/>
<h3>添加用户</h3>
<form action="<%=Path.getInstance().getContextPath()%>/pages/admin/adduser.jsp" method="post">
    用户名:<input type="text" name="user"/>
    密码：<input type="password" name="pass"/>
    <input type="submit" value="添加"/>
</form>
<br/>
<input type="button" value="返回主页" onclick="window.location.href='<%=request.getContextPath()%>'" />
</body>
</html>
