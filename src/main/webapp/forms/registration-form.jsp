<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>
<a class="close">x</a>
<h3><c:out value="${popup_label_register}" /></h3>
<form class="pop-up" action="controller" method="post">
  <input type="hidden" name="command" value="do_registration" />
  <label for="username">
    <p class="label_popup"><c:out value="${popup_login}" /></p>
    <input type="text" name="username" id="username" placeholder="<c:out value="${popup_login}" />" />
  </label>
  <label for="password">
    <p class="label_popup"><c:out value="${popup_password}" /></p>
    <input type="password" name="password" id="password" placeholder="<c:out value="${popup_password}" />" />
  </label>
  <label for="name">
    <p class="label_popup"><c:out value="${popup_name}" /></p>
    <input type="text" name="name" id="name" placeholder="<c:out value="${popup_name}" />" />
  </label>
  <label for="surname">
    <p class="label_popup"><c:out value="${popup_surname}" /></p>
    <input type="text" name="surname" id="surname" placeholder="<c:out value="${popup_surname}" />" />
  </label>
  <input type="submit" name="log-in" id="" value="<c:out value="${popup_btn_enter}" />">
</form>
