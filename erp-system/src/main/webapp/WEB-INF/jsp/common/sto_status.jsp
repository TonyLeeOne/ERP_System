<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%-- Create a hash map and store it in session scope --%>
<jsp:useBean id='map9' class='java.util.HashMap' scope='session'>
    <%-- Add initial key/value pairs stored in the hash
    map. The following <c:set> actions are only invoked
    when the hash map is created. --%>
    <c:set target='${map9}' property='1' value='待确认'/>
    <c:set target='${map9}' property='2' value='入库失败'/>
    <c:set target='${map9}' property='3' value='已入库'/>
</jsp:useBean>
<c:out value="${map9[storage.stoStatus]}"/>
