<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>All User</title>
</head>
<body>
<h1>List User</h1>
<a href="user/add.html">User List</a>
<br></br>
<c:if test="${!empty users}">
	<table border="5" width="600">
		<tr>
			<th>UserName</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>password</th>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.username}"/></td>
				<td><c:out value="${user.firstname}"/></td>
				<td><c:out value="${user.lastname}"/></td>
				<td><c:out value="${user.getDecryptedPassword()}"/></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>