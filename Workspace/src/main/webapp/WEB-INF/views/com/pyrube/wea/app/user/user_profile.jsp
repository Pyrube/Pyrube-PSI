<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.user.UserHolder" %>
<%@ page import="com.pyrube.wea.context.WebContextHolder" %>
<c:set var="userName" value="<%=UserHolder.getUser().getName() %>" />
<c:set var="localeCode" value="<%=WebContextHolder.getWebContext().getLocale().toString() %>" />
<c:set var="timezoneId" value="<%=WebContextHolder.getWebContext().getTimezone().getID() %>" />
<c:set var="themeName" value="<%=WebContextHolder.getWebContext().getTheme() %>" />
<!-- Profile Container Begins -->
<div id="userProfile" class="profile-container">
	<seael:elemset type="profile">
		<seael:multiprops label="false"><seael:textprop value="${userName }" cssClass="username" />
		<% if (UserHolder.getUser().isGuest()) { %><seael:link name="signon" /><seael:link name="signup" />
		<% } else { %><seael:link name="signoff" /><seael:link name="changePassword" /><% } %></seael:multiprops>
	</seael:elemset>
	<seael:elemset type="profile">
		<% if (!UserHolder.getUser().isGuest()) { %><seael:multiprops label="global.label.mobile"><seael:textprop type="mask" path="profile.mobile" /><seael:link name="changeMobile" /></seael:multiprops>
		<seael:multiprops label="global.label.email"><seael:textprop type="mask"  path="profile.email" /><seael:link name="changeEmail" /></seael:multiprops><% } %>
		<seael:textprop label="global.label.timezone" value="${timezoneId }" />
		<% if (!UserHolder.getUser().isGuest()) { %><seael:dateprop label="global.label.lastLogonTime" path="profile.lastLogonTime" format="timestamp" local="true" /><% } %>
	</seael:elemset>
	<seael:elemset type="panel" stylesheet="profiles locales">
		<li class="profile user-locale ${localeCode }"><a href="javascript:void(0);"><span class="locale" /><span class="icon" /><wone:message code="locale.${localeCode }" /></a>
			<ul class="profile-items" url="user/locales" modify="user/locale/"></ul></li>
	</seael:elemset>
	<seael:elemset type="panel" stylesheet="profiles themes">
		<li class="profile user-theme ${themeName }"><a href="javascript:void(0);"><span class="theme" /><span class="icon" /><wone:message code="theme.${themeName }" /></a>
			<ul class="profile-items" url="user/themes" modify="user/theme/"></ul></li>
	</seael:elemset>
</div>
<!-- Profile Container Ends -->
<script type="text/javascript">
$(function() {
	$('.profile-area').on('click.jsea', 'a.signon', function () {
		window.location = JSEA.getPageContext().resolveUrl('authen/signon');
	});
	$('.profile-area').on('click.jsea', 'a.signup', function () {
		window.location = JSEA.getPageContext().resolveUrl('authen/signup');
	});
	$('.profile-area').on('click.jsea', 'a.signoff', function () {
		window.Page.triggerHandler("navigate.jsea", 
			{ action : function () {
							window.location = JSEA.getPageContext().resolveUrl('authen/signoff');
						}
			}
		);
	});
	$('.profile-area').on('click.jsea', 'a.changePassword', function () {
		window.Page.triggerHandler("navigate.jsea", 
			{ action : function () {
							window.location = JSEA.getPageContext().resolveUrl('acct/changePassword');
						}
			}
		);
	});
	$('.profile-area').on('click.jsea', 'a.changeMobile', function () {
		window.Page.triggerHandler("navigate.jsea", 
			{ action : function () {
							window.location = JSEA.getPageContext().resolveUrl('acct/changeMobile');
						}
			}
		);
	});
	$('.profile-area').on('click.jsea', 'a.changeEmail', function () {
		window.Page.triggerHandler("navigate.jsea", 
			{ action : function () {
							window.location = JSEA.getPageContext().resolveUrl('acct/changeEmail');
						}
			}
		);
	});
	
	/**
	 * paint an array of profile items into container
	 */
	function paintProfileItems($container, items) {
		for (var i = 0; i < items.length; i++) {
			var item = items[i];
			if (item.id != null) {
				$('<li class="' + item.icon + '"><a uid="' + item.id + '" class="profile-item" href="javascript:void(0);"><span class="icon" />' + JSEA.localizeMessage(item.label) + '</a></li>').appendTo($container);
			} else {
				$('<li class="' + item.icon + '">' + JSEA.localizeMessage(item.label) + '</li>').appendTo($container);
			}
		}
	}
	// init css class for .profiles
	var $profiles = $('ul.profiles', $('#userProfile'));
	$profiles.addClass('sm').addClass("sm-vertical").addClass('sm-simple');
	$profiles.smartmenus({ hideOnClick : true, showOnClick : true });
	$profiles.bind('beforefirstshow.smapi', function (e, ulEle) {
		var $container = $(ulEle);
		if ($container.find('>li').size() == 0 ) {
			$.ajax({
				url : JSEA.getPageContext().resolveUrl($container.attr('url')),
				dataType : "json",
				success : function (items) {
					paintProfileItems($container, items);
				}
			});
		}
		return true;
	});
	
	$('ul.locales', $('#userProfile')).on('click', 'a.profile-item', function () {
		var $container = $(this).closest('ul.profile-items');
		var localeCode = $(this).attr('uid');
		$.ajax({
			url : JSEA.getPageContext().resolveUrl($container.attr('modify') + localeCode),
			dataType : "json",
			success : function (result) {
				if (result == false) {
					Message.error("message.error.locale-not-supported", localeCode);
					return false;
				}
				return window.Page.triggerHandler("navigate.jsea", 
						{ action : function () {
								window.location.reload();
							}
						}
					);
			}
		});
	});
	
	$('ul.themes', $('#userProfile')).on('click', 'a.profile-item', function () {
		var $container = $(this).closest('ul.profile-items');
		var themeName = $(this).attr('uid');
		$.ajax({
			url : JSEA.getPageContext().resolveUrl($container.attr('modify') + themeName),
			dataType : "json",
			success : function (result) {
				if (result == false) {
					Message.error("message.error.theme-not-supported", themeName);
					themeName = null;
				}
				Page.changeTheme(themeName);
				Page.UserProfile.hide().emptyContent();
			}
		});
	});
});
</script>