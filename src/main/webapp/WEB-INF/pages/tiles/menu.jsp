<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
		       News
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper">
			<ul style="list-style-image: url(/images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;">
				
				<a href="controller?command=go_to_news_list">news list</a><br />
				</li>

				<c:if test="${sessionScope.role eq 'admin'}">
					<li style="padding-left: 15px;">
				
					   <a href="controller?command=go_to_add_news">add news </a>
                
                    <br />
					
				</li></c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
</div>

