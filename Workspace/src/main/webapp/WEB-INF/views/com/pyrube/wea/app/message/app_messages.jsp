<%@ page import="java.io.StringWriter, 
				com.fasterxml.jackson.databind.ObjectMapper, 
				com.pyrube.one.app.AppException,
				com.pyrube.one.app.AppMessage,
				com.pyrube.one.app.Apps,
				com.pyrube.wea.WeaConstants,
				com.pyrube.wea.ui.resolvers.core.WeaMappingExceptionResolver" %>
<%
	Exception ex = (Exception) request.getAttribute(WeaMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);
	String messages = null;
	if (ex != null) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		AppMessage[] ams = null;
		if (ex instanceof AppException) {
			AppException ae = (AppException) ex;
			ams = Apps.some.messages(Apps.a.message.with.error(ae.getCode()).params(ae.getParams()));
		} else {
			// other unknown exception
			ams = Apps.some.messages(Apps.a.message.with.error(ex.getMessage()));
		}
		mapper.writeValue(sw, ams);
		messages = sw.toString();
	}
%>
<script>
$(function() {
	// the section is error for ajax/non-ajax request. 
	var messages = <%= (null != messages ? messages : "[]")%>;
	Messages.clear();
	for (var i = 0; i < messages.length; i++) {
		var message = messages[i];
		Messages.add(message.level, message.code, message.params);
	}
});
</script>