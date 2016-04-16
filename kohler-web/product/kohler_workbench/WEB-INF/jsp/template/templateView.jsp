<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>模板详情</title>
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
</head>
<body>
	<form id="editForm">
		<input type="hidden" name="fileName" value="${fileName }"/>
		<div align="center">
			<div>
				<textarea cols="100" rows="35" name="content">${content}</textarea>
			</div>
			<div style="height: 10px"></div>
			<div>
				<input type="button"  value="提交" onclick="editTemplate()"/>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function editTemplate(){
		$.post("edit.action",$("#editForm").serialize(), function(data){
			var result = eval(data);
			alert(result.msg);
			location.href = "list.action";
		  },"json");
		
	}

</script>
</html>
