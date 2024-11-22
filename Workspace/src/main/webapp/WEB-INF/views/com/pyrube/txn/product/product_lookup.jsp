<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:lookupForm basename="txn" funcname="product" returnProps="['prodNo', 'prodName', 'costPrice']">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="select" dors="true" /><seael:button 
			name="close" /></div>
		<div class="filter"><seael:button 
			name="search" /></div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid>
		<seaco:column name="prodNo" stylesheet="key" />
		<seaco:column name="prodName" stylesheet="name" />
		<seaco:column name="spuName" />
		<seaco:column name="marketDate" type="date" format="date" />
		<seaco:column name="costPrice" type="money" />
		<seaco:column name="retailPrice" type="money" />
	</seaco:grid>
</seaco:lookupForm>
