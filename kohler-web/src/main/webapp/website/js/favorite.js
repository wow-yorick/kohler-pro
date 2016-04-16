$(function() {
		//鼠标滑过出现分享编辑按钮
	$(".WaterFall").mouseover(function(){
		$(this).children(".morenav").show();
		}).mouseout(function(){
			$(this).children(".morenav").hide();
			});
			
	$(".pic").mouseover(function(){
		$(this).children(".morenav").show();
		}).mouseout(function(){
			$(this).children(".morenav").hide();
			
			});
	
	// 收藏按钮
	$('.product_btn.save').click(function() {
		var skuMetadataId = $(this).find('input[id=saveSkuMetadataId]').val();
		if(skuMetadataId!=null&&skuMetadataId!='undefined'){
			alert("获取的sku="+skuMetadataId);
			$.cookie('skuMetadataId', skuMetadataId);
			var url = chinaweb+"/collect/savefolder.action";
			//$.post("http://localhost:8080/kohler-website/utility/compare/getCompareList.action?skuMetadataid="+skuMetadataId+"&lang=1&jsoncallback=?",function(json){
				$.post(url,"skuMetadataId="+skuMetadataId+"&lang=1", function(data){
				alert(data);

			  },"json");	
		}
	});	
	
	//发送邮件
	$('.send').click(function() {
		var form = $('.emaillayer').find('form');
		//alert("success");
		var url = chinaweb+"/email/sendEmail.action";
		$.post(url,form.serialize(),function(data){
			alert(data);
		},"json");
	});

			
	
	//收藏页面点击出现社交分享下拉
	$('.share').click(function() {
		if($(this).next('.sharenav').is(":visible")){
			$(this).next('.sharenav').fadeOut();
			$(this).css("background","url(images/collection/collectionbg.png) -132px -1px no-repeat");
			}else{
			$(this).next('.sharenav').fadeIn();	
			$(this).css("background","url(images/collection/collectionbg.png) -132px -49px no-repeat");
				}
	})
	$('#share').click(function() {
		if($(this).next('.collection').is(":visible")){
			$(this).next('.collection').fadeOut();
		
			}else{
			$(this).next('.collection').fadeIn();	
	
				}
	})
	//编辑框的弹出层
	var docheight=$(document).height();
	var docwidth=$(document).width();
	var winheight=$(window).height();
	var winwidth=$(window).width();
	$(".shadow").css("width",docwidth);
	$(".shadow").css("height",docheight);
	$(".editcont").css("top",(winheight-600)/2);
	$(".editcont").css("left",(winwidth-700)/2);
	$(".createcollection .editcont").css("top",(winheight-480)/2);
	$(".successful").css("top",(winheight-300)/2);
	$(".successful").css("left",(winwidth-550)/2);
	$(".previewcont").css("left",(winwidth-1100)/2);
	$(".previewcont").css("top",(winheight-590)/2);
	$(".morenav .edit").click(function(){
		$(".editlayer").show();
		})
	$(".editlayer .closed").click(function(){
		$(".editlayer").hide();
		})
	//邮件分享弹出框
	$(".email").click(function(){
		$(".emaillayer").show();
		})
	$(".emaillayer .closed").click(function(){
		$(".emaillayer").hide();
		});
	//邮件分享 鼠标焦点
	$(".email").focus(function(){
			var text_val=$(this).val();
			if(text_val=="邮件地址"){
				$(this).val("");
				$(this).addClass("emailbg");
				}
			});
		$(".email").blur(function(){
			var text_val=$(this).val();
			if(text_val==""){
				$(this).val("邮件地址");
				$(this).removeClass("emailbg");
				}
			});
	$(".emaillayer .send").click(function(){
		$(".emaillayer .editcont").hide();
		$(".successful").show();
		})
	$(".emaillayer .sure").click(function(){
		$(".successful").hide();
		$(".emaillayer .editcont").show();
		$(".emaillayer").hide();
		});
	//预览弹出框
	$(".chart").click(function(){
		$(".previewlayer").show();
		})
	$(".previewlayer .closed").click(function(){
		$(".previewlayer").hide();
		});
	//创建收藏夹弹出框
	$(".create").click(function(){
		$(".createcollection").show();
		})
	$(".createcollection .closed").click(function(){
		$(".createcollection").hide();
		});
	$(".item .pic .add_collection").click(function(){
		$(".createcollection").show();
		})
})