<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="layui-container">
    <table class="layui-table">
        <thead>
        <tr>
            <th>工单编号</th>
            <th>开始日期</th>
            <th>结束日期</th>
            <th>计划数</th>
            <th>生产数</th>
            <th>状态</th>
            <th>进度</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${! empty manOrders}">
            <c:forEach items="${manOrders}" var="mOrder">
                <tr>
                    <td>${mOrder.moSn}</td>
                    <td>${mOrder.moStartDate}</td>
                    <td>${mOrder.moEndDate}</td>
                    <td>${mOrder.moCount}</td>
                    <td>${mOrder.moWaitCount}</td>
                    <td>
                        <%@include file="../common/mo_status.jsp" %>
                    </td>
                    <td width="100px">
                        <c:if test="${! empty mOrder.moWaitCount}">
                            <div class="layui-progress">
                                <div class="layui-progress-bar layui-bg-green"
                                     lay-percent="${mOrder.moWaitCount}/${mOrder.moCount}"></div>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
</div>
</body>

</html>