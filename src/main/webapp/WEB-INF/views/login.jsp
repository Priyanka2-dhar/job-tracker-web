<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log in</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <span class="brand">Job Application Tracker</span>
    </div>

    <div class="container">
        <h2>Log in</h2>
        <div class="card form-card">
            <c:if test="${not empty error}">
                <div class="error-msg"><c:out value="${error}"/></div>
            </c:if>
            <form method="post" action="login">
                <label>Username</label>
                <input type="text" name="username" maxlength="50" required autofocus>

                <label>Password</label>
                <input type="password" name="password" required>

                <p style="margin-top:20px;">
                    <button type="submit">Log in</button>
                </p>
            </form>
        </div>
    </div>
</body>
</html>