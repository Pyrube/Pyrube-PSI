<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:wizardForm funcname="authen" operation="signup" keyProp="id" statProp="status"
	operations="[{name:'signon',mode:'forward'}]" modelAttribute="onboarding">
	<!-- Wizard Container Begins -->
	<seaco:wizard id="signupWizard">
		<seaco:step id="generalSection" header="authen.header.signup-general" url="authen/signup/general" />
		<seaco:step id="detailsSection" header="authen.header.signup-details" url="authen/signup/details" />
		<seaco:step id="summarySection" header="authen.header.signup-summary" url="authen/signup/summary" />
	</seaco:wizard>
	<!-- Wizard Container Ends -->
	<!-- Hidden Fields -->
	<seael:hidfield path="id" />
	<seael:hidfield path="status" />
</seaco:wizardForm>