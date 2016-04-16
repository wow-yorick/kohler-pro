<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登录页</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/validate-methods.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>

<div class="login">
<div class="container">
    <div class="header">
        <h1 class="logo"><a href="#"></a></h1>
    </div>
    <div class="content">
    	<form id="loginForm">
	        <div class="main">
	            <div class="user row">
	                <div class="col-md-2">Account</div>
	                <div class="col-md-4"><input type="text"  name="userName" id="userName" required="required"/></div>
	            </div>
	            <div class="pwd row">
	                <div class="col-md-2">Password</div>
	                <div class="col-md-4"><input type="password" name="password" id="password" required="required"/></div>
	            </div>
	            <div class="btn">
	                <a href="javascript:void(0)" onclick="login()">Login</a>
	            </div>
	       </div>
       </form>
       <div id="errorMessage" style="display: none"></div>
    </div>
</div>
</div>
<script type="text/javascript">
	function login(){
	$("#errorMessage").html("");
	if ($("#loginForm").valid()) {	
		$.ajax({
			url:"dologin.action",
			data: $("#loginForm").serialize(),
			type:'post',
			success:function(data){
				var result = eval(data);
				if(result.status=="0"){
					window.location.href="${base}/common/index.action";
				}else{
					alert(result.msg);
				}
			},
		});
	}else{
		alert($("#errorMessage").html());
	}
}
	
	
</script>
</body>
</html>



