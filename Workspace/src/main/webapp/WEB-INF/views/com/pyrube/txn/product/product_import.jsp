<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:popupForm basename="txn" funcname="product" operation="import"
	metaless="true" enctype="application/json" event="Page.Event('bindClipboard')">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons triact"><seael:button 
			name="save" toggleable="true" disabled="true" /><seael:button 
			name="clear" confirm="product.confirm.clear-all"
			toggleable="true" disabled="true" cssClass="all"
			event="Page.Event('clearAllProducts')" /><seael:button 
			name="remove" confirm="product.confirm.remove-selected"
			title="button.alt.removeSelected" dors="true"
			toggleable="true" disabled="true" cssClass="selected"
			event="Page.Event('removeSelectedProducts')">button.text.removeSelected</seael:button><seael:button 
			name="close" /></div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid id="products" postProps="['marketDate|date']" required="true" uniqueIndex="['prodNo']"
		multiple="true" sortable="false" pageable="false" cssClass="list-container">
		<seaco:column name="prodNo" stylesheet="key" />
		<seaco:column name="prodName" stylesheet="name" />
		<seaco:column name="spuName" />
		<seaco:column name="marketDate" type="date" format="date" />
		<seaco:column name="costPrice" type="money" />
		<seaco:column name="retailPrice" type="money" />
		<seaco:column type="operations" header="global.header.operations" sortable="false">
			<wone:operation name="modify" url="txn/product/modify" />
			<wone:operation name="remove" permanent="true" confirm="product.confirm.remove-product" />
		</seaco:column>
	</seaco:grid>
</seaco:popupForm>
