<%@ taglib uri="jsea-element" prefix="seael" %>
<!-- Form Container Begins -->
<div class="form-container">
	<!-- Search Area Begins -->
	<seael:elemset type="search" stylesheet="fields">
		<seael:textfield label="product.label.prodNo" name="prodNo" cssClass="code uppercase" />
		<seael:textfield label="product.label.prodName" name="prodName" cssClass="name" />
		<seael:falefield label="product.label.spuName" name="spuName" cssClass="name" />
		<seael:multifields label="product.label.marketDate"><seael:datefield 
			name="marketDateFrom" format="date" />-<seael:datefield 
			name="marketDateTo" format="date" /></seael:multifields>
		<seael:multifields label="product.label.costPrice"><seael:amountfield 
			name="costPriceFrom" format="money" decimal="15, 3"  />-<seael:amountfield 
			name="costPriceTo" format="money" decimal="15, 3" /></seael:multifields>
		<seael:multifields label="product.label.retailPrice"><seael:amountfield 
			name="retailPriceFrom" format="money" decimal="15, 3"  />-<seael:amountfield 
			name="retailPriceTo" format="money" decimal="15, 3" /></seael:multifields>
	</seael:elemset>
	<!-- Search Area Ends -->
</div>
<!-- Form Container Ends -->