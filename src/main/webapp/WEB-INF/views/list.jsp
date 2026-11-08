<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Job Application Tracker</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background: #f6f7fb; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; background: white; }
        th, td { padding: 10px 14px; border-bottom: 1px solid #ddd; text-align: left; }
        th { background: #4a6cf7; color: white; }
    </style>
</head>
<body>
    <h1>Job Application Tracker</h1>
    <table>
        <tr>
            <th>ID</th><th>Company</th><th>Role</th>
            <th>Applied</th><th>Status</th><th>Follow up</th>
        </tr>
        <c:forEach var="a" items="${applications}">
            <tr>
                <td>${a.id}</td>
                <td>${a.company}</td>
                <td>${a.role}</td>
                <td>${a.appliedDate}</td>
                <td>${a.status}</td>
                <td>${a.followUpDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>