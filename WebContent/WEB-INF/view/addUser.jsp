<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head><title>Add user</title></head>
<body>
<h1>Add User</h1>

<c:url var="saveArticleUrl" value="/user/save.html" />
<form:form modelAttribute="user" method="POST" action="${saveArticleUrl}">
	<form:label path="username">User Name:</form:label>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<form:input path="username" size="46"/>
	<br></br>
	<form:label path="passwordHash">Password :</form:label>
	<form:password path="passwordHash" />
	<br />
	<form:label path="firstname">First name :</form:label>
	<form:input path="firstname" />
	<br />
	<br />
	<form:label path="lastname">Last name :</form:label>
	<form:input path="lastname" />
	<br />
	
	<br />
	<form:label path="email">Email:</form:label>
	<form:input path="email" />
	<br />
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="submit" value="Save Article" />
</form:form>

</body>
</html>