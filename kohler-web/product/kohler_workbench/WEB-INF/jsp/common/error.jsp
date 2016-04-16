<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>

<script src="<%=request.getContextPath()%>/js/layer/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.min.js"></script>
<style type="text/css"> 
body,h2{margin:0 ; padding:0; font-family:"微软雅黑";} 
.error .shadow{background-color:#666666; position:absolute; z-index:-999; left:0; top:0;width:100%; height:100%;opacity:0.4;filter: alpha(opacity=40);-moz-opacity: 0.4;} 
.error .errorbtn {width:100px; height:35px; line-height:35px; background:#607ca1; display:block; color:#fff; margin:0 auto; font-weight:bold;-moz-border-radius: 3px; /* Gecko browsers */-webkit-border-radius: 3px;   /* Webkit browsers */border-radius:3px;/* W3C syntax */ text-align:center; cursor:pointer;}
.error .errorbtn:hover{ background:#cccccc; display:block; color:#000; }
.error .main{ width:600px; height:200px; background-color:#fff;box-shadow: 2px 2px 2px 2px #7f7f80;}
.error .title{ height:40px; line-height:40px;padding-left:12px;background-color:#607ca1; color:#fff;}
.error #errorMessage{ padding-top:20px; padding-left:12px; margin-bottom:50px;}
</style>
</head>

<body>
    <div class="error">
        <!--shadow开始-->
        <div class="shadow"></div>
        <!--shadow结束-->
        <!--main开始-->
        <div class="main">
            <div class="title">Error Page</div>
            <div id="errorMessage">Operation is abnormal, please try again later or contact the administrator.</div>
            <div class="errorbtn">Close</div>
        </div>
        <!--main结束-->
        <script type="text/javascript">
            $(document).ready(function(){
                    var winheight=$(window).height(); //浏览器时下窗口可视区域高度
                    var winwidth=$(window).width(); //浏览器时下窗口可视区域高度
                    var topheight=(winheight-200)/2;
                    var leftwidth=(winwidth-600)/2;
                    $(".eborder").css("margin-left",leftwidth);
                    $(".eborder").css("margin-top",topheight);
                    $(".main").css("margin-left",leftwidth);
                    $(".main").css("margin-top",topheight);
                    $(".shadow").css("height",winheight);//class shadow的高度
                });
            
            $(".errorbtn").click(function(){
            	var index = parent.layer.getFrameIndex(window.name);
            	parent.layer.close(index);

                $(".error").detach(); 
            });

            
        </script>
    </div>
</body>
</html>

