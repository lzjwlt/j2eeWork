<%@ page import="sc.ustc.controller.Path" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %><%--
  Created by IntelliJ IDEA.
  User: lzjwlt
  Date: 2017/12/13
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>view_logfile</title>
</head>
<body>
<textarea rows="50" cols="150"><%
    String logPath = request.getServletContext().getRealPath("/WEB-INF/log/log.xml");
    if(Path.getInstance().getLogfilePath() != null){
        logPath = Path.getInstance().getLogfilePath();
    }
    File file = new File(logPath);
    if(file.exists() == false){
        out.print("日志文件为空");
    }
    else {
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = bReader.readLine()) != null) {
            sb.append(s + "\n");
        }
        bReader.close();
        String str = sb.toString();
        out.print(str);
    }
%></textarea>

</body>
</html>
