<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="begin" required="true"  type="java.lang.Integer" %>
<%@ attribute name="end"   required="true"  type="java.lang.Integer" %>
<%@ attribute name="var"   required="true"  rtexprvalue="false"  %>
<%@ variable alias="sum" name-from-attribute="var" variable-class="java.lang.Integer" scope="AT_BEGIN" %>
<%	
	int s = 0;
	for(int i = begin ; i <= end; i++){
		s += i;
	}
	jspContext.setAttribute("sum", s);
%>
<jsp:doBody />