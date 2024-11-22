<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:lookupForm basename="admin" funcname="role" returnProps="['roleId', 'roleCode', 'roleName']">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="select" dors="true" /><seael:button 
			name="close" /></div>
		<div class="filter"><seael:button 
			name="search" /></div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid multiple="true">
		<seaco:column name="roleCode" type="link" operation="rights" 
			url="admin/right/lookup" mode="lookup" 
			stylesheet="key" />
		<seaco:column name="roleName" />
	</seaco:grid>
</seaco:lookupForm>
