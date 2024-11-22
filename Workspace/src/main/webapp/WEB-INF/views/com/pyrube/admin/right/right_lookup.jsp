<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:lookupForm infobar="?{roleCode}" basename="admin" funcname="right" returnProps="['rightId', 'rightCode', 'rightName']">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="close" /></div>
		<div class="filter"><seael:button 
			name="search" />
		</div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid>
		<seaco:column name="rightCode" type="text" stylesheet="key" />
		<seaco:column name="rightName" />
	</seaco:grid>
</seaco:lookupForm>
