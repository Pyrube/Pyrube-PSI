<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.Apps" %>
<c:set var="localeCodes" value="<%=Apps.some.locales.supported().to.codes() %>" />
<seaco:popupForm basename="user" funcname="details" operation="edit" keyProp="loginame"
	infobar="global.infobar.user-edit" 
	validatable="true" modifiable="true">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons oneact"><seael:button 
			name="save" success="message.success.user-details-modification" /><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Form Container Begins -->
	<div class="form-container">
		<seael:elemset type="form">
			<seael:textfield label="global.label.nick" path="nick" maxLen="32" cssClass="name" />
			<seael:falefield label="global.label.gender" path="gender" items="[0, 1]" i18nPrefix="gender" stylesheet="gender" />
			<seael:datefield label="global.label.birthdate" path="birthdate" format="date" />
			<seael:falefield label="global.label.country" path="country" items="JSEA.Constants.OPTIONS.COUNTRIES" i18nPrefix="country" stylesheet="country" />
			<seael:falefield label="global.label.locale" required="true" path="locale" items="${localeCodes }" i18nPrefix="locale" stylesheet="locale" />
		</seael:elemset>
	</div>
	<!-- Form Container Ends -->
	<!-- Hidden Fields -->
	<seael:hidfield path="loginame" />
</seaco:popupForm>