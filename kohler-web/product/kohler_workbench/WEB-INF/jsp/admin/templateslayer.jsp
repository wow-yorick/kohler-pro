<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>adminTemplateslayer弹出层</title>
<link href="../../../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../../js/layer/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index = $tab_li.index(this);
		$('div.tab_box > .box').eq(index).show().siblings().hide();
	});	
	$("#txt span").click(function(){
		var boxClass=$(this).attr("class");
		if(boxClass=="check"){
			$(this).attr("class","checked");
			}else{
			$(this).attr("class","check");}
		});		

});
</script>
</head>

<body>
<div class="container admin_condition">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<div class="search">
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4">1001</div>
            	<div class="col-md-2 tn_c">Type</div>
            	<div class="col-md-4">
                		<select>
                           <option>Catalog</option>
                           <option>Catagory</option>
                           <option>Product</option>
                           <option>Other</option>
                   		</select>                  
                </div>    
            </div>
        	<div class="row">
            	<div class="col-md-2">Name</div>
            	<div class="col-md-4"><input type="text" value="Catalog Template CN"/></div>
            	<div class="col-md-2 tn_c">Folder</div>
            	<div class="col-md-4">
                		<select>
                           <option>SectionFolder</option>
                           <option>CategoryFolder</option>
                           <option>SubCategoryFolder</option>
                           <option>ProductFolder</option>
                   		</select>                  
                </div>    
            </div>  
            <div class="row">
            	<div class="col-md-2">Description</div>
                <div class="col-md-10"><input type="text"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Physical Name Rule</div>
                <div class="col-md-10"><input type="text" value="@Name"/></div> 
            </div> 
            <div class="row">
            	<div class="col-md-2 line">Template File Content</div>
                <div class="col-md-10"> <textarea > xxxxxx�</textarea ></div> 
            </div>     	
        </div>  
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-1">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-5">
                        		王明
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:2014-07-01 08:00
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-1">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-5">
                        		王明
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:2014-07-01 08:00
                        	 </div>
                        </div>
                    </div>            
        <div class="btns">
        	<a href="#" class="confirm">确定</a>
        	<a href="#" class="cancel">取消</a>
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
</body>
</html>
