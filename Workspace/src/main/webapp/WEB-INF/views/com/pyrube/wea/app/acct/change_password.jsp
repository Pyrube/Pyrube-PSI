<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="changePassword" validatable="true"
	servletRelativeAction="/acct/changePassword/check" cssClass="authen-form">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" stylesheet="fields">
			<seael:passfield path="password0" required="true" maxLen="18" />
			<seael:passfield path="password1" required="true" maxLen="18" />
			<seael:passfield path="password2" required="true" maxLen="18" equalTo="password1" />
			<seael:captcha name="captcha" required="true" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="buttons"><seael:button type="submit"
					name="changePassword" toggleable="true" disabled="true" /></div>
			</div>
			<!-- Funcbar Ends -->
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>