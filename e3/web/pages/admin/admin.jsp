<%@ page import="ustc.lzj.PasswdMap" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %><%--
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
    PasswdMap pm = new PasswdMap(request);
    out.println("\n" +
            "<table border=\"1\">\n" +
            "  <tr>\n" +
            "    <th> &nbsp; UserName &nbsp; </th>\n" +
            "    <th> &nbsp; Password &nbsp; </th>\n" +
            "  </tr>\n");
    HashMap hashMap = pm.getHashMap();
    Iterator iter = hashMap.entrySet().iterator();
    while (iter.hasNext()){
        Map.Entry entry = (Map.Entry)iter.next();
        Object key = entry.getKey();
        Object val = entry.getValue();
        out.println("<tr>\n"+
                        "<td>"+key+"</td>\n"+
                        "<td>"+val+"</td>\n"+
                    "</tr>\n");
    }
    out.println("</table>");
%>
<br/><input type="button" value="返回主页" onclick="window.location.href='<%=request.getContextPath()%>'" />
</body>
</html>
