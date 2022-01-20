<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>

<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/processcustomer" method="post">
    <label for="email">Email: </label>
    <input type="text" name="email" id="email" value="${email}">
    <label for="password">Password:</label>
        <input type="text" name="password" id="password" value="${password}">
        <label for="state">State </label>
        <input type="text" name="state" id="state" value="${state}">
        <input type="submit" name="signup" value="Sign Up">
</form>
</body>
</html>