<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="forceChangePassword" validatable="true"
	servletRelativeAction="/authen/forceChangePassword/check" cssClass="authen-form">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" stylesheet="fields">
			<seael:textfield path="username" required="true" readonly="true" />
			<seael:passfield name="password0" required="true" />
			<seael:passfield name="password1" required="true" />
			<seael:passfield name="password2" required="true" equalTo="password1" />
			<seael:captcha name="captcha" required="true" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="buttons"><seael:button type="submit"
					name="changePassword" toggleable="true" disabled="true" /></div>
			</div>
			<!-- Funcbar Ends -->
			<div class="linkbar">
				<div class="links"><seael:link 
					name="signon" /><seael:link 
					name="forgetPassword" /></div>
			</div>
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>