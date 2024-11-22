<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:popupForm basename="txn" funcname="product" operation="update" keyProp="prodNo">
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
			<seael:textprop path="prodNo" />
			<seael:textfield path="prodName" required="true" maxLen="64" cssClass="name" />
			<seael:lookup path="spuName" required="true" url="dict/spu/lookup" cascades="['spuId','spuName']" />
			<seael:textfield path="prodDesc" maxLen="120" />
			<seael:datefield path="marketDate" format="date" />
			<seael:amountfield path="costPrice" required="true" decimal="15, 3" format="money" />
			<seael:amountfield path="retailPrice" decimal="15, 3" format="money" />
			<seael:amountfield path="promoPrice" decimal="15, 3" format="money" />
		</seael:elemset>
		<!-- Form Area Ends -->
		<seael:hidfield path="prodNo" />
		<seael:hidfield path="spuId" />
	</div>
	<!-- Form Container Ends -->
</seaco:popupForm>
