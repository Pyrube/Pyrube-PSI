<%@ taglib uri="wea-one" prefix="wone" %>
<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:gridForm basename="admin" funcname="user" keyProp="userId" script="true">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons"><seael:button 
			name="create" access="hasAuthority('SYS:ADM:USR_MOD')" /></div>
		<div class="filter"><seael:button 
			name="search" /></div>
	</div>
	<!-- Funcbar Ends -->
	<seaco:grid>
		<seaco:column name="loginName" type="link" operation="view" stylesheet="key" />
		<seaco:column name="userName" />
		<seaco:column name="ext.gender" type="charone" i18nPrefix="user.label.ext.gender" />
		<seaco:column name="ext.lastLoginTime" type="date" format="timestamp" local="true" />
		<seaco:column name="userStat" type="charone" i18nPrefix="user.label.userStat" />
		<seaco:column name="dataStatus" type="icon" header="global.header.data-status" 
			stylesheet="setup status" i18nPrefix="setup.status" />
		<seaco:column type="operations" header="global.header.operations" sortable="false">
			<wone:operation name="update" access="hasAuthority('SYS:ADM:USR_MOD')" 
				inactive="Page.DataRule('notVerified'), Page.DataRule('isInternal')" />
			<wone:operation name="delete" access="hasAuthority('SYS:ADM:USR_DEL')" 
				inactive="Page.DataRule('notVerified'), Page.DataRule('isInternal')" />
			<wone:operation name="continue" access="hasAuthority('SYS:ADM:USR_MOD')" 
				inactive="Page.DataRule('isVerified'), Page.DataRule('isSubmitted')" />
			<wone:operation name="verify" access="hasAuthority('SYS:ADM:USR_VRF')" 
				inactive="Page.DataRule('isVerified'), Page.DataRule('notSubmitted')" />
			<wone:operation name="comments" mode="popover(comments)" 
				invisible="Page.DataRule('noComments')" />
		</seaco:column>
	</seaco:grid>
</seaco:gridForm>
