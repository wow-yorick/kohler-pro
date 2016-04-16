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
            	<div class="col-md-4">雅悦荟会员</div>
                <div class="col-md-2">Staus</div>
            	<div class="col-md-4">${AccountView.accountstatus}</div>
            </div>
        	<div class="row">
            	<div class="col-md-2">Name</div>
            	<div class="col-md-4">${AccountView.REAL_NAME}</div>
                <div class="col-md-2">Mobile</div>
            	<div class="col-md-4">${AccountView.ACCOUNT_NAME}</div>
            </div>
            <div class="row">
            	<div class="col-md-2">Email</div>
            	<div class="col-md-4">${AccountView.EMAIL}</div>
            </div> 
            <div class="row">
            	<div class="title">Residential Address</div>
            </div>
            <div class="row">
            	<div class="col-md-2">Province</div>
            	<div class="col-md-4">${AccountView.PROVINCE}</div>
                <div class="col-md-2">City</div>
            	<div class="col-md-4">${AccountView.CITY}</div>
            </div>
        	<div class="row">
            	<div class="col-md-2">District</div>
            	<div class="col-md-4">${AccountView.DISTRICT}</div>
            </div>
            <div class="row">
                <div class="col-md-2">Address</div>
            	<div class="col-md-4">${AccountView.ADDRESS}</div> 
            </div>
            <div class="row">
            	<div class="title">Credit Point</div>
            </div> 
            <div class="row">
            	<div class="col-md-2">Point</div>
            	<div class="col-md-4">${AccountView.CREDIT_POINT}</div>
            </div> 
            <c:if test="${type == 2 }">
		        <div class="row">
					<div class="col-md-2 line">Freeze Reason</div>
					<div class="col-md-10">
						<textarea id="freezeReason"  name="freezeReason">${AccountView.freezeReason}</textarea>
					</div>
				</div>
			</c:if> 	
        </div>
        <div class="clear"></div>  
                    
        <div class="btns">
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
    </script>

</div>
</body>
</html>
