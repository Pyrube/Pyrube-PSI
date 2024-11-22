<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:detailForm basename="admin" funcname="user" operation="update" keyProp="userId" refProp="loginName"
	validatable="true" modifiable="true">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons oneact"><seael:button 
			name="submit" cssClass="action" /><seael:button 
			name="back" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Tabs Container Begins -->
	<seaco:tabs id="userTabs">
		<seaco:tab id="generalTab" header="user.header.general">
			<jsp:include page="inc/user_update_general.jsp"></jsp:include>
		</seaco:tab>
		<seaco:tab id="roleTab" header="user.header.roles">
			<jsp:include page="inc/user_update_roles.jsp"></jsp:include>
		</seaco:tab>
	</seaco:tabs>
	<!-- Tabs Container Ends -->
	<!-- Hidden Fields -->
	<seael:hidfield path="userId" />
	<seael:hidfield path="loginName" />
	<seael:hidfield path="dataStatus" />
</seaco:detailForm>
