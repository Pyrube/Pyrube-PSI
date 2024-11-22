<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.Apps" %>
<seaco:simpleForm basename="user" funcname="home" operation="view"
	infobar="global.infobar.user-home"
	cssClass="info-form">
	<!-- Form Container Begins -->
	<div class="form-container">
		<seael:elemset type="panel" header="global.header.user-profile">
			<% if (Apps.the.user().isGuest()) { %>
			<div class="funcbar">
				<div class="links"><seael:link 
					name="signon" url="authen/signon" mode="forward" /></div>
			</div>
			<% } %>
			<seael:textprop label="global.label.loginame" path="profile.login" />
			<seael:textprop label="global.label.username" path="profile.name" />
			<% if (!Apps.the.user().isGuest()) { %>
			<seael:textprop label="global.label.mobile" path="profile.mobile" />
			<seael:textprop label="global.label.email" path="profile.email" />
			<% } %>
			<seael:textprop label="global.label.locale" path="profile.locale" type="charone" i18nPrefix="locale" stylesheet="locale" />
			<seael:textprop label="global.label.timezone" path="profile.timezone.ID" />
			<% if (!Apps.the.user().isGuest()) { %>
			<seael:dateprop label="global.label.lastLogonTime" path="profile.lastLogonTime" format="timestamp" local="true" />
			<% } %>
		</seael:elemset>
	</div>
	<!-- Form Container Ends -->
	<% if (!Apps.the.user().isGuest()) { %>	
	<!-- Form Container Begins -->
	<div class="form-container">
		<seael:elemset type="panel" header="global.header.user-details">
			<div class="funcbar">
				<div class="links"><seael:link 
					name="edit" url="user/details/edit" mode="popup" /></div>
			</div>
			<seael:textprop label="global.label.nick" path="details.ext.nick" />
			<seael:textprop label="global.label.gender" path="details.ext.gender" type="charone" i18nPrefix="gender" />
			<seael:dateprop label="global.label.birthdate" path="details.ext.birthdate" format="date" />
			<seael:textprop label="global.label.country" path="details.ext.country" type="charone" i18nPrefix="country" stylesheet="country" />
			<seael:textprop label="global.label.locale" path="details.locale" type="charone" i18nPrefix="locale" stylesheet="locale" />
		</seael:elemset>
	</div>
	<!-- Form Container Ends -->
	<% } %>
</seaco:simpleForm>