<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:gridForm basename="txn" funcname="product" keyProp="prodNo" 
	mode="popup" script="true">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="create" access="hasAuthority('PSI:TXN:PROD_MOD')" /><seael:button 
			name="import" access="hasAuthority('PSI:TXN:PROD_MOD')" /></div>
		<div class="filter"><seael:button 
			name="search" /></div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid>
		<seaco:column name="prodNo" type="link" operation="view" stylesheet="key" />
		<seaco:column name="prodName" stylesheet="name" />
		<seaco:column name="spuName" />
		<seaco:column name="marketDate" type="date" format="date" />
		<seaco:column name="costPrice" type="money" />
		<seaco:column name="retailPrice" type="money" />
		<seaco:column type="operations" header="global.header.operations" sortable="false">
			<wone:operation name="update" access="hasAuthority('PSI:TXN:PROD_MOD')" />
			<wone:operation name="delete" access="hasAuthority('PSI:TXN:PROD_DEL')" />
		</seaco:column>
	</seaco:grid>
</seaco:gridForm>
