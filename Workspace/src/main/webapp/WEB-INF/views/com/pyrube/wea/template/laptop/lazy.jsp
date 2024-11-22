<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="tiles" prefix="tiles" %>
<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="wea-one" prefix="wone" %>
<%@ page import="com.pyrube.wea.context.WebContextHolder" %>
<%-- This is the template page for general, Nov-23-2015, Aranjuez --%>
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
<!DOCTYPE HTML>
<html>
<head>
<title><wone:message code="global.title.page" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="theme-color" content="default" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<tiles:importAttribute name="favicon" />
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>${favicon}">
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>${favicon}" />
<tiles:importAttribute name="themes" />
<c:forEach var="cssPathAndName" items="${themes}"><link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>${cssPathAndName}">
</c:forEach>
<tiles:importAttribute name="javascripts" />
<c:forEach var="jsPathAndName" items="${javascripts}"><script type="text/javascript" src="<%=request.getContextPath()%>${jsPathAndName}"></script>
</c:forEach>
</head>
<body jsea-page-options="root: '<%=request.getContextPath()%>/',
	locale: '<%=WebContextHolder.getWebContext().getLocale().toString() %>',
	theme: '<%=WebContextHolder.getWebContext().getTheme() %>',
	lazy: true">
	<div class="app-header"><tiles:insertAttribute name="header" /></div>
	<div class="app-container">
		<tiles:insertAttribute name="menubar" />
		<div id="appMessagebar" class="messagebar"></div>
		<div id="appNavbar" class="navbar"><ul></ul></div>
		<div id="appToolbar" class="toolbar"><ul></ul></div>
		<div class="lazy-page pagebody">
			<div class="body-container">
				<div id="bodywrapper" class="body-wrapper"><tiles:insertAttribute name="body" /></div>
			</div>
		</div>
	</div>
	<div class="app-footer"><tiles:insertAttribute name="footer" /></div>
</body>
</html>
