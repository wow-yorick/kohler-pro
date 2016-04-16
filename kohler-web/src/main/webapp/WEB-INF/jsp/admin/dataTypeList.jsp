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
<title>DataType</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="/kohler-enterprise/css/main.css"/>
<link rel="stylesheet" type="text/css" href="/kohler-enterprise/css/main.css"/>
<link rel="stylesheet" type="text/css" href="/kohler-enterprise/css/style.css"/>
</head>
<body  class="admin_roles">
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>
			<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>Datatype</h1>
					<div class="query row">
					<form id="searchForm" action="${pager.url}" method="get" >
						<div class="col-md-10">
							<div class="col-md-1"><label>Name</label></div>
							<div class="col-md-2"><input type="text" name="dataTypeName" value="${dataTypeName}"></div>
						</div>
						<div class="opera_con col-md-2">
							<a class="btn" href="javascript:void(0);" onclick="listSelectDataType()">Search</a>
							<per:user resourceId="admin.datatype.create">
							<a class="btn new" href="#">Create</a> 
							</per:user>
							<per:user resourceId="admin.datatype.delete">
							<a class="btn" href="javascript:void(0);" onclick="deleteDataTypePage()">Delete</a>
							</per:user>
						</div>
						</form>
					</div>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width:10%">Action</th>
								<th style="width:70%">Name</th>
								<th style="width:6%">Creator</th>
								<th  class="no_rightborder">Creation Date</th>
							</tr>
						</thead>
						<tbody> 
							<c:forEach items="${pager.datas}" var="dataTypeList">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="dataTypeId"  value="${dataTypeList.datatypeId}">
										</div>
										<per:user resourceId="admin.datatype.edit">
										<a href="javascript:void(0);"
										onclick="editDataTypePage(${dataTypeList.datatypeId})"
										class="ope_icon edit"></a>
										</per:user> 
										<per:user resourceId="admin.datatype.view">
										<a href="javascript:void(0);"
										onclick="viewDataType(${dataTypeList.datatypeId})"
										class="ope_icon detail"></a>
										</per:user>
										</td>
									<td class="fl_left">${dataTypeList.datatypeName}</td>
									<td>${dataTypeList.createUser}</td>
									<td class="no_rightborder"><fmt:formatDate
											value="${dataTypeList.createTime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
                   <div class="page">
						<span class=""></span> <span></span>
					</div>
				</div>
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">
	function listSelectDataType(){
		$('#searchForm').submit();
	}
	$('.new').on('click', function(){
	    $.layer({
	        type: 2,
	        title: 'DataType',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '600px'],
			shadeClose: false,
	        offset : [($(window).height() - 550)/2+'px', ''],
	        iframe: {src: 'create.action'}
	    });
	});
	function editDataTypePage(dataTypeId){
		$.layer({
	        type: 2,
	        title: 'DataType',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'edit.action?dataTypeIds='+dataTypeId+'&type=2'}
	    });
	}
	
	function viewDataType(dataTypeId){
		$.layer({
	        type: 2,
	        title: 'DataType',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'view.action?dataTypeIds='+dataTypeId+'&type=1'}
	    });
	}
	
	function deleteDataTypePage(){
		var checkboxs = $("input[name='dataTypeId']:checked"),len = checkboxs.length,datatypeId = "";
		if( len == 0){
			//alert("请至少选择一条记录！");
			if(!confirm("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>")){
				
			}
			return false;
		}
		if(!confirm("<spring:message code="info.common.confirm.delete" arguments="" argumentSeparator=","/>")){
			return false;
		}

		$.each(checkboxs, function(i,item){      
		      if(i == len-1){
		    	  datatypeId +=$(item).val() ;
		      }else{
		    	  datatypeId +=$(item).val()+ ",";
		      }
		      
		  });
		  $.post("delete.action","datatypeId="+datatypeId, function(data){
			var result = eval(data);
			alert(result.msg);
			location.reload();
		  },"json");
	};
</script>
</html>







