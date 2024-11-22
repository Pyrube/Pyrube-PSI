<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="tiles" prefix="tiles" %>
<%@ taglib uri="jstl-core" prefix="c" %>
<%-- This is the template page for box, Nov-23-2015, Aranjuez --%>
<%
// Set standard HTTP/1.1 no-cache headers.
response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
// Set standard HTTP/1.0 no-cache header.
response.setHeader("Pragma", "no-cache");
// Set to expire far in the past.
response.setHeader("Expires", "0");
%>
<tiles:importAttribute name="javascripts" />
<c:forEach var="jsPathAndName" items="${javascripts}"><script type="text/javascript" src="<%=request.getContextPath()%>${jsPathAndName}"></script>
</c:forEach>
<tiles:insertAttribute name="body" />