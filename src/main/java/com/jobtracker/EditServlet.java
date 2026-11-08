package com.jobtracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private final ApplicationDAO dao = new ApplicationDAO();
    private static final List<String> STATUSES = List.of("Applied", "Interview", "Rejected", "Offer");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            JobApplication a = dao.getById(id);
            if (a == null) {
                resp.sendRedirect("applications");
                return;
            }
            show(req, resp, id, a.getCompany(), a.getRole(), a.getStatus(),
                    a.getFollowUpDate().toString(), null);
        } catch (NumberFormatException e) {
            resp.sendRedirect("applications");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("applications");
            return;
        }

        String company = req.getParameter("company");
        String role = req.getParameter("role");
        String status = req.getParameter("status");
        String followUp = req.getParameter("followUp");

        String error = null;
        LocalDate followUpDate = null;
        if (company == null || company.isBlank()) {
            error = "Company cannot be empty.";
        } else if (role == null || role.isBlank()) {
            error = "Role cannot be empty.";
        } else if (company.trim().length() > 100 || role.trim().length() > 100) {
            error = "Company and role must be 100 characters or fewer.";
        } else if (!STATUSES.contains(status)) {
            error = "Please choose a valid status.";
        } else {
            try {
                followUpDate = LocalDate.parse(followUp);
            } catch (DateTimeParseException | NullPointerException e) {
                error = "Please enter a valid follow-up date.";
            }
        }

        if (error != null) {
            show(req, resp, id, company == null ? "" : company, role == null ? "" : role,
                    status == null ? "" : status, followUp == null ? "" : followUp, error);
            return;
        }

        try {
            dao.update(id, company.trim(), role.trim(), status, followUpDate);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect("applications");
    }

    private void show(HttpServletRequest req, HttpServletResponse resp, int id,
                      String company, String role, String status, String followUp, String error)
            throws ServletException, IOException {
        req.setAttribute("id", id);
        req.setAttribute("company", company);
        req.setAttribute("role", role);
        req.setAttribute("status", status);
        req.setAttribute("followUp", followUp);
        req.setAttribute("error", error);
        req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
    }
}