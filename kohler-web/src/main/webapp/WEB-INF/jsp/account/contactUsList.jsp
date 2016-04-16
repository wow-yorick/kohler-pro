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
<div class="wrap">
		<!-- 公共头部head开始 -->
		<c:import url="/common/header.action"></c:import>
		<!-- 公共头部head结束 -->

		<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>Contact Us</h1>
					<div class="query row">
					   <form id="contactusFrom" action="contactUsList.action" method="post">
							<div class="col-md-8">
								<div class="col-md-1"><label>Status</label></div>
								<div class="col-md-2">
	                            	<select name="Status">
	                                	<option value="">--请选择--</option>
				                            <c:forEach items="${listdata}" var="listdata">
												<option value="${listdata.masterdataId}" <c:if test="${listdata.masterdataId == Status}">selected</c:if> >${listdata.masterdataName}</option>
											</c:forEach>
	                                </select>
	                            </div>
							</div>
					    </form>
						<div class="opera_con col-md-4">
							<a class="btn" id="searchnew" href="#">Search</a>
							<a class="btn reply" id="myreply"href="#">Reply</a>
							<a class="btn delete" id="mycancel" href="#">Cancel</a>
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
								<th>Status</th>
								<th>Name</th>
                                <th>Email</th>
                                <th>Contact Time</th>
                                <th>Message</th>
								<th  class="no_rightborder">Handle User</th>
							</tr>
						</thead>
						<tbody>
						  <c:forEach items="${pager.datas}" var="contactuslist">
							<tr>
								<td>
									<div class="checkbox">
										<a href="#" class="ope_icon choose"></a>
										<input type="checkbox"  name="contactus" onclick="check('${contactuslist.CONTACT_STATUS}',${contactuslist.CONTACT_INFO_ID})"  value="${contactuslist.CONTACT_INFO_ID}">
									</div>
									<a href="#" onclick="detail(${contactuslist.CONTACT_INFO_ID})" class="ope_icon detail"></a>
								</td>
								<td class="fl_left">${contactuslist.CONTACT_STATUS}</td>
                                <td class="fl_left">${contactuslist.REAL_NAME}</td>
								<td class="fl_left">${contactuslist.EMAIL}</td>
                                <td class="fl_left">${contactuslist.CONTACT_DATE}</td>
                                <td class="fl_left">${contactuslist.CONTACT_MESSAGE}</td>
								<td class="no_rightborder">${contactuslist.HANDLE_USER}</td>
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


function detail(contactusid)
{
	$.layer({
        type: 2,
        title: 'View Account',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '600px'],
		shadeClose: false,
        offset : [($(window).height() - 600)/2+'px', ''],
        iframe: {src: 'reply.action?contactusid='+contactusid+'&type=1'}
    });
}

function check(obj1,id){
	$("input[name='contactus']").each(function(i){
		 if(this.checked == 'checked'){
			 $(this).parent().children().attr('class',"ope_icon choose active");
		 }else{
			 $(this).parent().children().attr('class',"ope_icon choose");
			 this.checked=false;
		 }
	});
	if(obj1 == '未处理' || obj1 == '处理中'){
		$("#myreply").attr({
			"href":"#",
			"onclick":"reply("+id+");return false;"
		});

	}else{
		$("#myreply").removeAttr("href onclick");
	}
	$("#mycancel").attr({
		"href":"#",
		"onclick":"cancel("+id+");return false;"
	});
	
}
function cancel(contactusid){
	$.layer({
	    shade: [0],
		title: 'Confirm',
	    area: ['310px', '130px'],
	    dialog: {
	        msg: 'Cancel this message??',
	        btns: 2,                    
	        type: -1,
	        btn: ['OK','Cancel'],
	        yes: function(){
	        	$.post("cancelSave.action","contactusid="+contactusid, function(data){
	    			var result = eval(data);
	    			alert(result.msg);
	    			location.reload();
	    		  },"json");		
	        }
	    }
	});
}

function reply(contactusid){ 
		$.layer({
	        type: 2,
	        title: 'reply',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'reply.action?contactusid='+contactusid+'&type=2'}
	    });
}
	



$('#searchnew').on("click",function(){
	$("#contactusFrom").submit();
})
</script>
</body>
</html>