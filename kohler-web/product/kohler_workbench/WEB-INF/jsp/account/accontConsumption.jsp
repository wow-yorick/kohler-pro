<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account View</title>
<jsp:include page="../common/common.jsp" />
</head>
</head>

<body>
<div class="container admin_roles">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Accout Type</div>
            	<div class="col-md-4">${AccountView.accounttype}</div>
                <div class="col-md-2">Staus</div>
            	<div class="col-md-4">${AccountView.accountstatus}</div>
            </div>
        	<div class="row">
            	<div class="col-md-2">Name</div>
            	<div class="col-md-4">${AccountView.REAL_NAME}</div>
            	<div class="col-md-2">Mobile</div>
            	<div class="col-md-4">${AccountView.ACCOUNT_NAME}</div>    
            </div>  
        <div class="clear"></div>
       	<c:if test="${restpassword == 1 }">
       		 <div class="row">
            	<div class="col-md-2">New Password</div>
                <div class="col-md-4"> <input name="newpassword" id="passsword"  type="password" /></div> 
            </div>  
            <div class="row">
            	<div class="col-md-2">Confirm Password</div>
                <div class="col-md-4"> <input name="oldpassword" id="oldpassword" type="password" /></div> 
            </div>   	
       	</c:if>
        <c:if test="${type == 2 }">
	        <div class="row">
				<div class="col-md-2 line">Freeze Reason</div>
				<div class="col-md-10">
				<textarea id="freezeReason"  name="freezeReason">${AccountView.freezeReason}</textarea>
				</div>
			</div>
		</c:if>            
        <div class="btns">
        	<c:if test="${type == 2 }">
        		<a href="#" class="confirm" onclick="editsave();">Save</a>
        	</c:if>
        	<c:if test="${restpassword == 1 }">
        		<a href="#" class="confirm" onclick="resetsave();">Save</a>
        	</c:if>
        	<a href="#" class="cancel">Close</a>
        </div>
    </div>
    <!--main结束-->
   <!-- 关闭frame-->
       <script  type="text/javascript">
    		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function(){
    			parent.layer.close(index); //执行关闭
			});
			<c:if test="${type == 2 }">
				UeditorFileLoad.init("freezeReason");
			</c:if>
			function editsave()
			{   
				var id="${AccountView.ACCOUNT_ID}",freezeReason=$("#freezeReason").html();
				$.post("freezeSave.action", "accountId=" + id+ "&freezeReason="
						+ freezeReason, function(data) {
					var result = eval(data);
					alert(result.msg);
					var index = parent.layer.getFrameIndex(window.name);
					parent.location.reload();
					parent.layer.close(index);
				}, "json");
			}
			function resetsave(){
				var id="${AccountView.ACCOUNT_ID}",freezeReason=$("#freezeReason").html();
				if($("#passsword").val() == '' &&  $("#passsword").val() != $("#oldpassword").val()){
							alert('Password is Error!');
							return false;
				} 
				$.post("resetPasswordSave.action", "accountId=" + id+ "&password="
						+ $("#passsword").val(), function(data) {
					var result = eval(data);
					alert(result.msg);
					var index = parent.layer.getFrameIndex(window.name);
					parent.location.reload();
					parent.layer.close(index);
				}, "json");
			}
    </script>

</div>
</body>
</html>
