<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%-- Create a hash map and store it in session scope --%>
<jsp:useBean id='ship_map' class='java.util.HashMap' scope='session'>
    <%-- Add initial key/value pairs stored in the hash
    map. The following <c:set> actions are only invoked
    when the hash map is created. --%>
    <c:set target='${ship_map}' property='1' value='待审核'/>
    <c:set target='${ship_map}' property='2' value='审核不通过'/>
    <c:set target='${ship_map}' property='3' value='待确认'/>
    <c:set target='${ship_map}' property='4' value='已出货'/>
</jsp:useBean>
<c:out value="${ship_map[ship.SStatus]}"/>
