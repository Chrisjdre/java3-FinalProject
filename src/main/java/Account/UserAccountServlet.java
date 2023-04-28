package Account;

import Data_access.UserDAO_MySQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserAccountServlet", value = "/account")
public class UserAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("user", session.getAttribute("user"));
        request.getRequestDispatcher("WEB-INF/Account/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String[] nsfw = request.getParameterValues("nsfw"); // use with checkboxes

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Map<String, String> results = new HashMap<>();

        try {
            user.setFirst_name(firstName);
        } catch(IllegalArgumentException e) {
            results.put("firstNameError", e.getMessage());
        }
        try {
            user.setLast_name(lastName);
        } catch(IllegalArgumentException e) {
            results.put("lastNameError", e.getMessage());
        }
        try {
            user.setEmail(email);
        } catch(IllegalArgumentException e) {
            results.put("emailError", e.getMessage());
        }

        if(nsfw != null && nsfw[0].equals("on")) {
            user.setPrivileges("NSFW");
        }else{
            user.setPrivileges("none");
        }

        UserDAO_MySQL dao = new UserDAO_MySQL();
        boolean success = dao.updateUser(user);
        if(success){
            session.setAttribute("user", user);
            response.sendRedirect("account");
        } else {
            request.setAttribute("user", user);
            request.setAttribute("error", "Something went wrong");
            request.setAttribute("results", results);
            request.getRequestDispatcher("WEB-INF/Account/account.jsp").forward(request, response);
        }
    }
}
