<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%-- Create a hash map and store it in session scope --%>
<jsp:useBean id='map7' class='java.util.HashMap' scope='session'>
    <%-- Add initial key/value pairs stored in the hash
    map. The following <c:set> actions are only invoked
    when the hash map is created. --%>
    <c:set target='${map7}' property='1' value='待审核'/>
    <c:set target='${map7}' property='2' value='审核不通过'/>
    <c:set target='${map7}' property='3' value='待入库'/>
    <c:set target='${map7}' property='4' value='已入库'/>
</jsp:useBean>
<c:out value="${map7[purchase.mphVendorId]}"/>
