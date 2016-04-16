<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询条件-编辑</title>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
$(function(){
    $(".selectPageType .row").click(function(){
        if($(this).find(".checkbox a").hasClass("active")){
            $(this).find(".checkbox a").removeClass("active");
            $(this).attr("checked",false);
            if($(this).hasClass("this_dis"))
            $(".new_togg").css("display","none");
        }
        else{
            $(this).find(".checkbox a").addClass("active");
            $(this).attr("checked",true);
            if($(this).hasClass("this_dis"))
            $(".new_togg").css("display","block");
        }
    });   
});
</script>
</head>

<body>
<div class="container selectPageType">
	<c:if test="${dataTypeList== null || fn:length(dataTypeList) == 0}">
		<div class="row">
	        <div class="col-md-9"> 
				<h3>no page type exist</h3> 
		 	</div>
		 </div>
	</c:if>
	<c:if test="${dataTypeList!= null && fn:length(dataTypeList) > 0}">
		<c:forEach items="${dataTypeList}" var="dataType">
	    <div class="row layer" id="homepage">
	         <div class="col-md-5">
	             <div class="checkbox">
	                 <a href="<%=request.getContextPath() %>/page/newPage?id=${dataType.datatypeId}" class="ope_icon choo"></a>
	                 <input type="checkbox" class="this_dis" value="${dataType.datatypeId}">
	             </div>
        	 </div>
	         <div class="col-md-7">${dataType.datatypeName}</div>
	     </div>
	     </c:forEach>
	</c:if>
	
    <script type="text/javascript">
        //关闭frame
        $(function(){	
            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
            $('.cancel').on('click', function(){
                parent.layer.close(index); //执行关闭
            });
        });
    </script>
<script>
//获取当前窗口索引
var index = parent.layer.getFrameIndex(window.name);

$('.layer').on('click', function(){
	
	var checkValue = $(this).find(".col-md-5 .checkbox .this_dis").attr("value");
    parent.$.layer({
        type: 2,
        title: 'Page',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '620px'],
        shadeClose: false,
        offset : [($(window).height() - 200)/2+'px', ''],
        iframe: {src: '<%=request.getContextPath()%>/section/createPage.action?sectionId=${sectionId}&sectionName=${sectionName}&typeId='+checkValue}
    });
    parent.layer.close(index);
});

</script>
</div>
</body>
</html>
