<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Something went wrong</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="navbar">
        <span class="brand">Job Application Tracker</span>
        <span><a href="${pageContext.request.contextPath}/applications">Dashboard</a></span>
    </div>
    <div class="container">
        <div class="card">
            <h2>Something went wrong</h2>
            <p>The page you asked for could not be shown. Please go back to the dashboard and try again.</p>
            <p><a href="${pageContext.request.contextPath}/applications">Back to dashboard</a></p>
        </div>
    </div>
</body>
</html>