<%@ taglib uri="wea-one" prefix="wone"%>
<%@ page import="com.pyrube.one.app.Apps" %>
<div class="app-official">
	<ul class="links">
		<li><a href="<%=(String) Apps.config.property("OFFICIAL_WEBSITE_ADDR") %>" 
			target="_blank"><span><wone:message code="global.label.official-website" /></span></a></li>
	</ul>
</div>
<div class="app-misc">
	<div class="app-logo"><a href="javascript:void(0);" class="logo"></a></div>
	<div class="app-copyright"><span><wone:message code="global.label.copyright" 
		arguments='<%=(String) Apps.config.property("COPYRIGHT_YEAR") %>' htmlEscaping="false" /></span></div>
</div>
