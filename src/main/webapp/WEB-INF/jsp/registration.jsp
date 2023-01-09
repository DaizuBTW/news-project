<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
registration
<form action="controller" method="post">
    <input type="hidden" name="command" value="do_registration" /> Enter login:<br />
    <input type="text" name="username" value="" /><br /> Enter password:<br />
    <input type="password" name="password" value="" /><br /> Enter name:<br />
    <input type="text" name="name" value="" /><br /> Enter surname:<br />
    <input type="text" name="surname" value="" /><br />
    <input type="submit" value="Отправить" /><br />
</form>
</body>
</html> 