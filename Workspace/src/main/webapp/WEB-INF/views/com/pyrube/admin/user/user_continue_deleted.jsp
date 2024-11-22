<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:detailForm basename="admin" funcname="user" operation="delete" keyProp="userId" refProp="loginName">
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons twoact"><seael:button 
			name="submit" confirm="delete" /><seael:button 
			name="cancel" confirm="cancelDeleted" /><seael:button 
			name="back" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Tabs Container Begins -->
	<seaco:tabs id="userTabs">
		<seaco:tab id="generalTab" header="user.header.general">
			<jsp:include page="inc/user_view_general.jsp"></jsp:include>
		</seaco:tab>
		<seaco:tab id="roleTab" header="user.header.roles">
			<jsp:include page="inc/user_view_roles.jsp"></jsp:include>
		</seaco:tab>
	</seaco:tabs>
	<!-- Tabs Container Ends -->
	<!-- Hidden Fields -->
	<seael:hidfield path="userId" />
	<seael:hidfield path="loginName" />
	<seael:hidfield path="dataStatus" />
</seaco:detailForm>