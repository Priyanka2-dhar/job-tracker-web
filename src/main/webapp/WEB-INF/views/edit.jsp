<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit application</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <span class="brand">Job Application Tracker</span>
                <span>
                    <a href="applications">Dashboard</a>
                    <span class="who">Signed in as <c:out value="${sessionScope.user}"/></span>
                    <form method="post" action="logout" class="logout-form">
                        <button type="submit" class="link-btn">Log out</button>
                    </form>
                </span>
    </div>

    <div class="container">
        <h2>Edit application #${id}</h2>
        <div class="card form-card">
            <c:if test="${not empty error}">
                <div class="error-msg"><c:out value="${error}"/></div>
            </c:if>
            <form method="post" action="edit">
                <input type="hidden" name="id" value="${id}">

                <label>Company</label>
                <input type="text" name="company" maxlength="100"
                       value="<c:out value='${company}'/>" required>

                <label>Role</label>
                <input type="text" name="role" maxlength="100"
                       value="<c:out value='${role}'/>" required>

                <label>Status</label>
                <select name="status">
                    <option ${status == 'Applied' ? 'selected' : ''}>Applied</option>
                    <option ${status == 'Interview' ? 'selected' : ''}>Interview</option>
                    <option ${status == 'Offer' ? 'selected' : ''}>Offer</option>
                    <option ${status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                </select>

                <label>Follow-up date</label>
                <input type="date" name="followUp" value="<c:out value='${followUp}'/>" required>

                <p style="margin-top:20px;">
                    <button type="submit">Save changes</button>
                    <a href="applications" style="margin-left:12px;">Cancel</a>
                </p>
            </form>
        </div>
    </div>
</body>
</html>