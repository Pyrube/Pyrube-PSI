<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:lookupForm basename="dict" funcname="spu" returnProps="['spuId', 'spuName']">
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
		<seaco:column name="spuName" />
	</seaco:grid>
</seaco:lookupForm>
