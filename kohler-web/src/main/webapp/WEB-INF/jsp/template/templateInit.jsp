<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>init模板</title>
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
</head>
<body>
	<form id="templateForm"> 
	<div style="border: 1px solid black;">
		<div align="left">模板名称：${template.name}</div>
		<input type="hidden" class="templateId" value="${template.id}"/>
		<input type="hidden" id="fileName" value="${fileName}"/>
		
		<div style="height: 20px"></div>
		<div>
			<c:forEach items="${template.fields}" var="field">
				<c:choose>
					<c:when test="${field.complicated == '0'}">
						<div class="field">
							<label class="fieldname">${field.name}</label>
							<input type="hidden" class="complicated" value="${field.complicated}"  />
							<c:choose>
								<c:when test="${field.type == 'select'}">
									<select class="value">
										<c:forEach items="${field.values}" var="value">
											<option value="${value}">${value}</option>
										</c:forEach>
									</select>
								</c:when>
								<c:when test="${field.type == 'checkbox'}">
									<c:forEach items="${field.values}" var="value">
										<input class="value" type="${field.type}"  value="${value}"/>${value}
									</c:forEach>
								</c:when>
								<c:when test="${field.type == 'radio'}">
									<c:forEach items="${field.values}" var="value">
										<input class="value" type="${field.type}"  value="${value}"/>${value}
									</c:forEach>
								</c:when>
								<c:otherwise>
									<input class="value" type="${field.type}" />
			    				</c:otherwise>
							</c:choose>
						</div><br>
					</c:when>
					<c:otherwise>
						<div>
							<label>${field.name}</label>
							<input type="hidden" value="${field.complicated}"  />
							<!-- <a href="getComplicatedType.action?fileName=test2Template.xml">${field.name}</a> -->
							<a  href="javascript:void(0);" onclick="getComplicatedType()">${field.name}</a>
							
						</div>
						<div class="divforload">
						
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
	<input type="button"  value="提交" onclick="genTemplate()"/>
	</form>
</body>

<script type="text/javascript">
	function genTemplate(){
		var templateId = $(".templateId").val();
		var fileName = $("#fileName").val();
		var data="";
		var len = $(".field").length;
		$(".field").each(function(index,element){
			if(index == len-1){
				data += $(this).find(".fieldname").text()+","+$(this).find(".value").val()+","+$(this).find(".complicated").val();
			}else{
				data += $(this).find(".fieldname").text()+","+$(this).find(".value").val()+","+$(this).find(".complicated").val()+";";
			}
	    });
		alert(data);
	    $.ajax({
	        type: 'POST',
	        url: 'choseElement.action',
	        data: "templateId="+templateId+"&data="+data+"&fileName="+fileName,
	        success: function(data){
	        	var result = eval(data);
	        	if(result.msg != null){
	        		alert(result.msg);
	        	}
	        }
	    });
	}
	function getComplicatedType(){
		$.ajax({
	        type: 'POST',
	        url: 'getComplicatedType.action',
	        data: "fileName=test2Template.xml",
	        success: function(data){
	        	var result = eval(data);
	        	if(result.template.fields != null){
	        		var fieldsHtml = "";
	        		$(result.template.fields).each(function(i,field){
	        			fieldsHtml = '<div class="field">';
		        		fieldsHtml += '<label class="fieldname">'+field.name+'</label>';
		        		fieldsHtml += '<input type="hidden" class="complicated" value="'+field.complicated+'"/>';
						if(field.type == 'select'){
		        			
		        		}else if(field.type == 'checkbox'){
		        			$(field.values).each(function(i,value){
		        				fieldsHtml += '<input class="value" type="checkbox"  value="'+value+'"/>' +value;
		        			});
		        		}else{
		        			
		        		}
		        		fieldsHtml +='</div>';
		    	    });
		        	$(".divforload").html(fieldsHtml);
	        	}
	        }
	    });
		
	}
</script>
</html>
