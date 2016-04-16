<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>adminTemplateslayer弹出层</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
$(function(){
	var $tab_li = $('#tab ul li');
	var index = parent.layer.getFrameIndex(window.name);
	$tab_li.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index = $tab_li.index(this);
		$('div.tab_box > .box').eq(index).show().siblings().hide();
	});	
	$("#txt span").click(function(){
		var boxClass=$(this).attr("class");
		if(boxClass=="check"){
			$(this).attr("class","checked");
			}else{
			$(this).attr("class","check");}
		});	
	$(".cancel").click(function(){
		parent.layer.close(index); //执行关闭
	});
	//创建方法 
	$(".confirm").click(function(){
		$.post("editSave.action", $("#templateFrom").submit(),function(data) {
			var result = eval(data);
			alert(result.msg);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");
	});

});
</script>
</head>

<body>
<div class="container admin_condition">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <form id="templateFrom"  action="editSave.action" enctype="multipart/form-data" method="post">
    <div class="main">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4">${tem[0].templateId}</div>
            	<input type="hidden" name="templateId" value="${tem[0].templateId}"/>
            	<div class="col-md-2 tn_c">Type</div>
            	<div class="col-md-4">
                		<select name="templateType">
                			<c:if test="${isEdit != 1 }">
                			   <option value="${tem[0].typeId}">${tem[0].typeName}</option>
	                           <c:forEach  items="${typeList}" var="li">
	                           		<c:if test="${tem[0].typeName != li.masterdataName }">
	       								<option value="${li.masterdataId}">${li.masterdataName}</option>
	       							</c:if>
	                           </c:forEach> 
	                        </c:if>
	                        <c:if test="${isEdit eq 1 }">
	                        	<option>${tem[0].typeName}</option>
	                        </c:if>
                   		</select>                  
                </div>    
            </div>
        	<div class="row">
            	<div class="col-md-2">Name</div>
            	<div class="col-md-4"><input type="text" name="templateName" value="${tem[0].tname}"/></div>
            	<div class="col-md-2 tn_c">Folder</div>
            	<div class="col-md-4">
                		<select name="publishFolderId">
                			<c:if test="${isEdit != 1 }">
                			   <option value="${tem[0].folderId}">${tem[0].folderName}</option>
	                           <c:forEach  items="${folderList}" var="li">
	                           		<c:if test="${tem[0].folderName != li.folderName }">
	       								<option value="${li.folderId}">${li.folderName}</option>
	       							</c:if>	
	                           </c:forEach> 
                           </c:if>
                           <c:if test="${isEdit eq 1 }">
	                        	<option>${tem[0].folderName}</option>
	                        </c:if>
                   		</select>                  
                </div>    
            </div>  
            <div class="row">
            	<div class="col-md-2">Description</div>
                <div class="col-md-10"><input type="text" name="remark" value="${tem[0].remark}"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Physical Name Rule</div>
                <div class="col-md-10"><input type="text" name="physicalName" value="${tem[0].lname}"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Template Image</div>
                <div class="col-md-10"><input type="file" name="templateImagev"/></div> 
            </div>
            <div class="row">
            	<div class="col-md-2 line">Generate Url</div>
                <div class="col-md-10"><input type="text" value="${tem[0].generateUrl}" name="generateUrl"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Template File Content</div>
                <div class="col-md-10"> <textarea name="templateContent">${tem[0].filees}</textarea ></div> 
            </div>  
        </div>  
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-1">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-5">
                        		${tem[0].cuser }
                        	 </div>
                        	 <div class="col-md-6">
                        		<fmt:formatDate value="${tem[0].ctime }"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-1">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-5">
                        		${tem[0].muser }
                        	 </div>
                        	 <div class="col-md-6">
                        		<fmt:formatDate value="${tem[0].mtime }"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    </div>            
        <div class="btns">
	        <c:if test="${isEdit != 1 }">
	        	<a href="#" class="confirm" onclick="createTemplate();">确定</a>
        	</c:if>
        	<a href="#" class="cancel">取消</a>
        </div>
    </div>
    </form>
    <!--main结束-->
       <!-- 关闭frame-->
       <script  type="text/javascript">
    		 //获取当前窗体索引
			function createTemplate(){
				$.post("editSave.action", $("#templateFrom").submit(),function(data) {
					var result = eval(data);
					alert(result.msg);
					parent.location.reload();
					parent.layer.close(index);
				}, "json");
    		}
    		$(function() {
				var isEdit = ${isEdit};
				if(isEdit == 1){
					$("select").attr("disabled","disabled");
					$("input").attr("disabled","disabled");
					$("textarea").attr("disabled","disabled");
				}
			});
    </script>
</div>
</body>
</html>
