<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="tiles" prefix="tiles" %>
<%-- This is the template page for grid, Nov-23-2015, Aranjuez --%>
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
<tiles:insertAttribute name="body" />