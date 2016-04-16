<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="getCollectionName" prefix="collection"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create User</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css"
	href="../css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="../js/jquery.ztree.all-3.5.min.js"></script>

</head>

<body>
<div class="container admin_user">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <form id="addUserForm">
    <div class="main">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4">&nbsp</div>
            </div>
        	<div class="row">
            	<div class="col-md-2 required">Name</div>
            	<div class="col-md-4"><input type="text" name="realName" required="required" maxlength="25" /></div>
            	<div class="col-md-2 tn_c required">Login Name</div>
            	<div class="col-md-4"><input type="text" name="userName" isUserNameUnique = "true" required="required" maxlength="25" /></div>    
            </div>  
            <div class="row">
            	<div class="col-md-2 required">Password</div>
                <div class="col-md-4"><input type="password" name="password"  required="required" maxlength="25" /></div> 
            	<div class="col-md-2 tn_c required">Confirm Password</div>
                <div class="col-md-4"><input type="password" name="cofirmPassword" equalTo = "input[name='password']" required="required" maxlength="25" /></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 required">Roles</div>
				<div class="col-md-4">
					<select name="userRole" required="required" >
						<option value="">--select--</option>
						<c:forEach items="${allRoles}" var="r">
							<option value="${r.roleId}">${r.roleName}</option>
						</c:forEach>
					</select>
				</div>
            </div> 
               	
        </div>  
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-4">
                        		王明
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:2014-07-01 08:00
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-4">
                        		王明
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:2014-07-01 08:00
                        	 </div>
                        </div>
                    </div>            
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm" onclick="addUser();">Save</a>
        	<a href="#" class="cancel">Cancel</a>
        </div>
    </div>
    </form>
    <div id="errorMessage" style="display: none"></div>
    <!--main结束-->
    
      
</div>
<script>

	//判断userName是否唯一
	jQuery.validator.addMethod("isUserNameUnique", function(value, element) {
	
		var result = false;
	
		// 设置同步
		$.ajaxSetup({
			async : false
		});
		var param = {
			userName : value,
		};
		$.post("unlimited/checkUserName.action", param, function(data) {
			data = eval(data);
			var flag = data.flag;
			result = ("0" == flag);
		}, "json");
		// 恢复异步
		$.ajaxSetup({
			async : true
		});
		return result;
	}, "<spring:message code="info.user.003" arguments="" argumentSeparator=","/>");

	//关闭frame
	$('.cancel').on('click', function(){
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	});
	
	function addUser(){
		$("#errorMessage").html("");
		if ($("#addUserForm").valid()) {
			$.post("createSave.action", $("#addUserForm").serialize(),function(data) {
				var result = eval(data);
				alert(result.msg);
				var index = parent.layer.getFrameIndex(window.name);
				parent.location.reload();
				parent.layer.close(index);
			}, "json");
		}else{
			alert($("#errorMessage").html());
		}
		
	}
	
</script>
</body>
</html>
