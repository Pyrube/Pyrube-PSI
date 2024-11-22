<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:simpleForm funcname="httpm" operation="detect" nested="true" cssClass="message-form">
	<!-- Message Container Begins -->
	<div class='message-container'>
		<!-- Message Area Begins -->
		<seael:elemset type="message" header="true" stylesheet="n/a">
			<li class="message"><span class="error"></span><label><wone:message code="message.error.http-500" /></label></li>
		</seael:elemset>
		<!-- Message Area Ends -->
	</div>
	<!-- Message Container Ends -->
</seaco:simpleForm>