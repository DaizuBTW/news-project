<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>

<div class="wrapper">
	<div class="newstitle">News management</div>



	<div class="local-link">
		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<a href="" class="overlayLink" data-action="login-form.jsp">Log-in</a>
				<a href="" class="overlayLink" data-action="registration-form.jsp">Register</a>
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
				<c:out value="${sessionScope.username}" />
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="<c:out value="${header_singout}" />" /><br />
				</form>
			</div>

		</c:if>
	</div>

</div>
