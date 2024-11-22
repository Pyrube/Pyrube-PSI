<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.Apps" %>
<c:set var="fiscalYears" value='<%=Apps.some.objects.cached("fiscalYears") %>' />
<c:set var="quarters" value='<%=Apps.some.objects.cached("quarters") %>' />
<c:set var="channels" value='<%=Apps.some.objects.cached("listChannels") %>' />
<seaco:popupForm basename="txn" funcname="plan" operation="create" keyProp="salesPlanId">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons oneact"><seael:button 
			name="save" /><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" header="true">
			<seael:lookup path="prodNo" required="true" url="txn/product/lookup" cascades="['prodNo','prod.prodName=prodName','prod.costPrice=costPrice']" />
			<seael:selefield path="fiscalYear" required="true" items="${fiscalYears }" />
			<seael:radios path="fiscalQuar" required="true" items="${quarters }" i18nPrefix="quarter" cssClass="chips" />
			<seael:falefield path="channelId" required="true" items="${channels }" itemValue="channelId" itemLabel="channelName" stylesheet="channel" />
			<seael:textfield path="siCa" type="number" maxLen="7" />
			<seael:amountfield path="siRev" decimal="15, 3" format="money" />
			<seael:amountfield path="siGp" decimal="15, 3" format="money" />
			<seael:textfield path="soCa" type="number" maxLen="7" />
			<seael:amountfield path="soRev" decimal="15, 3" format="money" />
			<seael:amountfield path="soGp" decimal="15, 3" format="money" />
		</seael:elemset>
		<!-- Form Area Ends -->
		<seael:hidfield path="salesPlanId" />
		<seael:hidfield path="prod.prodName" />
		<seael:hidfield path="prod.costPrice" />
	</div>
	<!-- Form Container Ends -->
	<!-- List Container Begins -->
	<seaco:grid rs="${plan.details}" rsProp="details"
		sortable="false" pageable="false" cssClass="list-container">
		<seaco:column name="planMonth" type="charone(month)" stylesheet="key" />
		<seaco:column name="siCa" />
		<seaco:column name="siRev" type="money" />
		<seaco:column name="siGp" type="money" />
		<seaco:column name="soCa" />
		<seaco:column name="soRev" type="money" />
		<seaco:column name="soGp" type="money" />
		<seaco:column header="global.header.data-status" name="dataStatus"
			type="icon" stylesheet="status" i18nPrefix="status" />
		<seaco:column header="global.header.operations" type="operations">
			<wone:operation name="modify" url="txn/plan/detail/modify" />
			<wone:operation name="undo" hidden="Page.DataRule('cannotUndo')" />
		</seaco:column>
	</seaco:grid>
	<!-- List Container Ends -->
</seaco:popupForm>
