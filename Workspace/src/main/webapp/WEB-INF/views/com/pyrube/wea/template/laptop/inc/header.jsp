<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="wea-one" prefix="wone"%>
<%@ page import="com.pyrube.one.app.Apps" %>
<%@ page import="com.pyrube.wea.context.WebContextHolder" %>
<c:set var="localeCode" value="<%=WebContextHolder.getWebContext().getLocale().toString() %>" />
<div class="app-anchor">
	<ul class="links">
		<li><a href="javascript:void(0);" class="anchor"><span
				class="bg"></span><span class="text"><wone:message code="menu.anchor" /></span></a></li>
	</ul>
</div>
<div class="app-logo"><a href="<%=(String) Apps.config.property("OFFICIAL_WEBSITE_ADDR") %>" target="_blank" class="logo"></a></div>
<div class="app-actions">
	<ul class="links">
		<li class="authen-inout"><% if (Apps.the.user().isGuest()) { %><a href="<c:url value='/authen/signon' />" class="login"><span
				class="bg"></span><span class="text"><wone:message code="menu.signon" /></span></a>
			<% } else { %><a href="<c:url value='/authen/signoff' />" class="logout"><span
				class="bg"></span><span class="text"><wone:message code="menu.signoff" /></span></a><% } %></li>
		<li class="authen-profile"><a id="lnkUserProfile" href="javascript:void(0);" class="profile"><span
				class="bg"></span><span class="text major"><%=Apps.the.user.name() %></span><span
				class="text minor"><wone:message code="locale.${localeCode }" htmlEscaping="false" /></span></a></li>
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function () {
	Page.UserProfile = $('.profile', $('.app-actions')).dropdown({ url : 'user/profile' });
	$('.anchor', $('.app-anchor')).on('click', function () {
		$(this).toggleClass('expanded');
		$('.menubar').toggleClass('expanded');
	});
	
});
</script>