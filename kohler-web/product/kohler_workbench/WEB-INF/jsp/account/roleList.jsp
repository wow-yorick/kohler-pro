<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="getCollectionName" prefix="collection"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系列</title>
<jsp:include page="../common/common.jsp" />
</head>
<body>
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>
		<div class="content">
			<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>Template</h1>
					<div class="query row">
						<div class="col-md-10">
							<div class="col-md-1"><label>Name</label></div>
							<div class="col-md-2"><input type="text" name='rolename' value='${rolename}'></div>
						</div>
						<div class="opera_con col-md-2">
							<per:user resourceId="PC0011">
								<a class="btn" href="javascript:void(0);" onclick="searchCollection()">Search</a>
							</per:user>
							<per:user resourceId="PC0008">
							 	<a class="btn new" href="#">New</a> 
							 </per:user>
							 <per:user resourceId="PC0010">
							 	<a class="btn" href="javascript:void(0);" onclick="deleteRole()">Delete</a>
							 </per:user>
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
								<th>Creator</th>
								<th  class="no_rightborder">Creation Date</th>
							</tr>
						</thead>
						<tbody> 
							<c:forEach items="${pager.datas}" var="rolelist">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="roleid"  value="${rolelist.roleId}">
										</div>
										<a href="javascript:void(0);"
										onclick="editrole(${rolelist.roleId})"
										class="ope_icon edit"></a> <a href="javascript:void(0);"
										onclick="viewrole(${rolelist.roleId})"
										class="ope_icon detail"></a></td>
									<td>${rolelist.roleName}</td>
									<td class="fl_left">${rolelist.createUser}</td>
									<td class="no_rightborder"><fmt:formatDate
											value="${rolelist.createTime}"
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
		</div>
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">
	function searchCollection(){
		$("#searchForm").submit();
	}
	$('.new').on('click', function(){
	    $.layer({
	        type: 2,
	        title: 'adminRoles',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '600px'],
			shadeClose: false,
	        offset : [($(window).height() - 550)/2+'px', ''],
	        iframe: {src: 'newrolepage.action'}
	    });
	});
	function editrole(roleid){
		$.layer({
	        type: 2,
	        title: 'adminRoles',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'editrolepage.action?roleids='+roleid+'&type=2'}
	    });
	}
	
	function viewrole(roleid){
		$.layer({
	        type: 2,
	        title: 'adminRoles',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'editrolepage.action?roleids='+roleid+'&type=1'}
	    });
	}
	
	function deleteRole(){
		var checkboxs = $("input[name='roleid']:checked"),len = checkboxs.length,roleid = "";
		if( len == 0){
			alert("请至少选择一条记录！");
			return false;
		}
		$.each(checkboxs, function(i,item){      
		      if(i == len-1){
		    	  roleid +=$(item).val() ;
		      }else{
		    	  roleid +=$(item).val()+ ",";
		      }
		  }); 
		  $.post("deleterole.action","rolesid="+roleid, function(data){
			var result = eval(data);
			alert(result.msg);
			location.reload();
		  },"json");
	}
</script>
</html>







