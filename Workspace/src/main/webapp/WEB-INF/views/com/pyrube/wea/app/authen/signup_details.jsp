<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="signup" validatable="true" 
	modelAttribute="details" cssClass="authen-form signup">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form">
			<seael:textfield path="firstname" maxLen="32" />
			<seael:textfield path="lastname" required="true" maxLen="32" />
			<seael:textfield path="nick" maxLen="32" />
			<seael:falefield path="gender" items="[0, 1]" i18nPrefix="gender" stylesheet="gender" />
			<seael:datefield path="birthdate" format="date" />
			<seael:falefield path="country" items="JSEA.Constants.OPTIONS.COUNTRIES" i18nPrefix="country" stylesheet="country" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="buttons"><seael:button name="previous" /></div>
				<div class="buttons meta"><seael:button
					name="next" url="authen/signup/details/save"
					toggleable="true" disabled="true" /></div>
			</div>
			<!-- Funcbar Ends -->
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>