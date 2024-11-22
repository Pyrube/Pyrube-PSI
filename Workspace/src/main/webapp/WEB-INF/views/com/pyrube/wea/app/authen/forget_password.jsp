<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="authen" operation="forgetPassword" validatable="true"
	servletRelativeAction="/authen/findPassword" cssClass="authen-form">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" stylesheet="fields">
			<seael:textfield path="username" required="true" />
			<!-- Funcbar Begins -->
			<div class="funcbar">
				<div class="buttons"><seael:button type="submit"
					name="findPassword" toggleable="true" disabled="true" /></div>
			</div>
			<div class="funcbar">
				<div class="links"><seael:link 
					name="signon" /></div>
			</div>
			<!-- Funcbar Ends -->
		</seael:elemset>
		<!-- Form Area Ends -->
	</div>
	<!-- Form Container Ends -->
</seaco:simpleForm>