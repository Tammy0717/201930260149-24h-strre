<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>



<%
 if(session.getAttribute("flag")==null || session.getAttribute("flag").equals(""))
 {
  
 String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":"
+ request.getServerPort() + path + "/";
response.sendRedirect(basePath + "pa/loading.action");
  
  
 }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>the demo jsp title!</title>
</head>
<body>

<img width="520" height="520" src="${ pageContext.request.contextPath }/tubiao/data.png">
</body>
</html>






