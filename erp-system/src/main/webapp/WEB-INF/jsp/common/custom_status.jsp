<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%-- Create a hash map and store it in session scope --%>
<jsp:useBean id='map1' class='java.util.HashMap' scope='session'>
    <%-- Add initial key/value pairs stored in the hash
    map. The following <c:set> actions are only invoked
    when the hash map is created. --%>
    <c:set target='${map1}' property='1' value='有效'/>
    <c:set target='${map1}' property='2' value='无效'/>
</jsp:useBean>
<c:out value="${map1[custom.customStatus]}"/>
