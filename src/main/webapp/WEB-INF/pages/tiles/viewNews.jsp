<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>

<div class="body-title">
	<a href="controller?command=go_to_news_list"><c:out value="${button_back_to_news}" /> >> </a> View News
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text"><c:out value="${news_title}" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_date}" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_brief}" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_content}" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${sessionScope.role eq 'admin'}">
<div class="first-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_update_news" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="<c:out value="${button_update}" />" />
	</form>
</div>

<div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_delete_news" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="<c:out value="${button_delete}" />" />
	</form>
</div>
</c:if>