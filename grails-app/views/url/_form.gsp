<%@ page import="urlshortener.Url" %>



<div class="fieldcontain ${hasErrors(bean: urlInstance, field: 'url', 'error')} required">
	<label for="url">
		<g:message code="url.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="url" name="url" required="" value="${urlInstance?.url}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: urlInstance, field: 'shortUrlName', 'error')} ">
	<label for="shortUrlName">
		<g:message code="url.shortUrlName.label" default="Short Url Name" />
		
	</label>
	<g:textField name="shortUrlName" maxlength="9" value="${urlInstance?.shortUrlName}"/>

</div>

