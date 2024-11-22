<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<%@ page import="com.pyrube.one.app.Apps" %>
<seaco:popupForm basename="txn" funcname="plan" operation="details" keyProp="salesPlanId"
	infobar="false">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
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
	</seaco:grid>
	<!-- List Container Ends -->
	<seael:hidfield path="salesPlanId" />
</seaco:popupForm>
