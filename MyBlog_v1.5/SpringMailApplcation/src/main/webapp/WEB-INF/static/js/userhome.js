$("[id='edit']").each(function () {
    $(this).click(function () {
        window.location.href = "/blog/edit/" + $(this).val();
    });
});


$("[id='delete']").each(function () {
    $(this).click(function () {
        var res=window.confirm("确定删除？？");
        if(res==true){
            window.location.href = "/blog/delete/" + $(this).val();
        }
    });
});


$(function () {
    $("[id='utime']").each(function () {
        if($(this).text()=="　"){
            $(this).html("<span class='glyphicon glyphicon-pencil'></span>　暂未更新");
        }
    });
    $("#show").hide();
    $("#exit").hide();

    $("[data-toggle='tooltip']").tooltip();
    $("#hide").click(function () {
        $("#sidebar").hide();
        $("#show").show();
        $(this).hide();
    });

    $("#show").click(function () {
        $("#sidebar").show();
        $("#hide").show();
        $(this).hide();
    });

    $("#enter").click(function () {
        $("#exit").show();
        $(this).hide();
    });

    $("#exit").click(function () {
        $("#enter").show();
        $(this).hide();
    });


});

function toggleFullScreen() {
    if (!document.fullscreenElement && // alternative standard method
        !document.mozFullScreenElement && !document.webkitFullscreenElement) {// current working methods
        if (document.documentElement.requestFullscreen) {
            document.documentElement.requestFullscreen();
        } else if (document.documentElement.mozRequestFullScreen) {
            document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullscreen) {
            document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
        }
    } else {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
    }
}
