<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>

<div class="head">
	<!-- 公共头部head -->
	<div class="top">
		<!-- 头部head中的最上一行top -->
		<img src="../images/logo.png">
		<h1>Website Workbench</h1>
		<div class="operation">
			<a href="javascript:void(0);" id="logout" class="layout">logout</a> <a href="javascript:void(0);" id="change_pass" class="change_pass">Changes
				Password</a>
		</div>
		<p class="wel">Good afternoon, ${loginUser.userName} , welcome!</p>
	</div>
	<div class="menu">
		<!-- 头部head中的菜单menu -->
		<ul>
			<!-- 默认显示全部菜单 -->
			<c:forEach items="${topMenu}" var="tm">
				<c:if test="${per:contain(topPermission,tm.permissionCode)}">
					<li style="">
						<div class="shadow">
							<c:choose>
								<c:when test="${empty tm.actionUrl}">
									<a href="#" class="me_active">${tm.permissionName}</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value="${tm.actionUrl}"/>" class="me_active">${tm.permissionName}</a>
								</c:otherwise>
							</c:choose>
							<ul class="sub_menu">
								<c:forEach items="${subMenu}" var="sm">
									<c:if test="${tm.permissionId eq sm.parentId}">
										<c:if test="${per:contain(subPermission,sm.permissionCode)}">
											<li><a href="<c:url value="${sm.actionUrl}"/>">${sm.permissionName}</a></li>
										</c:if>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>
<script>
$("#change_pass").click(function(){
	$.layer({
        type: 2,
        title: 'Change Password',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: "<%=request.getContextPath()%>/home/unlimited/changePassword.action"}
    });
});

$("#logout").click(function(){
	 if(!confirm("Are you sure logout?")){
         return false;
	}
	$.post("<%=request.getContextPath()%>/base/logout.action", function(data) {
        //alert(data.msg);
        window.location.href="<%=request.getContextPath()%>/base/login.action";
    });
})
</script>