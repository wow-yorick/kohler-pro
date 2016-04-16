<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="getCollectionName" prefix="collection"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DataType</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="/kohler-enterprise/css/main.css"/>
<script type="text/javascript" src="../js/jquery.ztree.all-3.5.min.js"></script>

</head>

<body>
<div class="container queryConditions">
	<!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    <form id="addDatatypeForm">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4"><a id="datatypeId">${dataType.datatypeId}</a></div>
            	<div class="col-md-2 tn_c required">Type</div>
  				<div class="col-md-4 ">
                	<select id="datatypeType" value="${dataType.datatypeType}"  required="required">
                		<c:forEach items="${masterdataEntity.datas}" var="publishFolder">
                			<c:choose>
								<c:when test="${publishFolder.masterdataMetadataId eq dataType.datatypeType}">
									<option value="${publishFolder.masterdataMetadataId}" selected="selected">${publishFolder.masterdataName}</option>
								</c:when>
								<c:otherwise>
									<option value="${publishFolder.masterdataMetadataId}">${publishFolder.masterdataName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
                	</select>
                </div>
            </div>
        	<div class="row">
            	<div class="col-md-2 required">Name</div>
            	<div class="col-md-4 input_small"><input type="text" id="dataTypeName" name="dataTypeName" isDatatypeNameUnique="true" required="required" maxlength="25" value="${dataType.datatypeName}" ></div>  
                <div class="col-md-2 required">Menu Name</div>            	
            	<div class="col-md-4 "><input type="text" id="datatypeMenuName" name="datatypeMenuName" required="required" maxlength="25" value="${dataType.menuName}"></div>  
            </div>
            <div class="row larger">
            	<div class="col-md-2 required">List UI Schema XML</div>
                <div class="col-md-10">
                	<textarea rows="4" id="datatypeListUISchemaXML" name="datatypeListUISchemaXML" required="required" maxlength="1000">${dataType.listDefinationXML}</textarea>
               </div>  
            </div>
            <div class="row larger">
            	<div class="col-md-2 required">Edit UI Schema XML</div>
                <div class="col-md-10">
                	<textarea rows="4" id="datatypeEditUISchemaXML" name="datatypeEditUISchemaXML" required="required" maxlength="1000">${dataType.editDefinationXML}</textarea>
               </div>  
            </div>               	
        </div>              
		<div class="clear"></div>  
        <div class="text" style="float:left;margin-left:110px;margin-top:20px;width:450px;margin-bottom:20px;">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-4">
                        		${dataType.createUser}
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:<fmt:formatDate value="${dataType.createTime}"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    	<div class="row" style="margin-top:20px;">
                        	 <div class="col-md-2">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-4">
                        		${dataType.modifyUser}
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:<fmt:formatDate value="${dataType.modifyTime}"
									pattern="yyyy-MM-dd HH:mm" />
                        		
                        	 </div>
                        </div>
                    </div>
        <div class="btns">
        	<a href="javascript:void(0);" id="edit" class="confirm"
						onclick="editDataType(${dataType.datatypeId})">Save</a>
        	<a href="#" id="cancel" class="cancel">Cancel</a>
        	<a href="#" id="back" class="cancel" style="display:none">Cancel</a>
        </div>
        <div id="errorMessage" style="display: none"></div>
        </form>
    <!--main结束-->
   <!-- 关闭frame-->
       <script  type="text/javascript">
    		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function(){
    		parent.layer.close(index); //执行关闭
		});
    		
    </script>

</div>
<input type="hidden" id="dataTypesid"  value="">
<script>
$(document).ready(function(){
	var type1 =  ${type};
	if(type1 == 1){
		$("#dataTypeName").attr("disabled","disabled");
		$("#datatypeMenuName").attr("disabled","disabled");
		$("#datatypeListUISchemaXML").attr("disabled","disabled");
		$("#datatypeEditUISchemaXML").attr("disabled","disabled");
		$("#datatypeType").attr("disabled","disabled");
		setGray4DisabledSelect($("#datatypeType"));
		document.getElementById("edit").style.display = "none"; 
		document.getElementById("cancel").style.display = "none";
		document.getElementById("back").style.display = "";
		$(".required").removeClass("required");
	}
	});
	
//判断sectionName是否唯一
jQuery.validator.addMethod("isDatatypeNameUnique", function(value, element) { 
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	var datatypeId = $("#datatypeId").text();
    var result = false;
    // 设置同步
    $.ajaxSetup({
        async: false
    })
    $.post("unlimited/uniquenessEditVerification.action", "datatypeName=" + value + "&datatypeId=" + datatypeId, function(data){
    	result = data;
        // 恢复异步
    	$.ajaxSetup({
	        async: true
	    });
    },"json");
    
    return result;
}, "<spring:message code="info.admin.datatype.001" arguments="" argumentSeparator=","/>"); 
	//关闭frame
	
	function editDataType(id)
	{
		$("#errorMessage").html("");
		if ($("#addDatatypeForm").valid()) {
			var dataTypeName=$("#dataTypeName").val();	
			var    datatypeMenuName=$("#datatypeMenuName").val();
			var    datatypeType=$("#datatypeType").val();
			var    datatypeListUISchemaXML=$("#datatypeListUISchemaXML").val();
			var    datatypeEditUISchemaXML=$("#datatypeEditUISchemaXML").val();
		
			$.post("unlimited/changeDatatype.action", "datatypeListUISchemaXML="+datatypeListUISchemaXML 
				+ "&datatypeEditUISchemaXML=" + datatypeEditUISchemaXML, function(data) {
			var result = eval(data);
			if(result.msg != "")
				alert(result.msg);
			if(result.msg == "")
			$.post("editSave.action", "dataTypeName=" + dataTypeName + "&dataTypeId=" + id + "&datatypeMenuName=" 
					+ datatypeMenuName + "&datatypeType="+datatypeType+"&datatypeListUISchemaXML="+datatypeListUISchemaXML 
					+ "&datatypeEditUISchemaXML=" + datatypeEditUISchemaXML, function(data) {
				var result = eval(data);
				alert(result.msg);
				var index = parent.layer.getFrameIndex(window.name);
				parent.location.reload();
				parent.layer.close(index);
			}, "json");
		}, "json");
		
		//$.post("editSave.action", "dataTypeName=" + dataTypeName + "&dataTypeId=" + id + "&datatypeMenuName=" 
		//		+ datatypeMenuName + "&datatypeType="+datatypeType+"&datatypeListUISchemaXML="+datatypeListUISchemaXML 
		//		+ "&datatypeEditUISchemaXML=" + datatypeEditUISchemaXML, function(data) {
		//	var result = eval(data);
		//	alert(result.msg);
		//	var index = parent.layer.getFrameIndex(window.name);
		//	parent.location.reload();
		//	parent.layer.close(index);
		//	}, "json");
		
		}
		else{
			alert($("#errorMessage").html());
				
		}
		
	}
	
	
</script>
</body>
</html>
