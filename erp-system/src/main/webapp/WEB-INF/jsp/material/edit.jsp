<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="mName" class="layui-form-label">
                        物料名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mName" name="mName" value="${material.mName}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="mSn" class="layui-form-label">
                        物料编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mSn" name="mSn" value="${material.mSn}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="mCount" class="layui-form-label">
                        库存数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="mCount" name="mCount"
                               value="${material.mCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-col-md6">
                        <input type="hidden" class="input-file" name="mId" value="${material.mId}">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <c:if test="${!empty material.mStatus}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-block" id="single" value="${material.mStatus}">
                            <input type="radio" name="mStatus" value="1" title="可用" id="pro">
                            <input type="radio" name="mStatus" value="2" title="禁用" id="stop">
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="layui-row">
                <div class="layui-col-md10">
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="mNote" placeholder="请输入内容"
                                      class="layui-textarea">${material.mNote}</textarea>
                        </div>
                    </div>
                </div>
            </div>




            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${not empty material.mId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty material.mId}">
                        <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                            新增
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    $(function () {
        if($("#single").attr('value')=='1'){
            $("#pro").attr('checked',true);
        }
        if($("#single").attr('value')=='2'){
            $("#stop").attr('checked',true);
        }
        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form
                , layer = layui.layer;

            //自定义验证规则
            // form.verify({
            //     nikename: function (value) {
            //         if (value.length < 5) {
            //             return '昵称至少得5个字符啊';
            //         }
            //     }
            //     , pass: [/(.+){6,12}$/, '密码必须6到12位']
            //     , repass: function (value) {
            //         if ($('#L_pass').val() != $('#L_repass').val()) {
            //             return '两次密码不一致';
            //         }
            //     }
            // });

            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/material/add",
                    data: data.field,
                    type: "POST",
                    async: false,
                    success: function (res) {
                        if (res == '数据新增成功') {
                            //发异步，把数据提交给php
                            layer.alert("增加成功", {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        } else {
                            layer.alert("新增失败")
                        }
                        return false;
                    },
                    error: function (res) {
                        console.log("error:");
                        console.log(res);
                    }
                });
                return false;
            });
            //监听提交
            form.on('submit(edit)', function (data) {
                $.ajax({
                    url: "/material/update",
                    data: data.field,
                    type: "POST",
                    // dataType: "json",
                    // contentType: "application/json",
                    async: false,
                    success: function (res) {
                        if (res == "数据更新成功") {
                            //发异步，把数据提交给php
                            layer.alert(res, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        } else {
                            layer.alert("更新失败");
                        }

                        return false;
                    },
                    error: function (res) {
                        console.log("error:");
                        console.log(res);
                    }
                });
                return false;
            });

        });
    })

    var fileInput = document.querySelector('.input-file');/**
     * 异步上传图片
     */
    fileInput.addEventListener('change', function (e) { //监听change事件，选择文件后触发
        var formData = new FormData();
        for (var i = 0; i < this.files.length; i++) {
            formData.append("files", this.files[i]);
        }
        $.ajax({
            url: "/image/upload",
            method: 'post',
            data: formData,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (result) {
                var s = "";
                if (result.status == 0)
                    $.each(result.data, function (index, url) {
                        s += "<img src='" + url + "'/>";
                    })
                $("#pic").html(s);
                $("#proImage").val(result.data.toString());
            },
            error: function (qXHR, textStatus, errorThrown) {
                console.log(textStatus + errorThrown);
            }
        });
    })
</script>
</body>

</html>