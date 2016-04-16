$(function() {
	$('.slides-bar').each(function(){
		var id = this.id;
		// alert(id);
		
		if($('#'+id+' ul').length == 1) {
			$('#'+id+' .slidesjs-previous').hide();
			$('#'+id+' .slidesjs-next').hide();
		}else{
			$('#'+id).slidesjs({
	    		navigation: false,
	    		pagination: false
			})
			$('.img-thumbnail-list .slidesjs-control').css({'width':'520px','margin':'0 auto'});
			$('.img-thumbnail-list #slides').hide();
		}
	})
})
