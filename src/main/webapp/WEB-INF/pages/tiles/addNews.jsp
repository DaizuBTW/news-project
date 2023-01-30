<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>

<div class="body-title">
    <a href="controller?command=go_to_news_list"><c:out value="${button_back_to_news}" /> >> </a> <c:out value="${label_add}" />
</div>

<div class="add-table-margin">
    <form action="controller" method="post">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text"><c:out value="${news_title}" /></td>

                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="title" value="" />
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${news_date}" /></td>

                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="date" value="" />
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${news_brief}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="brief" value="" />
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text"><c:out value="${news_content}" /></td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="content" value="" />
                </div></td>
            </tr>
        </table>
        <div class="body-button-position">
            <input type="hidden" name="command" value="do_add_news" />
            <input type="submit" value="<c:out value="${button_add}" />" />
        </div>
    </form>
</div>
