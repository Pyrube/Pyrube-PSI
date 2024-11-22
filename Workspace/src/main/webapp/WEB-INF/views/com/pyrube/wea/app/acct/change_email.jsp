<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="changeEmail" validatable="true"
	servletRelativeAction="/acct/changeEmail/check" cssClass="authen-form">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" stylesheet="fields">
			<seael:passfield path="password" required="true" maxLen="18" />
			<seael:emailfield path="email" required="true" maxLen="40" />
			<seael:captcha name="captcha" required="true" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="buttons"><seael:button type="submit"
					name="changeEmail" toggleable="true" disabled="true" /></div>
			</div>
			<!-- Funcbar Ends -->
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>