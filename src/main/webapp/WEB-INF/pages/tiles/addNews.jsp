<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
    <a href="controller?command=go_to_news_list">News >> </a> View News
</div>

<div class="add-table-margin">
    <form action="controller" method="post">
        <table class="news_text_format">
            <input type="hidden" name="command" value="do_add_news" />
            <tr>
                <td class="space_around_title_text">News Title</td>

                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="title" value="" />
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">News Date</td>

                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="date" value="" />
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">Brief</td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="brief" value="" />
                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">Content</td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="content" value="" />
                </div></td>
            </tr>
        </table>
        <input type="submit" value="add news" />
    </form>
</div>
