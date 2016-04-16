<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>NewAsset</title>

<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
	<script  type="text/javascript" >
	 $(document).ready(function() {
			var setting = {
				view : {
					showLine : false
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			
			var zNodes = ${all};
			
			$.fn.zTree.init($("#tree"), setting, zNodes);
			//选中默认节点
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var folderId = ${folderId};
			var node = treeObj.getNodeByParam("id", folderId, null);
			treeObj.selectNode(node);
			//展开默认节点
			var node=treeObj.getNodeByParam("id",folderId,null);
			treeObj.expandNode(node,true,true,true);
			//$(".checkbox input").click(function(){
		    //    if($(this).parent(".checkbox").children("a").hasClass("active")){
		    //        $(this).parent(".checkbox").children("a").removeClass("active");
		    //        $(this).attr("checked",false);
		    //    }
		    //    else{
		    //        $(this).parent(".checkbox").children("a").addClass("active");
		    //        $(this).attr("checked",true);
		    //    }
		    //});
		});
	/*$(document).ready(function(){
		$(".shadow").hover(function(){
			$(this).children("a.me_active").addClass("active");
			$(this).children("ul").css("display","block");
		},function(){
			$(this).children("a.me_active").removeClass("active");
			$(this).children("ul").css("display","none");
		});
		$(".bar a").click(function(){
			$(".bar a").removeClass("active");
			$(this).addClass("active");
			if($(this).siblings("ul").hasClass("active")){
				$(this).siblings("ul").css("display","none");
				$(this).siblings("ul").removeClass("active");
			}
			else{
				$(this).siblings("ul").css("display","block");
				$(this).siblings("ul").addClass("active");
			}	
		});
		$(".activation").click(function(){
			if($(this).hasClass("active"))
				$(this).removeClass("active");
			else{
				$(this).addClass("active");
			}
		});
		$(".table tbody tr:odd").addClass("tr_bg");
		// 增加jquery代碼開始
		
		// 增加jquery代碼結束
	});*/
	function deleteFolder(){
		var jsonData = "";
        var chooses = $("input[name='folderId']:checked");
        if(chooses.length == 0){
            alert("Please choose a section or a page at least!");
            return false;
        }else{
            //组织id和type以json格式传给后台
            sectionId = $(chooses[0]).val();
            sectionType = $(chooses).parent().parent().parent().children("td").eq(1).text();
            chooses.each(function(index,item){
                if(index == chooses.length-1){
                    jsonData += $(this).val();
                }else{
                    jsonData += $(this).val()+',';
                }
              });
        }
        var isok = confirm("Sure you want to delete？");
        if(isok){
            $.post("deleteFolder.action", "data="+jsonData,function(data) {
                var result = eval(data);
                alert(result.msg);
                location.reload();
            }, "json");
        }
	}
	function createFolderPage(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
        var folderId = ${folderId};
        var node = treeObj.getNodeByParam("id", folderId, null);
        var id = node.id;
        var name = node.name;
		alertFirstIframe("Create folder","createFolder.action?folderId="+id+"&folderName="+name,'840px','600px');
	}
	function createFile(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var folderId = ${folderId};
        var node = treeObj.getNodeByParam("id", folderId, null);
        var id = node.id;
        var name = node.name;
        alertFirstIframe("Create file","createFile.action?folderId="+id+"&folderName="+name,'840px','600px');
    }
	function updateFolder(folderId,typeName,isEdit){
        alertFirstIframe("update","editFolder.action?folderId="+folderId+"&typeName="+typeName+"&isEdit="+isEdit,'840px','600px');
    }
	function searchOneFolder(folderId,typeName){
        alertFirstIframe("search folder","searchOneFolder.action?folderId="+folderId+"&typeName="+typeName,'840px','600px');
    }
	</script>
</head>
<body class="index">
	<div class="wrap">
		<!-- 公共头部head开始 -->
        <c:import url="/common/header.action"></c:import>
			<!-- 头部head中的菜单menu结束 -->
		<!-- 公共头部head结束 -->

		<!-- 内容区域content开始 -->
		<div class="content">
			<!-- 左边的菜单bar开始 -->
			<div class="bar">
				<ul id="tree" class="ztree"></ul>

				
				
			</div>
			<!-- 左边的菜单bar结束 -->
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域query开始-->
				<div class="query row">
					<div class="opera_con col-md-4" style="float:right;">
						<a class="btn long"  href="javascript:void(0);" onclick="createFolderPage();">Create Folder</a>
						<c:if test="${pId.parentId != null}">
						  <a class="btn create" href="javascript:void(0);" onclick="createFile();">Create Files</a>
						</c:if>
						<a class="btn delete" href="javascript:void(0);" onclick="deleteFolder();">Delete</a>
					</div>
						
				</div>
				<!-- 操作及筛选区域query结束-->	
				<!-- 表格table开始 -->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width:10%;">Action</th>
								<th style="width:20%;">Type</th>
								<th style="width:50%;">Name</th>
								<th style="width:8%;">Creator</th>
								<th  class="no_rightborder"  style="width:12%;">Creation Date</th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach items="${folList.datas}" var="list">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="folderId" value="${list.folderId}:${list.masterdataName}">
										</div>
										<a href="javascript:void(0);" onclick="updateFolder('${list.folderId}','${list.masterdataName}','0');" class="ope_icon edit"></a>
										<a href="javascript:void(0);" onclick="updateFolder('${list.folderId}','${list.masterdataName}','1');" class="ope_icon detail"></a>
									</td>
									<td class="fl_left">${list.masterdataName}
									   <input type="hidden" name="typeName" id="typeName" value="${list.masterdataName}">
									</td>
									<td class="fl_left">${list.folderName}</td>
									<td class="fl_left">${list.createName}</td>
									<td class="no_rightborder fl_left">
									   <fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm" />
									</td>
								</tr> 
							</c:forEach>	
						</tbody>
					</table>
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
				<!-- 表格table结束 -->
			</div>
			<!-- content的主要区域content_main结束 -->
			
		</div>
		<!-- 内容区域content结束 -->
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>
</html>