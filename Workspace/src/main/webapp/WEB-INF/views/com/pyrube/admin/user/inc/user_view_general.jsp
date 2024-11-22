<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<!-- Form Container Begins -->
<div class="form-container">
	<!-- Panel Area Begins -->
	<seael:elemset type="panel">
			<seael:textprop path="loginName" />
			<seael:textprop path="ext.nickName" />
			<seael:textprop path="firstName" />
			<seael:textprop path="lastName" />
			<seael:textprop path="ext.gender" type="charone" i18nPrefix="user.label.ext.gender" />
			<seael:textprop path="localeCode" type="charone" i18nPrefix="user.label.localeCode" />
			<seael:textprop path="ext.countryCode" type="charone" i18nPrefix="country" />
			<seael:textprop path="email" />
			<seael:textprop path="mobile" />
			<seael:textprop path="idNum" />
			<seael:dateprop path="ext.birthdate" format="date" />
	</seael:elemset>
	<!-- Panel Area Ends -->
</div>
<!-- Form Container Ends -->