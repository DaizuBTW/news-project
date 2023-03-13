<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>


<div class="body-title">
    <a href="controller?command=go_to_news_list"><c:out value="${button_back_to_news}" /> >> </a> <c:out value="${label_update}" />
</div>

<div class="add-table-margin">
    <form action="controller" method="post">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text"><c:out value="${news_title}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <textarea name="title" cols="40" rows="2" required
                              placeholder="<c:out value="${requestScope.news.title }" />"
                    ><c:out value="${requestScope.news.title }" /></textarea>
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${news_date}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="datetime-local" name="date" value="<c:out value="${requestScope.news.newsDate }" />">
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${news_brief}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <textarea name="brief" cols="40" rows="2" required
                              placeholder="<c:out value="${requestScope.news.briefNews }" />"
                    ><c:out value="${requestScope.news.briefNews }" /></textarea>
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${news_content}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <textarea name="content" cols="40" rows="3" required
                              placeholder="<c:out value="${requestScope.news.content }" />"
                    ><c:out value="${requestScope.news.content }" /></textarea>
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${category_label}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <select name="category">
                        <option value="1"><c:out value="${sport}" /></option>
                        <option value="2"><c:out value="${society}" /></option>
                        <option value="3"><c:out value="${weather}" /></option>
                    </select>
                </div></td>
            </tr>
        </table>
    <div class="body-button-position">
        <input type="hidden" name="command" value="do_update_news" />
        <input type="hidden" name="id" value="${news.idNews}" />
        <input type="submit" value="<c:out value="${button_update}" />" />
    </div>
    </form>
</div>
