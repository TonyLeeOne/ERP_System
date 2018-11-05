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
    $("#preView").click(function () {
        $("#editor").hide();
        $("#myModalLabel").text($("#subject").val());
        $(".modal-body").html(editor.txt.html());
    });

    $("#close").click(function () {
        $("#editor").show();
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

document.getElementById('send').addEventListener('click', function () {
    // 读取 html
    document.getElementById('content').value = editor.txt.html();
}, false);

$("#myModal").modal({backdrop: "static", keyboard: false,show:false});
