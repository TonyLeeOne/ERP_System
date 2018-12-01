/**
 * 所有列表页删除、批量删除公用
 */
jQuery(function () {

    /*列表删除公用*/
    $(document).on("click", "#delete", function (e) {
        member_del(this, $(this).attr("href"));
        e.preventDefault();
        return false;
    }).on("click", "#batch_delete", function (e) {
        delAll(this);
        e.preventDefault();
        return false;
    });

    function member_del(obj, url) {
        layer.confirm('确认要删除吗？', function (index) {
            $.get(url, function (res) {
                //发异步删除数据
                if (res == '数据删除成功') {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 2000});
                } else {
                    layer.msg(res + '!', {icon: 0, time: 2000});
                }

            });
        });
    }

    function delAll(obj) {
        var param = tableCheck.getData();
        var names = [];
        jQuery("table tbody .layui-form-checked").each(function () {
            names.push(jQuery(this).data("name"));
        });
        layer.confirm('确认要删除吗？<br />' + names.join("，"), function (index) {
            $.ajax({
                url: $(obj).data("batch-url"),
                type: "post",
                contentType: 'application/json;charset=utf-8',
                processData: false,
                data: JSON.stringify(param),
                success: function (res) {
                    //捉到所有被选中的，发异步进行删除
                    if (res == '数据删除成功') {
                        // $(".layui-form-checked").not('.header').parents('tr').remove();
                        // layer.msg('已删除!', {icon: 1, time: 1000});
                        layer.alert("已删除", {icon: 6}, function () {
                            // 获得frame索引
                            // var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            window.location.reload();
                            // parent.layer.close(index);
                        });
                    } else {
                        layer.msg(res + '!', {icon: 0, time: 2000});
                    }
                }
            });
        });
    }
});
