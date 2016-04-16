<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log</title>
<jsp:include page="../common/common.jsp" />

</head>
<body class="admin_user">
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>

		<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>Syslog</h1>
					<div class="query row">
					<form id="syslogFrom" action="loglist.action" method="post">
					<input type="hidden" name="search" value="search">
						<div class="col-md-10">
							<div class="col-md-1"><label>Start Date</label></div>
							<div class="col-md-2 laydate"><input type="text" class="laydate-icon"  onclick="laydate()" name="sdate" id="sdate" value="${sdate}" /></div>
							<div class="col-md-1"><label>End Date</label></div>
							<div class="col-md-2 laydate" ><input type="text" class="laydate-icon"  onclick="laydate()" name="edate" id="edate" value="${edate}" /></div>
							<div class="col-md-1"><label>Operation</label></div>
							<div class="col-md-2">
                            	<select id="operation" name="operation">
                            		<option></option>
                                	<option value="INSERT">INSERT</option>
                                    <option value="UPDATE">UPDATE</option>
                                    <option value="DELETE">DELETE</option>
                                </select>
                            </div>
						</div>
					 </form>	
						<div class="opera_con col-md-2">
							<a class="btn search" href="#">Search</a>
							<a class="btn delete" href="#">Delete</a>
							<a class="btn clea" href="#">Clear</a>
						</div>
					</div>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width:5%">Action</th>
								<th style="width:15%">User</th>
								<th style="width:15%">Time</th>
                                <th>Object</th>
                                <th style="width:40%">Operation</th>
								<th  class="no_rightborder" style="width:10%">Id</th>
							</tr>
						</thead>
						<tbody> 
							<c:forEach items="${pager.datas}" var="sysloglist">
							<tr>
								<td>
									<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="syslog"  value="${sysloglist.SYS_LOG_ID}">
										</div>
								</td>
								 <td>${sysloglist.USER_NAME}</td>
								<td>${sysloglist.OPERATION_TIME}</td>
                                <td>${sysloglist.OPERATION_OBJECT}</td>
								<td>${sysloglist.OPERATION}</td>
								<td>${sysloglist.OBJECT_ID}</td>
							</tr>
                            </c:forEach>
                        </tbody>
					</table>
					<style>
					.xubox_botton .xubox_botton2 {
    background-position: -5px -114px;
    height: 29px;
    line-height: 29px;
    margin-left: -76px;
    width: 71px;
}
.xubox_botton a {
    background: url("../default/xubox_ico0.png") re	peat scroll 0 0 rgba(0, 0, 0, 0);
    bottom: 10px;
    color: #fff;
    font-size: 14px;
    font-weight: bold;
    left: 50%;
    overflow: hidden;
    position: absolute;
    text-align: center;
    text-decoration: none;
}
					
					</style>
                   	<script>
                   var str="${operation}";
                   if(str){
                  	 $("#operation").val(str);
                   }
                   	$('.search').on("click",function(){
                   			$("#syslogFrom").submit();
                   	})
//清除按钮
$('.clea').on('click', function(){
	$(".xubox_main").addClass("a")
	$.layer({
    shade: [0],
	title: 'Confirm',
    area: ['310px', '130px'],
    dialog: {
        msg: 'Clear all logs ?',
        btns: 2,                    
        type: -1,
        btn: ['OK','Cancel'],
        yes: function(){
        	var sdate=$("#sdate").val(),edate=$("#edate").val(),operation=$("#operation").val();
        	$.post("clear.action","sdate="+sdate+"&edate="+edate+"&operation="+operation, function(data){
        		var result = eval(data);
        		alert(result.msg);
        		location.reload();
        	  },"json");
        	}
	 }
});
})
$('.delete').on('click', function(){
	$(".xubox_main").addClass("a");
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
            	//layer.msg('Delete the success', 1, 1);//按钮一的回调函数
            var checkboxs = $("input[name='syslog']:checked"),len = checkboxs.length,syslogid = "";
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
			    	  syslogid +=$(item).val() ;
			      }else{
			    	  syslogid +=$(item).val()+ ",";
			      }
			  });
				$.post("delete.action","syslogid="+syslogid, function(data){
				var result = eval(data);
				alert(result.msg);
				location.reload();
			  },"json");
		  }	
            
        }
    });
});
	//删除按钮
	
	
	
	
	</script>
					<div class="page">
						<span class=""></span>
						<span></span>
					</div>
				</div>
				<!-- 表格table结束-->
				<!-- 翻页部分table_con开始-->
				<jsp:include page="../common/page.jsp"></jsp:include>
				<!-- 翻页部分table_con开始-->
			</div>
		</div>
		<!-- 内容区域content结束 -->
	</div>
</body>
</html>