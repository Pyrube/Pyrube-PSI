<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jstl-core" prefix="c" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:popupForm basename="txn" funcname="detail" operation="modify" keyProp="planDetailsId"
	infobar="?{planMonth}">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons oneact"><seael:button 
			name="okay" url="txn/plan/detail/modify/okay" /><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" header="true">
			<seael:textfield path="siCa" type="number" maxLen="7" />
			<seael:amountfield path="siRev" decimal="15, 3" format="money" />
			<seael:amountfield path="siGp" decimal="15, 3" format="money" />
			<seael:textfield path="soCa" type="number" maxLen="7" />
			<seael:amountfield path="soRev" decimal="15, 3" format="money" />
			<seael:amountfield path="soGp" decimal="15, 3" format="money" />
		</seael:elemset>
		<!-- Form Area Ends -->
		<seael:hidfield path="planDetailsId" />
		<seael:hidfield path="salesPlanId" />
		<seael:hidfield path="planMonth" />
	</div>
	<!-- Form Container Ends -->
</seaco:popupForm>
