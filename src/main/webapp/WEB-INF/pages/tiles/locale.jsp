<head>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="loc" />

	<fmt:message bundle="${loc}" key="local.message" var="message" />
	<fmt:message bundle="${loc}" key="local.pagetitle" var="page_title" />

	<fmt:message bundle="${loc}" key="icon.pagelang" var="icon" />

	<fmt:message bundle="${loc}" key="local.error.name.global_error" var="error" />

	<fmt:message bundle="${loc}" key="local.menu.welcome.label" var="welcome_label" />
	<fmt:message bundle="${loc}" key="local.menu.welcome.message" var="welcome_message" />


	<fmt:message bundle="${loc}" key="local.popup.login" var="popup_login" />
	<fmt:message bundle="${loc}" key="local.popup.password" var="popup_password" />
	<fmt:message bundle="${loc}" key="local.popup.name" var="popup_name" />
	<fmt:message bundle="${loc}" key="local.popup.surname" var="popup_surname" />
	<fmt:message bundle="${loc}" key="local.popup.button.enter" var="popup_btn_enter" />
	<fmt:message bundle="${loc}" key="local.popup.button.register" var="popup_btn_register" />
	<fmt:message bundle="${loc}" key="local.popup.label.register" var="popup_label_register" />
	<fmt:message bundle="${loc}" key="local.popup.label.signin" var="popup_label_signin" />

	<fmt:message bundle="${loc}" key="local.header.login" var="header_login" />
	<fmt:message bundle="${loc}" key="local.header.password" var="header_password" />
	<fmt:message bundle="${loc}" key="local.header.registration" var="header_registration" />
	<fmt:message bundle="${loc}" key="local.header.signin" var="header_singin" />
	<fmt:message bundle="${loc}" key="local.header.signout" var="header_singout" />
	<fmt:message bundle="${loc}" key="local.header.name" var="header_name" />
	<fmt:message bundle="${loc}" key="local.header.loginerror" var="login_error" />
	<fmt:message bundle="${loc}" key="local.header.button.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.header.button.en" var="en_button" />

	<fmt:message bundle="${loc}" key="local.menu.button.add" var="menu_add_news" />
	<fmt:message bundle="${loc}" key="local.menu.button.list" var="menu_news_list" />

	<fmt:message bundle="${loc}" key="local.body.button.delete" var="button_delete" />
	<fmt:message bundle="${loc}" key="local.body.button.add" var="button_add" />
	<fmt:message bundle="${loc}" key="local.body.button.news" var="button_show_news" />
	<fmt:message bundle="${loc}" key="local.body.button.update" var="button_update" />
	<fmt:message bundle="${loc}" key="local.body.button.more" var="button_more" />
	<fmt:message bundle="${loc}" key="local.body.button.back_to_news" var="button_back_to_news" />
	<fmt:message bundle="${loc}" key="local.body.button.apply" var="button_apply" />

	<fmt:message bundle="${loc}" key="local.label.news" var="label_news" />
	<fmt:message bundle="${loc}" key="local.label.update" var="label_update" />
	<fmt:message bundle="${loc}" key="local.label.add" var="label_add" />

	<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
	<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
	<fmt:message bundle="${loc}" key="local.news.brief" var="news_brief" />
	<fmt:message bundle="${loc}" key="local.news.content" var="news_content" />

	<fmt:message bundle="${loc}" key="local.category.society" var="society" />
	<fmt:message bundle="${loc}" key="local.category.sport" var="sport" />
	<fmt:message bundle="${loc}" key="local.category.weather" var="weather" />
	<fmt:message bundle="${loc}" key="local.category.label" var="category_label" />
	<fmt:message bundle="${loc}" key="local.category.all" var="category_all" />

</head>