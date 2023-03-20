<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/pages/tiles/locale.jsp" %>


<div class="menu-wrapper">
    <div class="menu-title-wrapper">
        <div class="menu-title">
            <c:out value="${label_news}"/>
        </div>
    </div>
    <div class="list-menu-invisible-wrapper">
        <hr>
        <div class="list-menu-wrapper">
            <ul style="list-style-image: url(/images/img.jpg); text-align: left;">
                <li style="padding-left: 15px;">

                    <a href="controller?command=go_to_news_list"><c:out value="${menu_news_list}"/></a><br/>
                </li>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <li style="padding-left: 15px;">
                        <a href="controller?command=go_to_add_news"><c:out value="${menu_add_news}"/></a>
                        <br/>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="clear"></div>

    </div>
</div>
<div class="menu-lang">
    <a href="controller?command=do_localization&local=en"> <c:out value="${en_button}"/> </a>
    <a href="controller?command=do_localization&local=ru"> <c:out value="${ru_button}"/> </a>
</div>
<c:if test="${not (requestScope.error eq null)}">
    <div class="menu-wrapper-error">
        <p>
            <fmt:message bundle="${loc}" key="${requestScope.error}" var="error_message"/>
            <c:out value="${error_message}"/>
        </p>
    </div>
</c:if>

