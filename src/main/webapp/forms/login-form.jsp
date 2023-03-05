<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>
<a class="close">x</a>
<h3><c:out value="${popup_label_signin}" /></h3>
<form class="pop-up" action="controller" method="post">
    <input type="hidden" name="command" value="do_sign_in" />
    <label for="username">
        <c:out value="${popup_login}" />
        <input type="text" name="username" value="admin" id="username" placeholder="<c:out value="${header_login}" /> "/>
    </label>
    <label for="password">
        <c:out value="${popup_password}" />
        <input type="password" name="password" value="admin" id="password" placeholder="<c:out value="${header_password}" />"/>
    </label>
    <input type="submit" name="log-in" id="" value="<c:out value="${popup_btn_enter}" />">
</form>
