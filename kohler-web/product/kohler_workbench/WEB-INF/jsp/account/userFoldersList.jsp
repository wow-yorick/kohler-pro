<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserFolders</title>
<jsp:include page="../common/common.jsp" />
</head>
<body class="admin_roles">
	<div  class="wrap" style="height: 750px;">
		<c:import url="/common/header.action"></c:import>
	<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->
				<div class="condition">
					<h1>Account</h1>
					<div class="query row">
					 <form id="userFoldersFrom" action="userFolderList.action" method="post">
							 <div class="col-md-8">
								<div class="col-md-1"><label>User Type</label></div>
								<div class="col-md-2">
	                            		<select id="UserType" name="UserType" >
				                           <option value="">--请选择--</option>
				                            <c:forEach items="${listdata}" var="listdata">
												<option value="${listdata.masterdataId}" <c:if test="${listdata.masterdataId == Type}">selected</c:if> >${listdata.masterdataName}</option>
											</c:forEach>
	                   					</select>
	                            </div>
								<div class="col-md-1"><label>Type</label></div>
								<div class="col-md-2">
	                            	<select id="type" name="type" >
				                           <option value="">--请选择--</option>
				                            <c:forEach items="${listdata1}" var="listdata1">
												<option value="${listdata1.masterdataId}" <c:if test="${listdata1.masterdataId == UserType}">selected</c:if> >${listdata1.masterdataName}</option>
											</c:forEach>
	                   					</select>
	                            </div>
							</div>
					 </form>
					 <div class="opera_con col-md-4 accountbtn">
							<a class="btn" id="searchnew" href="#">Search</a>
							<a class="btn delete" href="#">Delete</a>
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
								<th>User Type</th>
								<th>User's Mobile</th>
                                <th>User's Name</th>
                                <th>Type</th>
								<th  class="no_rightborder">Title</th>
							</tr>
						</thead>
						<tbody>
						  <c:forEach items="${pager.datas}" var="UserFolders">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="UserFolders"  value="${UserFolders.ACCOUNT_FOLDER_DETAIL_ID}">
										</div>
									</td>
									<td class="fl_left">${UserFolders.MASTERDATA_NAME}</td>
									<td class="fl_left">${UserFolders.ACCOUNT_NAME}</td>
									<td class="fl_left">${UserFolders.REAL_NAME}</td>
									<td class="fl_left">${UserFolders.titleid}</td>
									<td class="fl_left">${UserFolders.titlename}</td>
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
    <script>
//弹出一个iframe层

function alertInfo() {
	if(!confirm("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>")){
		
	}
}
//删除按钮
$('.delete').on('click', function(){
	$.layer({
    shade: [0],
	title: 'Confirm',
    area: ['310px', '130px'],
    dialog: {
        msg: 'Delete this data?',
        btns: 2,                    
        type: -1,
        btn: ['OK','Cancel'],
        yes: function(){
        	var checkboxs = $("input[name='UserFolders']:checked"),len = checkboxs.length,ACCOUNT_FOLDER_DETAIL_ID = "";
        	if( len == 0){
				return false;
			}
        	$.each(checkboxs, function(i,item){      
			      if(i == len-1){
			    	  ACCOUNT_FOLDER_DETAIL_ID +=$(item).val() ;
			      }else{
			    	  ACCOUNT_FOLDER_DETAIL_ID +=$(item).val()+ ",";
			      }
			  });
        	
	        $.post("delete.action",{'ACCOUNT_FOLDER_DETAIL_ID':ACCOUNT_FOLDER_DETAIL_ID},function(data) {
					alert(data.msg);
					location.reload();
			}, "json");
        }
    }
});});

$(document).ready(function(){
	$('#searchnew').on("click",function(){
			$("#userFoldersFrom").submit();
	})
});

	</script>

</body>
</html>