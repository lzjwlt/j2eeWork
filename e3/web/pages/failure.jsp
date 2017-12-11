<%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/3
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%-- session错误代码
1.
2.
3.
4.
5.
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Failure</title>
</head>
<body>
<h1>发现错误</h1><br/>
<h3>
    <%
        String errorInfo =(String)session.getAttribute("ErrorInfo");
        if(errorInfo != null){
            out.print(errorInfo);
        }
    %></h3>
<br/><input type="button" value="返回主页" onclick="window.location.href='<%=request.getContextPath()%>'" />

</body>
</html>
