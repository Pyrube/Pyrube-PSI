<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.Apps" %>
<c:set var="localeCodes" value="<%=Apps.some.locales.supported().to.codes() %>" />
<!-- Form Container Begins -->
<div class="form-container">
	<!-- Panel Area Begins -->
	<seael:elemset type="panel">
			<seael:textfield path="loginName" type="nulm" required="true" maxLen="32" cssClass="code" remote="unique" />
			<seael:textfield path="ext.nickName" maxLen="32" cssClass="name" />
			<seael:textfield path="firstName" maxLen="32" cssClass="name" />
			<seael:textfield path="lastName" maxLen="32" cssClass="name" />
			<seael:falefield path="ext.gender" required="true" items="[0, 1]" i18nPrefix="user.label.ext.gender" stylesheet="gender" />
			<seael:falefield path="localeCode" required="true" items="${localeCodes }" i18nPrefix="user.label.localeCode" stylesheet="locale" />
			<seael:falefield path="ext.countryCode" required="true" items="['CN', 'HK', 'KR', 'JP', 'SG', 'IN', 'ID', 'US', 'CA', 'BR', 'AR', 'RU', 'GB', 'FR', 'DE', 'IT', 'ES', 'AU', 'EG']" i18nPrefix="country" stylesheet="country" />
			<seael:emailfield path="email" required="true" maxLen="40" remote="unique" />
			<seael:textfield path="mobile" maxLen="16" remote="unique" />
			<seael:textfield path="idNum" maxLen="24" />
			<seael:datefield path="ext.birthdate" format="date" />
	</seael:elemset>
	<!-- Panel Area Ends -->
	<!-- Hidden Fields -->
	<seael:hidfield path="ext.registerSrc" />
</div>
<!-- Form Container Ends -->