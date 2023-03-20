<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>


<div class="body-title">
    <a href="controller?command=go_to_news_list"><c:out value="${button_back_to_news}" /> >> </a> <c:out value="${label_update}" />
</div>

<div class="add-table-margin">
    <form action="controller" method="post">
        <div class="news-wrapper">
            <label for="title">
                <p class="label_popup"><c:out value="${news_title}"/></p>
                <textarea name="title" cols="40" rows="3" id="title" required
                          placeholder="<c:out value="${requestScope.news.title }" />"
                ><c:out value="${requestScope.news.title }" /></textarea>
            </label>
            <label for="date">
                <p class="label_popup"><c:out value="${news_date}" /></p>
                <input type="datetime-local" name="date" id="date"  required>
            </label>
            <label for="brief">
                <p class="label_popup"><c:out value="${news_brief}" /></p>
                <textarea name="brief" cols="40" rows="5" id="brief" required
                          placeholder="<c:out value="${requestScope.news.briefNews }" />"
                ><c:out value="${requestScope.news.briefNews }" /></textarea>
            </label>
            <label for="content">
                <p class="label_popup"><c:out value="${news_content}" /></p>
                <textarea name="content" cols="40" rows="15" id="content" required
                          placeholder="<c:out value="${requestScope.news.content }" />"
                ><c:out value="${requestScope.news.content }" /></textarea>
            </label>
            <label for="category">
                <p class="label_popup"><c:out value="${category_label}" /></p>
                <select name="category" id="category">
                    <option value="1"><c:out value="${sport}" /></option>
                    <option value="2"><c:out value="${society}" /></option>
                    <option value="3"><c:out value="${weather}" /></option>
                </select>
            </label>
        </div>
        <c:if test="${not (requestScope.newsError eq null)}">
            <div class="menu-wrapper-error">
                <p>
                    <fmt:message bundle="${loc}" key="${requestScope.newsError}" var="error_message"/>
                    <c:out value="${error_message}"/>
                </p>
            </div>
        </c:if>
    <div class="body-button-position">
        <input type="hidden" name="command" value="do_update_news" />
        <input type="hidden" name="id" value="${news.idNews}" />
        <input type="submit" value="<c:out value="${button_update}" />" />
    </div>
    </form>
</div>
