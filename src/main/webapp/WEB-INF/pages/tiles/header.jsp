<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>

<div class="wrapper">
	<div class="newstitle">News management</div>


	<div class="local-link">

		<div class="locals" align="right">

			<a href="controller?command=do_localization&local=en"> <c:out value="${en_button}" /> </a> &nbsp;&nbsp;
			<a href="controller?command=do_localization&local=ru"> <c:out value="${ru_button}" /> </a> <br /> <br />
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_in" />
					<input type="text" name="username" value="" placeholder="<c:out value="${header_login}" />"/><br />
					<input type="password" name="password" value="" placeholder="<c:out value="${header_password}" />"/><br />

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red">
							<c:out value="${login_error}" />
						</font>
						<script>
							window.alert("<c:out value="${login_error}" />");
						</script>
					</c:if>
					<div class="signin-btn">
						<input type="submit" value="<c:out value="${header_singin}" />" />
					</div>
				</form>
				<form class="reg" action="controller" method="post">
					<input type="hidden" name="command" value="go_to_registration_page" />
					<input type="submit" value="<c:out value="${header_registration}" />" /><br />
				</form>
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="<c:out value="${header_singout}" />" /><br />
				</form>
			</div>

		</c:if>
	</div>

</div>
