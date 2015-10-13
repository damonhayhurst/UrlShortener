
<%@ page import="urlshortener.Url" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'url.label', default: 'Url')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-url" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-url" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>


			<ol class="property-list url">
			
				<g:if test="${urlInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="url.url.label" default="Url" /></span>
					
						<a href="${urlInstance.url}"><span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${urlInstance}" field="url"/></span></a>
					
				</li>
				</g:if>
			
				<g:if test="${urlInstance?.shortUrlName}">
				<li class="fieldcontain">
					<span id="shortUrlName-label" class="property-label"><g:message code="url.shortUrlName.label" default="Short Url Name" /></span>
					
						<span class="property-value" aria-labelledby="shortUrlName-label"><g:fieldValue bean="${urlInstance}" field="shortUrlName"/></span>
					
				</li>
				</g:if>

				<g:if test="${urlInstance?.clicksCount}">
					<li class="fieldcontain">
						<span id="clicksCount-label" class="property-label"><g:message code="url.clicksCount.label" default="Click Count" /></span>

						<span class="property-value" aria-labelledby="clicksCount-label"><g:fieldValue bean="${urlInstance}" field="clicksCount"/></span>

					</li>
				</g:if>
			
				<g:if test="${urlInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="url.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${urlInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:urlInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${urlInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
