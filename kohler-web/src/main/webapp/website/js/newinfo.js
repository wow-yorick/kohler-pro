        $(function(){
            $('.mm_btn').click(function() {
                $(this).addClass('mm_btn_actived').siblings().removeClass('mm_btn_actived');
            })
            $('#slides').slidesjs({
                navigation: false,
                pagination: false,
                height:35,
                play: {
                    active: true,
                    effect: "slide",
                    interval: 2500,
                    auto: true,
                    swap: true,
                    pauseOnHover: true,
                    restartDelay: 2500
                }
            })
        })
