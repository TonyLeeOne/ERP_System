$(function () {
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

var E = window.wangEditor
var editor1 = new E('#div1', '#div2')  // 两个参数也可以传入 elem 对象，class 选择器
editor1.create();