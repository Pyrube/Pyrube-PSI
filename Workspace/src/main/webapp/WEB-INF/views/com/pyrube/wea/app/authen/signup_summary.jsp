<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="signup" 
	modelAttribute="onboarding" cssClass="authen-form signup">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Panel Area Begins -->
		<seael:elemset type="panel" header="authen.header.signup-general">
			<seael:textprop path="authen.username" label="authen.label.username" />
		</seael:elemset>
		<!-- Panel Area Ends -->
		<!-- Panel Area Begins -->
		<seael:elemset type="panel" header="authen.header.signup-details">
			<seael:textprop path="details.nick" label="authen.label.nick" />
			<seael:textprop path="details.firstname" label="authen.label.firstname" />
			<seael:textprop path="details.lastname" label="authen.label.lastname" />
			<seael:textprop path="details.gender" label="authen.label.gender" type="charone" i18nPrefix="gender" />
			<seael:dateprop path="details.birthdate" label="authen.label.birthdate" format="date" />
			<seael:textprop path="details.country" label="authen.label.country" type="charone" i18nPrefix="country" stylesheet="country" />
		</seael:elemset>
		<!-- Panel Area Ends -->
	</div>
	<!-- Form Container Ends -->
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button name="previous" /></div>
		<div class="buttons meta"><seael:button
			name="ok" url="authen/signup/submit" /></div>
	</div>
	<!-- Funcbar Ends -->
</seaco:simpleForm>