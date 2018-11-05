$(function () {
    $("#register").hide();
    $("#doRegis").click(function () {
        $("#register").show();
        $("#login").hide();
    });
    $("#doLogin").click(function () {
        $("#register").hide();
        $("#login").show();
    });

    $("#gifCode1").blur(function () {
        $.ajax({
            url: "/user/gifCodeVerify",
            method: "get",
            error: function () {

            },
            success: function (data) {
                if (data.toString() == $("#gifCode1").val()) {
                    $("#logGif").attr('src', '../images/ok.gif');
                } else {
                    $("#logGif").attr('src', '../images/ng.gif');
                }
            }
        });
    });

    $("#gifCode").blur(function () {
        $.ajax({
            url: "/user/gifCodeVerify",
            method: "get",
            error: function () {
            },
            success: function (data) {
                if (data.toString() == $("#gifCode").val()) {
                    $("#logGif1").attr('src', '../images/ok.gif');
                } else {
                    $("#logGif1").attr('src', '../images/ng.gif');
                }
            }
        });
    });

    $("#username").blur(function () {
        $.ajax({
            url: "/user/getUserName",
            method: "POST",
            data: {username: $("#username").val()},
            error: function () {
            },
            success: function (data) {
                if (data.toString() == $("#username").val().toString()) {
                    $("#nameGif").attr('src', '../images/ng.gif');
                } else {
                    $("#nameGif").attr('src', '../images/ok.gif');

                }
            }
        });
    });

});

function chageCode() {
    document.getElementById("codeImage").src = "../captcha/kaptcha.jpg?" + Math.random();
}

function chageCodes() {
    document.getElementById("codeImage1").src = "../captcha/kaptcha.jpg?" + Math.random();
}