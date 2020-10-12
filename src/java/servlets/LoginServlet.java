package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;

/**
 *
 * @author Raymond
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getAttribute("logout") != null) {
            session.invalidate();
            session = request.getSession();
            request.setAttribute("message", "You have successfully logged out");
        }

        if (session.getAttribute("username") != null || session.getAttribute("password") != null) {
            response.sendRedirect("home");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            request.setAttribute("message", "Login Credentials are Invalid");
        } else {
            AccountService validation = new AccountService();
            User user = validation.login(username, password);
            if (user == null) {
                request.setAttribute("message", "Account not found");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("home");
                return;
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }
}
