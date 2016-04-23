<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="<%=path %>/static/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">  
    i = 1;  
    j = 1;  
    $(document).ready(function(){  
          
        $("#btn_add1").click(function(){  
            document.getElementById("newUpload1").innerHTML+='<div id="div_'+i+'"><input  name="file" type="file"  /><input type="button" value="删除"  onclick="del_1('+i+')"/></div>';  
              i = i + 1;  
        });  
          
        $("#btn_add2").click(function(){  
            document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';  
              j = j + 1;  
        });  
    });  
  
    function del_1(o){  
     document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));  
    }  
      
    function del_2(o){  
         document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));  
    }
    
    $(function(){
    	setInterval(function(){
    		$.get('<%=path %>/upfile/progress',function(data){console.debug(data);})
    	},1000);
    });
</script>
  </head>
  
  <body>
    <form action="<%=path %>/save" method="post">
    	<input type="text" name="username" />
    	<button type="submit">提交</button>
    </form>
    
    <h1>springMVC字节流输入上传文件</h1>   
    <form name="userForm1" action="<%=path %>/upload" enctype="multipart/form-data" method="post">  
        <div id="newUpload1">  
            <input type="file" name="file">  
        </div>  
        <input type="button" id="btn_add1" value="增加一行" >
        <input type="text" name="currentPage" value="1" />
        <input type="text" name="pageSize" value="10" />
        <input type="submit" value="上传" >  
    </form>   
    <br>  
    <br>  
    <hr align="left" width="60%" color="#FF0000" size="3">  
    <br>  
    <br>  
     <h1>springMVC包装类上传文件</h1>   
    <form name="userForm2" action="<%=path %>/upload2" enctype="multipart/form-data" method="post"">  
        <div id="newUpload2">  
            <input type="file" name="file">  
        </div>  
        <input type="button" id="btn_add2" value="增加一行" >  
        <input type="submit" value="上传" >  
          
          
    </form>
  </body>
</html>
