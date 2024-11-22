<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:gridForm basename="txn" funcname="plan" keyProp="salesPlanId" 
	mode="popup" operations="['duplicate']">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="create" access="hasAuthority('PSI:TXN:SOP_MOD')" /><seael:button 
			name="duplicate" access="hasAuthority('PSI:TXN:SOP_MOD')" /></div>
		<div class="filter"><seael:button 
			name="search" /></div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid>
		<seaco:column name="prodNo" type="link" operation="refer" url="txn/product/referred" stylesheet="key" />
		<seaco:column name="prod.prodName" stylesheet="name" />
		<seaco:column name="fiscalYear" />
		<seaco:column name="fiscalQuar" type="charone(quarter)" />
		<seaco:column name="channelId" type="icon({chan.channelName})" stylesheet="channel" />
		<seaco:column name="siCa" />
		<seaco:column name="siRev" type="money" />
		<seaco:column name="siGp" type="money" />
		<seaco:column name="soCa" />
		<seaco:column name="soRev" type="money" />
		<seaco:column name="soGp" type="money" />
		<seaco:column type="operations" header="global.header.operations" sortable="false">
			<wone:operation name="details" mode="popdown" />
			<wone:operation name="update" access="hasAuthority('PSI:TXN:SOP_MOD')" />
		</seaco:column>
	</seaco:grid>
</seaco:gridForm>
