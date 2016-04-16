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
            	<div class="col-md-5" id="c5">${roledata['ROLE_ID']}</div>
            </div>
        	<div class="row">
            	<div class="col-md-1">Name</div>
            	<div class="col-md-5"><input type="text" id="rolename" value="${roledata['ROLE_NAME']}" /></div>   
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
        <div class="text" style="float:left;margin-left:110px;margin-top:20px;width:450px;margin-bottom:20px;">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-4">
                        		${roledata.CREATE_USER}
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:<fmt:formatDate value="${roledata.CREATE_TIME}"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    	<div class="row" style="margin-top:20px;">
                        	 <div class="col-md-2">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-4">
                        		${roledata.MODIFY_USER}
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:<fmt:formatDate value="${roledata.MODIFY_TIME}"
									pattern="yyyy-MM-dd HH:mm" />
                        		
                        	 </div>
                        </div>
                    </div>            
        <div class="btns">
        	<a href="javascript:void(0);" id="edit" class="confirm"
						onclick="editRole(${roledata['ROLE_ID']})">确定</a> 
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
		$("#edit").show();
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
				onCheck: zTreeOnCheck
			}
		};
		var zNodes = ${jsondata};
		var type   =  ${type};
		if(type == 1){
			$("#rolename").attr("disabled","disabled");
			$("#edit").hide();
		}
		if(typeof(zNodes) != "undefined"){
			$.fn.zTree.init($("#tree"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj('tree'),roletree='${roledata.rid}';
			if(typeof(roletree) != "undefined" && roletree != ''){
				var checktree=roletree.split(","),size=checktree.length,i=0,node,nodes=zTree.transformToArray(zTree.getNodes());
				if(size > 0){
					for(i;i<size;i++){
						 node=zTree.getNodeByParam("id", checktree[i], null);
						 zTree.selectNode(node);
						 zTree.checkNode(node, true, true);
					}
					if(type == 1){
						for (var i=0, l=nodes.length; i < l; i++) {
							zTree.setChkDisabled(nodes[i], true,true);
						}
					}
				}
			}
		}
	});
	function zTreeOnCheck(event, treeId, treeNode) {
	    var treeObj = $.fn.zTree.getZTreeObj("tree"),nodes=treeObj.getCheckedNodes(true),v="",size=nodes.length,i=0;
	    for(i;i<size;i++){
	    	v+=nodes[i].id + ",";
	    }
	    $("#rolesid").val(v);
	};
	function editRole(id)
	{
		var rolename=$("#rolename").val(),
			//roleid=$("#c5").html(),
		    rolesid=$("#rolesid").val();
		$.post("editrole.action", "rolename=" + rolename + "&rolesid="
				+ rolesid+"&roleid="+id, function(data) {
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
