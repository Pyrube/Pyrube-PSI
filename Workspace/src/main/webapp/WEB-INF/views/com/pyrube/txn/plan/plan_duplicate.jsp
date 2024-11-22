<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.Apps" %>
<c:set var="fiscalYears" value='<%=Apps.some.objects.cached("fiscalYears") %>' />
<c:set var="quarters" value='<%=Apps.some.objects.cached("quarters") %>' />
<c:set var="channels" value='<%=Apps.some.objects.cached("listChannels") %>' />
<seaco:popupForm basename="txn" funcname="plan" operation="duplicate" keyProp="salesPlanId">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons oneact"><seael:button 
			name="okay" /><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" header="plan.header.plan-source">
			<seael:selefield path="fiscalYear" required="true" items="${fiscalYears }" stylesheet="channel" />
			<seael:radios path="fiscalQuar" required="true" items="${quarters }" i18nPrefix="quarter" cssClass="chips" />
			<seael:checkboxes path="channelIds" required="true" items="${channels }" itemValue="channelId" itemLabel="channelName" stylesheet="channel" cssClass="chips" />
			<seael:lookup path="prodNo" url="txn/product/lookup" cascades="['prodNo','prod.prodName=prodName']" help="true" />
		</seael:elemset>
		<!-- Form Area Ends -->
		<seael:hidfield path="salesPlanId" />
		<seael:hidfield path="prod.prodName" />
	</div>
	<!-- Form Container Ends -->
</seaco:popupForm>
