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
<div class="container admin_condition">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Name</div>
            	<div class="col-md-4">${ContactusView.REAL_NAME}</div>
            	<div class="col-md-2 tn_c">Email</div>
            	<div class="col-md-4">${ContactusView.EMAIL}</div>    
            </div>
        	<div class="row">
            	<div class="col-md-2">Post Time</div>
            	<div class="col-md-4">${ContactusView.CONTACT_DATE}</div>
            	<div class="col-md-2 tn_c">Status</div>
            	<div class="col-md-4">${ContactusView.CONTACT_STATUS}</div>    
            </div>  
            <div class="row">
            	<div class="col-md-2">Message</div>
            	<div class="col-md-10 message">${ContactusView.CONTACT_MESSAGE}</div> 
            </div>
           <c:if test="${type == 1 }"> 
            <div class="row">
            	<div class="col-md-2">Handle User</div>
            	<div class="col-md-4">${ContactusView.HANDLE_USER}</div>
            	<div class="col-md-2 tn_c">Reply Time</div>
            	<div class="col-md-4">${ContactusView.REPLY_DATE}</div>    
            </div>  
            <div class="row">
            	<div class="col-md-2">Message</div>
            	<div class="col-md-10 message">${ContactusView.REPLY_CONTENT}</div> 
            </div> 
            </c:if> 
            <c:if test="${type == 2 }">
		        <div class="row">
					<div class="col-md-2 line">Reply Content</div>
					<div class="col-md-10">
					<textarea id="Replytext"  name="replyContent">${ContactusView.REPLY_CONTENT}</textarea>
					</div>
				</div>
			</c:if> 	
        </div>  
                    
        <div class="btns">
        	<c:if test="${type == 2 }">
        		<a href="#" class="confirm" onclick="Reply();">Reply & Send Mail</a>
        	</c:if>
        	
        	<a href="#" class="cancel">Cancel</a>
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
				var ue=UeditorFileLoad.init("Replytext");
				function Reply()
				{   
					var id="${ContactusView.CONTACT_INFO_ID}",Reply=ue.getContentTxt();
					$.post("replySave.action", "contactusid=" + id+ "&replyContent="
							+ Reply, function(data) {
						var result = eval(data);
						alert(result.msg);
						var index = parent.layer.getFrameIndex(window.name);
						parent.location.reload();
						parent.layer.close(index);
					}, "json");
				}
			</c:if>
			
    </script>
</div>
</body>
</html>
