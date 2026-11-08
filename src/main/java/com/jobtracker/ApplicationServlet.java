package com.jobtracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/applications")
public class ApplicationServlet extends HttpServlet {
    private final ApplicationDAO dao = new ApplicationDAO();
    private static final List<String> STATUSES = List.of("Applied", "Interview", "Rejected", "Offer");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String q = req.getParameter("q");
        String status = req.getParameter("status");
        if (q == null) q = "";
        if (status == null || !STATUSES.contains(status)) status = "";

        try {
            Map<String, Integer> counts = dao.getStatusCounts();
            int total = counts.values().stream().mapToInt(Integer::intValue).sum();

            req.setAttribute("applications", dao.search(q, status));
            req.setAttribute("counts", counts);
            req.setAttribute("total", total);
            req.setAttribute("followUps", dao.getFollowUps());
            req.setAttribute("q", q);
            req.setAttribute("selectedStatus", status);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                String company = req.getParameter("company");
                String role = req.getParameter("role");
                if (company != null && role != null
                        && !company.isBlank() && !role.isBlank()) {
                    dao.add(company.trim(), role.trim());
                }
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                String status = req.getParameter("status");
                if (STATUSES.contains(status)) {
                    dao.updateStatus(id, status);
                }
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("id")));
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect("applications");
    }
}