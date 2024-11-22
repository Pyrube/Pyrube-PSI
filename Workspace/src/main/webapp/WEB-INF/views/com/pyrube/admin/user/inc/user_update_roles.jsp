<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<!-- Grid Container Begins -->
<div class="funcbar">
	<div class="buttons">
		<seael:button name="add" event="Page.Event('addUserRoles')" />
	</div>
</div>
<seaco:grid id="roles" rs="${user.roles}" rsProp="roles"
	sortable="false" pageable="false" cssClass="list-container">
	<seaco:column name="roleCode" type="link" operation="rights" 
		url="admin/right/lookup" stylesheet="key" />
	<seaco:column name="roleName" />
	<seaco:column header="global.header.data-status" name="dataStatus"
		type="icon" stylesheet="status" i18nPrefix="status" />
	<seaco:column header="global.header.operations" type="operations">
		<wone:operation name="remove" confirm="user.confirm.remove-role" hidden="Page.DataRule('isRemoved'), Page.DataRule('isAdded')" />
		<wone:operation name="undo" hidden="Page.DataRule('cannotUndo')" />
	</seaco:column>
</seaco:grid>
<!-- Grid Container Ends -->