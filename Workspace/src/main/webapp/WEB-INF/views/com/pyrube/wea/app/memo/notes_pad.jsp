<%@ taglib uri="jsea-component" prefix="seaco" %>
<%@ taglib uri="jsea-element" prefix="seael" %>
<seaco:lookupForm id="frmNotes" basename="jsea" funcname="note" nested="true" metaless="true">
	<seaco:grid id="notesContainer" url="jsea/note/list" sortable="false" pageable="false">
		<seaco:column name="eventCode" type="charone(note.event?{noteFrom})" stylesheet="key" />
		<seaco:column name="content" type="desc" />
		<seaco:column name="noteTime" type="date" format="timestamp" local="true" />
	</seaco:grid>
</seaco:lookupForm>
<seaco:popupForm id="frmNote" infobar="false" 
	basename="jsea" funcname="note" operation="leave" 
	mode="save" metaless="true">
	<!-- Form Container Begins -->
	<div class="form-container">
		<!-- Panel Area Begins -->
		<seael:elemset type="panel" stylesheet="fields">
			<seael:textarea label="global.label.note-content" id="noteContent" name="content"
				required="true" maxLen="4000"
				placeholder="global.placeholder.note-content" />
		</seael:elemset>
	</div>
	<!-- Form Container Ends -->
	<!-- Funcbar Begins -->
	<div class="funcbar">
		<div class="buttons">
			<seael:button name="save" callback="Noter.append" 
				toggleable="true" disabled="true" /></div>
	</div>
	<!-- Funcbar Ends -->
	<!-- Hidden Fields -->
	<seael:hidfield path="dataType" />
	<seael:hidfield path="dataId" />
	<seael:hidfield path="dataStatus" />
</seaco:popupForm>