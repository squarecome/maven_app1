<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.fanglai.model.Test"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. Home<br>
    <%
    	List<Test> tests = (List<Test>)request.getAttribute("tests");
    	for(int i = 0;i<tests.size();i++){
    		out.println(tests.get(i).getName());
    		out.println("<br/>");
    		for(int j = 0;j<tests.get(i).getOrders().size();j++){
    			out.println(tests.get(i).getOrders().get(j).getName());
        		out.println("<br/>");
    		}
    	}
    %>
  </body>
</html>