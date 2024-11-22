<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="signup" validatable="true" 
	modelAttribute="authen" cssClass="authen-form signup">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form">
			<seael:textfield path="username" type="nulm" required="true" maxLen="32" 
				remote="['unique', 'authen/signup/unique/username']" help="true" autocomplete="off" />
			<seael:passfield path="password1" required="true" maxLen="18" />
			<seael:passfield path="password2" required="true" maxLen="18" equalTo="password1" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="links"><seael:link 
					name="signon">authen.link.text.signup-signon</seael:link></div>
				<div class="buttons meta"><seael:button
					name="next" url="authen/signup/general/save"
					toggleable="true" disabled="true" /></div>
			</div>
			<!-- Funcbar Ends -->
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>