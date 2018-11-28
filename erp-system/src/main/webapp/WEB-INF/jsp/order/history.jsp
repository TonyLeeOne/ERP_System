<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="layui-col-md6 layui-col-md-offset3">
        <ul class="layui-timeline">
            <c:if test="${!empty ships}">
            <c:forEach items="${ships}" var="ship">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title"><i class="layui-icon">&#xe637;</i>  ${ship.SShipDate}</h3>
                    <p>

                        <i class="layui-icon layui-icon-user">【客户】: ${ship.order.OCustomName}</i><br>
                        <i class="layui-icon layui-icon-tabs">&#xe62a;</i>
                        【产品编号】: ${ship.SProCode}
                        【出货数量】: ${ship.SShipCount}
                        【当前状态】: <%@include file="../common/ship_status.jsp" %><br>
                        <i class="layui-icon">&#xe613;</i>
                        【审核人】: ${ship.SAuditor}
                        【审核日期】: ${ship.SAuditDate}
                        【确认人】: ${ship.SSurer}


                    </p>
                </div>
            </li>
            </c:forEach>
            </c:if>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h5 class="layui-timeline-title">没有更多记录了</h5>
                </div>
            </li>
        </ul>
</div>
</body>

</html>