$(function() {

   var startNum = 1; // 起始位置

   /*
    * @param
    */
   function waterfall() {
      var init = 0;

      $(window).scroll(function() {
         var docheight = parseInt($(document).height());
         var windowheight = parseInt($(this).height());
         var scroll = parseInt($(this).scrollTop());
         var bottomheight = parseInt($('.pub_footer').height());
         var content = '';
         if (init <= scroll) { //下滚
            if ((windowheight + scroll) >= (docheight - bottomheight - 500)) {
               $.ajax({
                  type: "GET",
                  url: "test1.json",
                  data: {"data":1},
                  dataType: "json",
                  success: function(msg) {
                     if(startNum <= msg.length) {
                     // console.log(msg.length);
                        $.each(msg,function(i, item) {
                           content = '<li>';
                           content += '<div>';
                           content += '<div class="item_img"><img src="' + item.imgurl + '" alt=""></div>';
                           content += '<span>';
                           content += '<h3>' + item.pro_title + '</h3>';
                           content += '<p>' + item.pro_detail + '</p>';
                           content += '<p class="type">' + item.pro_sku + '</p>';
                           content += '</span>';
                           content += '</div>';
                           content += '<a href="bathspd.html" class="detail_popup">';
                           content += '<div class="item_img"><img src="' + item.imgurl + '" alt=""></div>';
                           content += '<span>';
                           content += '<h3>' + item.pro_title + '</h3>';
                           content += '<p>' + item.pro_detail + '</p>';
                           content += '<p class="type">' + item.pro_sku + '</p>';
                           content += '</span>';
                           content += '</a>';
                           content += '<div class="preview">快速预览</div>';
                           content += '</li>';
                           $('.item-panel').append(content);
                           startNum ++;
                        })
                     }
                  }
               })
            }
         }
         init = scroll;
      });
   }

   waterfall();
})

$(window).load(function() {
   function setBanner(bannerArr) {
      var content = '';
      var contentL = new Array();
      
      $.ajax({
         type: "GET",
         url: "test2.json",
         data: {"data":1},
         async:false,
         dataType: "json",
         success: function(msg) {
            $.each(msg,function(key, item) {
               contentL[key] = '<li class="large">';
               contentL[key] += '<div class="item_img_large"><img src="' + item.imgurl + '" alt=""></div>';
               contentL[key] += '<span class="frame"></span>';
               contentL[key] += '<span class="large_txt">';
               contentL[key] += '<h2>"' + item.pro_title1 + '" </h2>';
               contentL[key] += '<h2>"' + item.pro_title2 + '" </h2>';
               contentL[key] += '<p>"' + item.pro_detail + '" </p>';
               contentL[key] += '</span>';
               contentL[key] += '<a href="javascript:;" class="large_link"></a>';
               contentL[key] += '</li>';
            })
         }
      })

      $.ajax({
         type: "GET",
         url: "test1.json",
         data: {"data":1},
         async: false,
         dataType: "json",
         success: function(msg) {
               var index = 0;
               var numfix = 0;
               $.each(msg,function(i, item) {
                  if(index < bannerArr.length) {
                     var insertNum = (bannerArr[index][0]-1)*3 + bannerArr[index][1];
                     if(insertNum-numfix == i+1) {
                        content += contentL[index];
                        index++;
                        numfix = 2;
                     }
                  }
                  content += '<li>';
                  content += '<div>';
                  content += '<div class="item_img"><img src="' + item.imgurl + '" alt=""></div>';
                  content += '<span>';
                  content += '<h3>' + item.pro_title + '</h3>';
                  content += '<p>' + item.pro_detail + '</p>';
                  content += '<p class="type">' + item.pro_sku + '</p>';
                  content += '</span>';
                  content += '</div>';
                  content += '<a href="bathspd.html" class="detail_popup">';
                  content += '<div class="item_img"><img src="' + item.imgurl + '" alt=""></div>';
                  content += '<span>';
                  content += '<h3>' + item.pro_title + '</h3>';
                  content += '<p>' + item.pro_detail + '</p>';
                  content += '<p class="type">' + item.pro_sku + '</p>';
                  content += '</span>';
                  content += '</a>';
                  content += '<div class="preview">快速预览</div>';
                  content += '</li>';
               })
         }
      })
      $('.item-panel').append(content);
   }

   /* [1,1] 第1行，第1列 */
   var bannerArr = [[1,1],[4,2]];
   setBanner(bannerArr);

})