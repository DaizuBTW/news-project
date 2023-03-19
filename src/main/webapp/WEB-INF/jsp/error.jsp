<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error</title>
    </head>
    <body>
        <div class="body-title">
            <a href="controller?command=go_to_base_page"><c:out value="${button_back_to_news}"/> >> </a> Error page
        </div>

        <div class="add-table-margin">
            <c:out value="${error}" />
            <c:out value="${requestScope.errorMessage}" />
            <c:out value="${requestScope.error}" />
            <c:out value="${requestScope.values()}" />
        </div>
    </body>
</html>
