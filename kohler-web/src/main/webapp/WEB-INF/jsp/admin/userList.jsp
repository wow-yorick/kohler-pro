<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="getCollectionName" prefix="collection"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
<jsp:include page="../common/common.jsp" />
</head>
<body>
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>
		
			<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>User</h1>
					<div class="query row">
					<form id="searchForm" action="${base}/user/userList.action" method="post">
						<div class="col-md-10">
							<div class="col-md-1"><label>Name</label></div>
							<div class="col-md-3"><input type="text" name="realName" value="${realName }"></div>
							<div class="col-md-1"><label>Login Name</label></div>
							<div class="col-md-3"><input type="text"  name="userName" value="${userName }"></div>
						</div>
					</form>
						<div class="opera_con col-md-2">
							<a class="btn" href="javascript:void(0);"  onclick="searchUser();">Search</a>
							<a class="btn new" href="#">Create</a>
							<a class="btn delete" href="javascript:void(0);" onclick="deleteUsers();">Delete</a>
						</div>
					</div>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th>Action</th>
								<th>Name</th>
                                <th>Login Name</th>
                                <th>Roles</th>
								<th>Creator</th>
								<th  class="no_rightborder">Creation Date</th>
							</tr>
						</thead>
						<tbody> 
						<c:choose>
								<c:when test="${fn:length(pager.datas) == 0 }">
									<tr>
										<td colspan ="3">Sorry,these is no data for this action!</td>
									</tr>
								</c:when>
							<c:otherwise>	
							<c:forEach items="${pager.datas}" var="user">
							<tr>
								<td>
									<div class="checkbox">
										<a href="#" class="ope_icon choose"></a>
										<input type="checkbox" name="sysUserIds" value="${user.userId}">
									</div>
									<a href="javascript:void(0);" class="ope_icon edit" onclick="editUser('${user.userId}');"></a>
									<a href="javascript:void(0);" class="ope_icon detail" onclick="viewUser('${user.userId}');"></a>
								</td>
                                <td>${user.realName }</td>
								<td>${user.loginName }</td>
								<td class="fl_left">${user.roleName }</td>
								<td>${user.createUser }</td>
								<td class="no_rightborder">${user.createTime }</td>
							</tr>
							</c:forEach>
                         </c:otherwise>
                         </c:choose>
                            
						</tbody>
					</table>
<script>

	</script>
					<div class="page">
						<span class=""></span>
						<span></span>
					</div>
				</div>
				<!-- 表格table结束-->
				<!-- 翻页部分table_con开始-->
				<c:if test="${fn:length(pager.datas) != 0 }">
				<jsp:include page="../common/page.jsp"></jsp:include>
				</c:if>
				<!-- 翻页部分table_con开始-->
			</div>
		</div>
		<!-- 内容区域content结束 -->
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">

	function searchUser(){
		//alert("success");
		$("#searchForm").submit();
	}
	
	$('.new').on('click', function(){
		
		var url = '${base}'+"/user/create.action";
		
		//console.info("获取的url="+url);
		//var name = node.name;
		alertFirstIframe("create user",url,'880px','600px');
		
	});
	function editUser(userId){
		
		var url = '${base}'+"/user/edit.action?sysUserId="+userId+"&type=2";
		alertFirstIframe("Edit user",url,'880px','600px');
	}
	
	function viewUser(userId){
		
		var url = '${base}'+"/user/edit.action?sysUserId="+userId+"&type=1";
		alertFirstIframe("View user",url,'880px','600px');
	}
	
	
	
function deleteUsers(){
		
		var checkboxs = $("input[name='sysUserIds']:checked");
		
		var len = checkboxs.length;
		if( len == 0){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		
		if(!confirm("<spring:message code="info.common.confirm.delete" arguments="" argumentSeparator=","/>")){
			return false;
		}

		var sysUserIds = new Array();
		
		$.each(checkboxs, function(i,item){      
			sysUserIds.push( $(item).val()); 
		  }); 
		//console.info(ids);
		
		
		$.post("delete.action","sysUserIds="+sysUserIds, function(data){
			var result = eval(data);
			alert(result.msg);
			location.reload();
		  },"json");	
		
}
</script>
</html>







