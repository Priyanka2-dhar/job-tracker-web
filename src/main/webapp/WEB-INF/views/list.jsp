<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Job Application Tracker</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background: #f6f7fb; }
        h1 { color: #333; }
        h2 { color: #333; margin-top: 32px; }
        .stats { display: flex; gap: 16px; margin-bottom: 24px; flex-wrap: wrap; }
        .stat { background: white; padding: 16px 24px; border-radius: 8px; min-width: 110px; text-align: center; }
        .stat .num { font-size: 28px; font-weight: bold; color: #4a6cf7; }
        .stat .label { color: #666; font-size: 14px; }
        .card { background: white; padding: 16px 20px; margin-bottom: 24px; border-radius: 8px; }
        .due { border-left: 5px solid #e5a000; }
        .empty { color: #666; }
        input[type=text] { padding: 8px; margin-right: 8px; }
        button { padding: 8px 14px; border: none; border-radius: 4px; cursor: pointer; background: #4a6cf7; color: white; }
        button.danger { background: #e5484d; }
        table { border-collapse: collapse; width: 100%; background: white; }
        th, td { padding: 10px 14px; border-bottom: 1px solid #ddd; text-align: left; }
        th { background: #4a6cf7; color: white; }
        form.inline { display: inline; }
    </style>
</head>
<body>
    <h1>Job Application Tracker</h1>

    <div class="stats">
        <div class="stat"><div class="num">${total}</div><div class="label">Total</div></div>
        <div class="stat"><div class="num">${counts.Applied}</div><div class="label">Applied</div></div>
        <div class="stat"><div class="num">${counts.Interview}</div><div class="label">Interview</div></div>
        <div class="stat"><div class="num">${counts.Offer}</div><div class="label">Offer</div></div>
        <div class="stat"><div class="num">${counts.Rejected}</div><div class="label">Rejected</div></div>
    </div>

    <div class="card due">
        <strong>Follow up today</strong>
        <c:if test="${empty followUps}">
            <p class="empty">Nothing to follow up today.</p>
        </c:if>
        <c:forEach var="f" items="${followUps}">
            <p><c:out value="${f.company}"/> &ndash; <c:out value="${f.role}"/>
               (${f.status}, follow-up date ${f.followUpDate})</p>
        </c:forEach>
    </div>

    <div class="card">
        <form method="post" action="applications">
            <input type="hidden" name="action" value="add">
            <input type="text" name="company" placeholder="Company" required>
            <input type="text" name="role" placeholder="Role" required>
            <button type="submit">Add application</button>
        </form>
    </div>

    <h2>All applications</h2>
    <table>
        <tr>
            <th>ID</th><th>Company</th><th>Role</th><th>Applied</th>
            <th>Status</th><th>Follow up</th><th>Actions</th>
        </tr>
        <c:forEach var="a" items="${applications}">
            <tr>
                <td>${a.id}</td>
                <td><c:out value="${a.company}"/></td>
                <td><c:out value="${a.role}"/></td>
                <td>${a.appliedDate}</td>
                <td>${a.status}</td>
                <td>${a.followUpDate}</td>
                <td>
                    <form method="post" action="applications" class="inline">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${a.id}">
                        <select name="status">
                            <option ${a.status == 'Applied' ? 'selected' : ''}>Applied</option>
                            <option ${a.status == 'Interview' ? 'selected' : ''}>Interview</option>
                            <option ${a.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                            <option ${a.status == 'Offer' ? 'selected' : ''}>Offer</option>
                        </select>
                        <button type="submit">Update</button>
                    </form>
                    <form method="post" action="applications" class="inline"
                          onsubmit="return confirm('Delete this application?');">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${a.id}">
                        <button type="submit" class="danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>