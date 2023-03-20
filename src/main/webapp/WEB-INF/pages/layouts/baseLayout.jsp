<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="script/validation.js"></script>
    <script type="text/javascript" src="script/pop-upp.jst"></script>
    <link rel="shortcut icon" href="../../../news_project_war/images/<c:out value="${icon}"/>" type="image/png">
    <title><c:out value="${page_title}"/></title>

    <link rel="stylesheet" type="text/css" href="styles/newsStyle.css">
    <link rel="stylesheet" type="text/css" href="styles/popUpElement.css">

</head>
<body>
<div class="page">
    <div class="header">
        <c:import url="/WEB-INF/pages/tiles/header.jsp"/>
    </div>

    <div class="base-layout-wrapper">
        <div class="menu">

            <c:if test="${not (sessionScope.user eq 'active')}">
                <div class="menu-wrapper">
                    <div class="menu-title-wrapper">
                        <div class="menu-title">
                            <h3><c:out value="${welcome_label}"/></h3>
                        </div>
                    </div>
                    <div class="list-menu-invisible-wrapper">
                        <hr>
                        <p class="welcome"><c:out value="${welcome_message}"/></p>
                    </div>
                </div>
                <div class="menu-lang">
                    <a href="controller?command=do_localization&local=en"> <c:out value="${en_button}"/> </a>
                    <a href="controller?command=do_localization&local=ru"> <c:out value="${ru_button}"/> </a>
                </div>
                <c:if test="${not (requestScope.error eq null)}">
                    <div class="menu-wrapper-error">
                        <p>
                            <fmt:message bundle="${loc}" key="${requestScope.error}" var="error_message"/>
                            <c:out value="${error_message}"/>
                        </p>
                    </div>
                </c:if>
                <%-- <c:import url=""></c:import> --%>
            </c:if>
            <c:if test="${sessionScope.user eq 'active'}">
                <c:import url="/WEB-INF/pages/tiles/menu.jsp"/>
            </c:if>
        </div>

        <div class="content">
            <c:if test="${not (sessionScope.user eq 'active')}">
                <c:import url="/WEB-INF/pages/tiles/guestInfo.jsp"/>
            </c:if>
            <c:if test="${sessionScope.user eq 'active'}">
                <c:import url="/WEB-INF/pages/tiles/body.jsp"/>
            </c:if>


        </div>
    </div>

    <div class="footer">

        <c:import url="/WEB-INF/pages/tiles/footer.jsp"/>
    </div>
</div>
<c:import url="/WEB-INF/pages/tiles/forms.jsp"/>
<!--
<div class="overlay" style="display: none;">
    <div class="login-wrapper">
        <div class="login-content" id="loginTarget">
        </div>
    </div>
</div>
-->
</body>

</html>