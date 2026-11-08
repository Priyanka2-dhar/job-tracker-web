<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Application Tracker</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <span class="brand">Job Application Tracker</span>
        <span><a href="applications">Dashboard</a></span>
    </div>

    <div class="container">
        <div class="stats">
            <div class="stat"><div class="num">${total}</div><div class="label">Total</div></div>
            <div class="stat"><div class="num">${counts.Applied}</div><div class="label">Applied</div></div>
            <div class="stat"><div class="num">${counts.Interview}</div><div class="label">Interview</div></div>
            <div class="stat"><div class="num">${counts.Offer}</div><div class="label">Offer</div></div>
            <div class="stat"><div class="num">${counts.Rejected}</div><div class="label">Rejected</div></div>
        </div>

        <div class="card due">
            <strong>Follow-ups due</strong>
            <c:if test="${empty followUps}">
                <p class="empty">Nothing to follow up today.</p>
            </c:if>
            <c:forEach var="f" items="${followUps}">
                <p><c:out value="${f.company}"/> &ndash; <c:out value="${f.role}"/>
                   <span class="badge ${f.status}">${f.status}</span>
                   (follow-up date ${f.followUpDate})</p>
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
        <div class="card">
            <form method="get" action="applications">
                <input type="text" name="q" placeholder="Search company or role"
                       value="<c:out value='${q}'/>">
                <select name="status">
                    <option value="">All statuses</option>
                    <option value="Applied" ${selectedStatus == 'Applied' ? 'selected' : ''}>Applied</option>
                    <option value="Interview" ${selectedStatus == 'Interview' ? 'selected' : ''}>Interview</option>
                    <option value="Offer" ${selectedStatus == 'Offer' ? 'selected' : ''}>Offer</option>
                    <option value="Rejected" ${selectedStatus == 'Rejected' ? 'selected' : ''}>Rejected</option>
                </select>
                <button type="submit">Search</button>
                <a href="applications" style="margin-left:10px;">Clear</a>
            </form>
        </div>
        <h2>All applications</h2>
        <table>
            <tr>
                <th>ID</th><th>Company</th><th>Role</th><th>Applied</th>
                <th>Status</th><th>Follow up</th><th>Actions</th>
            </tr>
            <c:forEach var="a" items="${applications}">
                <tr class="${a.overdue ? 'overdue' : ''}">
                    <td>${a.id}</td>
                    <td><c:out value="${a.company}"/></td>
                    <td><c:out value="${a.role}"/></td>
                    <td>${a.appliedDate}</td>
                    <td><span class="badge ${a.status}">${a.status}</span></td>
                    <td>${a.followUpDate}
                        <c:if test="${a.overdue}"><span class="overdue-tag">Overdue</span></c:if>
                    </td>
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
                <c:if test="${empty applications}">
                    <p class="empty">No applications match your search.</p>
                </c:if>
    </div>
</body>
</html>