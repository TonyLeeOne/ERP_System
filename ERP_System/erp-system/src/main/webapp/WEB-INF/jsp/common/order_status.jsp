<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%-- Create a hash map and store it in session scope --%>
<jsp:useBean id='map' class='java.util.HashMap' scope='session'>
    <%-- Add initial key/value pairs stored in the hash
    map. The following <c:set> actions are only invoked
    when the hash map is created. --%>
    <c:set target='${map}' property='1' value='待审核'/>
    <c:set target='${map}' property='2' value='未通过'/>
    <c:set target='${map}' property='3' value='待出货'/>
    <c:set target='${map}' property='4' value='已出货'/>
    <c:set target='${map}' property='5' value='待生产'/>
    <c:set target='${map}' property='6' value='待采购'/>
</jsp:useBean>
<c:out value="${map[order.OStatus]}"/>
