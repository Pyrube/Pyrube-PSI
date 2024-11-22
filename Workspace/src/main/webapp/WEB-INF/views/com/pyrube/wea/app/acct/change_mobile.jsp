<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="changeMobile" validatable="true"
	servletRelativeAction="/acct/changeMobile/check" cssClass="authen-form">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" stylesheet="fields">
			<seael:passfield path="password" required="true" maxLen="18" />
			<seael:textfield path="mobile" required="true" maxLen="16" />
			<seael:captcha name="captcha" required="true" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="buttons"><seael:button type="submit"
					name="changeMobile" toggleable="true" disabled="true" /></div>
			</div>
			<!-- Funcbar Ends -->
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>