<%@tag import="java.lang.reflect.InvocationTargetException"%>
<%@tag import="java.lang.reflect.Method"%>
<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag import="java.util.Iterator"%>
<%@tag import="java.util.Map.Entry"%>
<%@tag import="java.util.Map"%>
<%@tag import="java.util.Collection"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag dynamic-attributes="attributeMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="items" required="true" type="java.util.Collection" %>
<%@ attribute name="itemValue"  %>
<%@ attribute name="itemLabel"  %>
<%@ attribute name="value"  %>
<select name="${name}" 
	 <c:forEach items="${attributeMap}" var="attr">
	 	${attr.key}="${attr.value}"
	 </c:forEach> 
	>
<c:forEach items="${items}" var="code">
		<option value="${code[itemValue]}" ${code[itemValue] eq value ? 'selected="selected"':''} >${code[itemLabel]}</option>
	</c:forEach>
</select>

