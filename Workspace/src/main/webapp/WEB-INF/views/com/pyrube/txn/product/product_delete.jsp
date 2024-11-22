<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:popupForm basename="txn" funcname="product" operation="delete" actions="['confirm']"
	keyProp="prodNo" refProp="prodName">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons oneact"><seael:button
			name="confirm" confirm="delete" /><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Form Area Begins -->
		<seael:elemset type="form" header="true">
			<seael:textprop path="prodNo" />
			<seael:textprop path="prodName" />
			<seael:textprop path="spuName" />
			<seael:textprop path="prodDesc" />
			<seael:dateprop path="marketDate" format="date" />
			<seael:amountprop path="costPrice" format="money" />
			<seael:amountprop path="retailPrice" format="money" />
			<seael:amountprop path="promoPrice" format="money" />
		</seael:elemset>
		<!-- Form Area Ends -->
		<seael:hidfield path="prodNo" />
		<seael:hidfield path="prodName" />
	</div>
	<!-- Form Container Ends -->
</seaco:popupForm>
