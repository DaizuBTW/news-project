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
    <script type="text/javascript" src="script/pop-up.js"></script>
    <link rel="shortcut icon" href="../../../images/en.png" type="image/png">
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
<div class="overlay" style="display: none;">
    <div class="login-wrapper">
        <div class="login-content" id="loginTarget">
            <a class="close">x</a>
            <h3>Sign in</h3>
            <form class="pop-up" action="controller" method="post">
                <input type="hidden" name="command" value="do_sign_in"/>
                <label for="username">
                    Username:
                    <input type="text" name="username" value="admin" id="username"
                           placeholder="<c:out value="${header_login}" /> "/><br/>

                    <!--<input type="text" name="username" id="username" placeholder="Username must be between 8 and 20 characters" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{8,20}$" required />-->
                </label>
                <label for="password">
                    Password:
                    <!--<input type="password" name="password" id="password" placeholder="Password must contain 1 uppercase, lowercase and number" pattern="(?=^.{8,}$)((?=.*d)|(?=.*W+))(?![.n])(?=.*[A-Z])(?=.*[a-z]).*$" required />-->
                    <input type="password" name="password" value="admin" id="password"
                           placeholder="<c:out value="${header_password}" />"/><br/>
                </label>

                <button type="submit">Sign in</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>