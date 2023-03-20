<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>


<div class="body-title">
    <a href="controller?command=go_to_news_list"><c:out value="${button_back_to_news}"/> >> </a> Error page
</div>

<div class="add-table-margin">
    error
</div>
<html>
<head>
    <title>error</title>
    <link rel="stylesheet" type="text/css" href="styles/newsStyle.css">
</head>
<body>
<div class="error-page">
    <div class="error-wrapper">
        <h1>Ой...</h1>
        <h2>Что-то пошло не так, попробуйте позже</h2>
        <br/>
        <br/>
        <form>
            <input type="hidden" name="command" value="go_to_news_list" />
            <input type="submit" class="error-input" value="<c:out value="${button_back_to_news}"/>">
        </form>
    </div>
</div>
</body>
</html>
