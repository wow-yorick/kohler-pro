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
<title>Account</title>
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
						 <form id="accountFrom" action="accountList.action" method="post">
							<input type="hidden" name="search" value="search">
							<div class="col-md-8 accountrow">
								<div class="col-md-1"><label>Name</label></div>
								<div class="col-md-2 laydate"><input type="text"name="Name" id="Name" value="${Name}" /></div>
								<div class="col-md-1"><label>Mobile</label></div>
								<div class="col-md-2 laydate" ><input type="text"name="Mobile" id="Mobile" value="${Mobile}" /></div>
								<div class="col-md-1"><label>Type</label></div>
								<div class="col-md-2">
									<select id="Type" name="Type" >
					                           <option value="">--请选择--</option>
					                            <c:forEach items="${listdata}" var="listdata">
													<option value="${listdata.masterdataId}" <c:if test="${listdata.masterdataId == Type}">selected</c:if> >${listdata.masterdataName}</option>
												</c:forEach>
		                   			</select>
	                            </div>
							</div>
						 </form>
						 <div class="opera_con col-md-4 accountbtn">
								<a class="btn" id="searchnew" href="#">Search</a>
								<a class="btn freeze" id="freeze"  onclick="FreezeEdit();return false;">Freeze</a>
								<a class="btn unfreeze" id="unfreeze" href="#" onclick="unFreezeEdit();return false;">Unfreeze</a>
	                            <a class="btn reset" id="rest" onclick="reset();return false;"  href="#">Reset Password</a>
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
								<th>Type</th>
								<th>Mobile</th>
								<th>Name</th>
								<th  class="no_rightborder">邮箱</th>
								<th  class="no_rightborder">Status</th>
							</tr>
						</thead>
						<tbody>
						  <c:forEach items="${pager.datas}" var="accountlist">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" id="ac" class="ope_icon choose"></a>
											<input type="checkbox" name="account" onclick="check(this,'${accountlist.accountstatus}',${accountlist.ACCOUNT_ID})" value="${accountlist.ACCOUNT_ID}">
										</div>
										<a href="javascript:void(0);"
										onclick="viewaccount(${accountlist.ACCOUNT_ID})"
										class="ope_icon detail"></a></td>
									<td class="fl_left">${accountlist.AccountType}</td>
									<td class="fl_left">${accountlist.ACCOUNT_NAME}</td>
									<td class="fl_left">${accountlist.REAL_NAME}</td>
									<td class="fl_left">${accountlist.EMAIL}</td>
									<td class="fl_left">${accountlist.accountstatus}</td>
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
function viewaccount(accountId){
	$.layer({
        type: 2,
        title: 'Account',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'view.action?accountId='+accountId}
    });
}

function unFreezeEdit(accountId)
{
	$.layer({
        type: 2,
        title: 'Account',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'unfreeze.action?accountId='+accountId}
    });
}
function FreezeEdit(accountId){
	 //var checkboxs = $("input[name='account']:checked"),accountId = checkboxs.val();
     $.layer({
         type: 2,
         title: 'Account',
         maxmin: false,
         shadeClose: true, //开启点击遮罩关闭层
         area : ['880px' , '650px'],
 		shadeClose: false,
         offset : [($(window).height() - 660)/2+'px', ''],
         iframe: {src: 'freeze.action?accountId='+accountId}
     });
}

function reset(accountId){
	$.layer({
        type: 2,
        title: 'Account',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'view.action?accountId='+accountId+'&type=1'}
    });
}
var i=0;
function check(obj,obj1,id){
	/**
	$("input[name='account']").each(function(i){
		if(this.checked == 'checked'){
				 alert(1);
				 $(this).parent().children().attr('class',"ope_icon choose active");
		}else{
				 alert(2);
				 $(this).parent().children().attr('class',"ope_icon choose");
				 this.checked=false;
		}
		
		i=id;
	});
	**/
	var checkboxs = $("input[name='account']:checked"),len = checkboxs.length;
	if( len != 1){
		$("#unfreeze,#freeze,#rest").removeAttr("href onclick");
		return false;
	}
	
	//if(!confirm("<spring:message code="info.product.category.001" arguments="" argumentSeparator=","/>")){
		//return false;
	//}
	//$(obj).parent().children().attr('class',"ope_icon choose active").siblings().attr('class',"ope_icon choose ");
	if(obj1 == '已冻结'){
		$("#freeze").removeAttr("href onclick");
		$("#unfreeze").attr({
			"href":"#",
			"onclick":"unFreezeEdit("+id+");return false;"
		});
	}
	if(obj1 == '正常'){
		$("#unfreeze").removeAttr("href onclick");
		$("#freeze").attr("href","#");
		$("#freeze").attr({
			"href":"#",
			"onclick":"FreezeEdit("+id+");return false;"
		});
	}
	$("#rest").attr("onclick","reset("+id+");return false;");
}
$(document).ready(function(){
	
	$('.long').on('click', function(){
	    $.layer({
	        type: 2,
	        title: 'New Product',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['980px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'create.action'}
	    });
	});
	$('#searchnew').on("click",function(){
			$("#accountFrom").submit();
	})
});

	</script>

</body>
</html>