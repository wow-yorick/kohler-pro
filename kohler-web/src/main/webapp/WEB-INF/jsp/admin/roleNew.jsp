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
<link rel="stylesheet" type="text/css"
	href="../css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="../js/jquery.ztree.all-3.5.min.js"></script>

</head>

<body>
<div class="container admin_roles">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-1">Id</div>
            	<div class="col-md-5"></div>
            </div>
        	<div class="row">
            	<div class="col-md-1">Name</div>
            	<div class="col-md-5"><input type="text" id="rolename" value="rolename"/></div>   
            </div>  
            <div class="row">
            	<div class="col-md-1">Rights</div>
                <div  style="float:left;width:50%;margin-top: 3px; min-height: 200px;padding-right: 20px; border: 1px solid #e2e2e2;"  >
                <!--  class=" allocate"-->
				<!-- 页面的主要内容content的左边的菜单bar -->
				<ul id="tree" class="ztree"></ul>

			</div>
			</div>
            </div> 
            
               	
        </div>
        <div class="clear"></div>
        <!--  
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-4">
                        		王明
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:2014-07-01 08:00
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-4">
                        		王明
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:2014-07-01 08:00
                        	 </div>
                        </div>
                    </div>
         -->            
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm"
						onclick="addRole()">确定</a> 
        	<a href="#" id="closebtn" class="cancel">取消</a>
        </div>
    </div>
    <!--main结束-->
   <!-- 关闭frame-->
       <script  type="text/javascript">
    		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function(){
    		parent.layer.close(index); //执行关闭
		});
    		
    </script>

</div>
<input type="hidden" id="rolesid"  value="">
<script>
	$(document).ready(function(){
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				//beforeCheck: beforeCheck,
				//onCheck: zTreeOnCheck
			}
		};
		var zNodes = ${jsondata};
		$.fn.zTree.init($("#tree"), setting, zNodes);
		var zTree = $.fn.zTree.getZTreeObj("tree");
	});
	
	//function zTreeOnCheck(event, treeId, treeNode) {};
	
	function addRole()
	{	
		var treeObj = $.fn.zTree.getZTreeObj("tree"),nodes=treeObj.getCheckedNodes(true),size=nodes.length,v="",i=0;
		var arrayObj = new Array();
		if(size > 0){
			for(i;i<size;i++){
				if(nodes[i].isParent == false){
					arrayObj.push(nodes[i].id);
				}
			}
			for(i=0,size=arrayObj.length;i<size;i++){
				if(i < 1){
					v=arrayObj[i];
				}else{
					v+=","+arrayObj[i];
				}
			}
		}
		
		var rolename=$("#rolename").val(),
		    rolesid=v;
		$.post("createSave.action", "rolename=" + rolename + "&rolesid="
				+ rolesid, function(data) {
			var result = eval(data);
			alert(result.msg);
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");
		
	}
	
</script>
</body>
</html>
