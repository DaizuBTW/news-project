<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>


<div class="body-title">
	<c:out value="${menu_news_list}" />
</div>

<form action="controller" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<p class="category">Политика</p>
					<h2><c:out value="${news.title}" /></h2>
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
							<a href="controller?command=go_to_update_news&id=${news.idNews}"><c:out value="${button_update}" /></a>
						</c:if>
						
						<a href="controller?command=go_to_view_news&id=${news.idNews}"><c:out value="${button_more}" /></a>
   					    
   					    <c:if test="${sessionScope.role eq 'admin'}">
   					         <input type="checkbox" name="id" value="${news.idNews }" />
   					    </c:if>
					</div>
				</div>
			</div>
		</div>

	</c:forEach>

	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="body-button-position">
				<input type="hidden" name="command" value="do_delete_news" />
				<input type="submit" value="<c:out value="${button_delete}" />" />
		</div>
	</c:if>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        	No news.
		</c:if>
	</div>
</form>
