<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../common/header.jsp" %>
<style>
    .upload-file {
        position: relative;
        /*width: 80px;*/
        padding: 10px 15px;
        border: 1px solid rgb(119, 154, 80);
        border-radius: 5px;
        background-color: rgb(66, 215, 142);
        color: #333333;
        font-size: 14px;
        text-align: center;
        overflow: hidden;
    }

    .upload-file span {
    / / 单行显示 text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
    }

    .upload-file:hover {
    / / 简单的hover效果 font-size: 15 px;
        border-color: rgb(39, 226, 81);
    }

    .upload-file input[type='file'] {
        /*height: 100%;*/
        /*width: 100%;*/
        position: absolute;
    / / 设置为绝对定位，不会影响到其他元素 top: 0;
        right: 0;
        opacity: 0;
    / / 透明度为0 filter: alpha(opacity = 0);
        cursor: pointer;
    }

    img {
        height: 100px;
        width: 100px;
    }
</style>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="customName" class="layui-form-label">
                        产品编号<span class="x-red">*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customName" name="proCode" value="${product.proCode}"
                               autocomplete="off" lay-verify="required"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="customAddress" class="layui-form-label">
                        产品名称<span class="x-red">*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customAddress" name="proName" value="${product.proName}"
                               autocomplete="off" lay-verify="required"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="customCode" class="layui-form-label">
                        库存数量<span class="x-red">*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="customCode" name="proCount" lay-verify="required"
                               value="${product.proCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="customTel" class="layui-form-label">
                        单价<span class="x-red">*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="customTel" name="proPrice" lay-verify="required"
                               value="${product.proPrice}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <c:if test="${!empty product.proId}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-block" id="single" value="${product.proStatus}">
                            <input type="radio" name="proStatus" value="1" title="量产中" id="pro">
                            <input type="radio" name="proStatus" value="2" title="已停产" id="stop">
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="layui-row">
                <div class="layui-col-md10">
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="proNote" placeholder="请输入内容"
                                      class="layui-textarea">${product.proNote}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="customPublish" class="layui-form-label">
                        图片
                    </label>
                    <div class="upload-file layui-input-inline" id="customPublish">
                        <input type="file" class="input-file" multiple="true" id="select">
                        <span class="tip">点击上传图片</span>
                    </div>
                </div>

                <div class="layui-col-md6">

                    <label for="proUnit" class="layui-form-label">
                        单位 <span class="x-red">*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="proUnit" lay-verify="required" lay-search id="proUnit" lay-verify="required"
                                value="${product.proUnit}">
                            <c:if test="${! empty product.proUnit}">
                                <option value="${product.proUnit}">${product.proUnit}</option>
                            </c:if>
                            <option value="PCS">PCS</option>
                            <option value="m">m</option>
                            <option value="m&sup2;">m&sup2;</option>
                            <option value="kg">kg</option>
                            <option value="瓶">瓶</option>
                            <option value="盒">盒</option>
                            <option value="包">包</option>
                            <option value="条">条</option>
                            <option value="卷">卷</option>
                            <option value="本">本</option>
                            <option value="台">台</option>
                        </select>
                        <%--<input type="number" id="mUnit" name="mUnit"--%>
                        <%--value="${material.mUnit}" autocomplete="off" class="layui-input">--%>
                    </div>

                <div class="layui-col-md6">
                        <input type="hidden" class="input-file" value="${product.proImage}" name="proImage" id="proImage">
                </div>


            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label for="customPublish" class="layui-form-label">
                        图片展示
                    </label>
                    <div class="layui-col-md6" id="pic">
                        <c:if test="${! empty product.proImage}">
                            <c:set value="${fn:split(product.proImage,',')}" var="urls"/>
                                <c:forEach items="${urls}" var="url">
                                    <img src="${url}">
                                </c:forEach>
                        </c:if>
                        <c:if test="${empty product.proImage}">
                            <img src="/images/default.PNG">
                        </c:if>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <input type="hidden" class="input-file" name="proId" value="${product.proId}">
                </div>

            </div>

            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${not empty product.proCode}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty product.proCode}">
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
                    url: "/product/add",
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
                    url: "/product/update",
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