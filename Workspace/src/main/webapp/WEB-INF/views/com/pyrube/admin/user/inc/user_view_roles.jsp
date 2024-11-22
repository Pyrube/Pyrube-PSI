<%@ taglib uri="jsea-component" prefix="seaco" %>
<!-- Grid Container Begins -->
<seaco:grid id="roles" rs="${user.roles}" rsProp="roles" postProps="['updateTime|longTimestampZ']"
	sortable="false" pageable="false" cssClass="list-container">
	<seaco:column name="roleCode" type="link" operation="rights" 
		url="admin/right/lookup" 
		stylesheet="key" />
	<seaco:column name="roleName" />
	<seaco:column header="global.header.data-status" name="dataStatus"
		type="icon" stylesheet="status" i18nPrefix="status" />
</seaco:grid>
<!-- Grid Container Ends -->