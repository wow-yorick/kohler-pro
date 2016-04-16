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
    <div class="main">
    <form id="templateFrom" action="createSave.action" enctype="multipart/form-data" method="post">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4">${template.templateId }</div>
            	<input type="hidden" name="templateId" value="${template.templateId }"/>
            	<div class="col-md-2 tn_c">Type</div>
            	<div class="col-md-4">
                		<select name="templateType">
                           <c:forEach  items="${typeList}" var="li">
       						<option value="${li.masterdataId}">${li.masterdataName}</option>
                           </c:forEach> 
                   		</select>                  
                </div>    
            </div>
        	<div class="row">
            	<div class="col-md-2">Name</div>
            	<div class="col-md-4"><input type="text" id="templateName" name="templateName" value=""/></div>
            	<div class="col-md-2 tn_c">Folder</div>
            	<div class="col-md-4">
                		<select name="publishFolderId">
                           <c:forEach  items="${folderList}" var="li">
       						<option value="${li.folderId}">${li.folderName}</option>
                           </c:forEach> 
                   		</select>                  
                </div>    
            </div>  
            <div class="row">
            	<div class="col-md-2">Description</div>
                <div class="col-md-10"><input type="text" name="remark"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Physical Name Rule</div>
                <div class="col-md-10"><input type="text" value="" id="physicalName" name="physicalName"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Generate Url</div>
                <div class="col-md-10"><input type="text" value="" name="generateUrl"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Template File Content</div>
                <div class="col-md-10"> <textarea name="templateContent" id="templateContent"></textarea ></div> 
            </div>     	
            <div class="row">
            	<div class="col-md-2 line">Template Image</div>
                <div class="col-md-10"><input type="file" name="templateImagev"/></div> 
            </div>
        </div>  
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-1">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-5">
                        		${template.createUser }
                        	 </div>
                        	 <div class="col-md-6">
                        		<fmt:formatDate value="${template.createTime }"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-1">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-5">
                        		${template.modifyUser }
                        	 </div>
                        	 <div class="col-md-6">
                        		<fmt:formatDate value="${template.modifyTime }"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    </div>            
        <div class="btns">
        	<a href="#" class="confirm" onclick="createTemplate();">确定</a>
        	<a href="#" class="cancel">取消</a>
        </div>
    </div>
    </form>
    <!--main结束-->
       <!-- 关闭frame-->
       <script  type="text/javascript">
    		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function(){
    		parent.layer.close(index); //执行关闭
		});
    		//创建方法 
    		function createTemplate(){
    			var templateName = $("#templateName").val();
    			var physicalName = $("#physicalName").val();
    			var templateContent = $("#templateContent").val();
    			if(templateName== '' || templateName == null){
    				alert("name为必填项！");
    				return;
    			}
    			if(physicalName== '' || physicalName == null){
    				alert("Physical Name Rule为必填项！");
    				return;
    			}
    			if(templateContent== '' || templateContent == null){
    				alert("Template File Content为必填项！");
    				return;
    			}
    			$.post("createSave.action", $("#templateFrom").submit(),function(data) {
					var result = eval(data);
					alert(result.msg);
					parent.location.reload();
					parent.layer.close(index);
				}, "json");
    		}
    </script>
</div>
</body>
</html>
