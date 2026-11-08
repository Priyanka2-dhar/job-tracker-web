package com.jobtracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/applications")
public class ApplicationServlet extends HttpServlet {
    private final ApplicationDAO dao = new ApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("applications", dao.getAll());
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }
}
