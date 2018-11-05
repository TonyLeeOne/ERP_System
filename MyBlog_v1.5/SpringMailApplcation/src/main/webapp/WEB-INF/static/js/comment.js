$(function () {
    $("[data-toggle='tooltip']").tooltip();
    $("#sub").hide();
    $("#comments").hide();
    $("#cancel").hide();
    $("#comment").click(function () {
        $("#sub").show();
        $("#comments").show();
        $("#cancel").show();
        $(this).attr('disabled', 'disabled');
    });

    $("#cancel").click(function () {
        $("#sub").hide();
        $("#comments").hide();
        $(this).hide();
        $("#comment").removeAttr('disabled');
    });
    $("#sub").click(function () {
        $("#content").val(editor1.txt.html());
    });
});
