<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="node" type="com.luanvm.coffee.web.form.DynaTree" required="true"%>
<%@ tag import="java.util.LinkedList" import="java.util.LinkedList" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	
<c:choose>
	<c:when test="${node.getChildren().size() >0}">
		<li><a href="#">${node.getTitle()}</a></li>
			<ul>
				<c:forEach var="child" items="${node.getChildren() }" >
					<template:navTree node="${child}"/>
				</c:forEach>
			</ul>
	</c:when>
	<c:otherwise>
		<li><a href="#">${node.getTitle()}</a></li>
	</c:otherwise>
</c:choose>
</html>