package com.example.webapp;

import com.example.webapp.GenerateDAO.UserDAO;
import com.example.webapp.Objects.*;
import com.example.webapp.Support;

import com.example.webapp.validators.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "CustomerController", urlPatterns = "/processcustomer")
public class CustomerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestCustomer customer = RequestCustomer.fromRequestParameters(request);
        customer.setAsRequestAttributes(request);
        List violations = customer.validate();

        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
        }

        String url = determineUrl(violations);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private String determineUrl(List violations) {
        if (!violations.isEmpty()) {
            return "/";
        } else {
            return "/WEB-INF/views/managerPage.jsp";
        }
    }

    private static class RequestCustomer {

        private final String password;
        private final String email;
        private String state;

        public  ArrayList<Users> allUser;
        public int role_id;

        private RequestCustomer(String password, String email, String state) {
            this.password = password;
            this.email = email;
            this.state = state;
            this.allUser = new ArrayList<>();
        }

        public static RequestCustomer fromRequestParameters(
                HttpServletRequest request) {
            return new RequestCustomer(
                    request.getParameter("password"),
                    request.getParameter("email"),
                    request.getParameter("state"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) {
            request.setAttribute("password1", password);
            request.setAttribute("email", email);

            try {
                if(CheckOnRegistration()) {
                    state = "Sing in";
                    request.setAttribute("state", state);
                    Support sup = new Support();
                    this.role_id = sup.takeUserRole(email, password);
                }
                else{
                    state = "New user";
                    request.setAttribute("state", state);

                    UserDAO userDAO = new UserDAO();
                    Users us = new Users();
                    us.setRole("Client");
                    us.setPassword(password);
                    us.setEmail(email);
                    userDAO.insert(us);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public List validate(){
            List violations = new ArrayList<>();
            if (!StringValidator.validate(password)) {
                violations.add("Insert your password");
            }
            if (!EmailValidator.validate(email)) {
                violations.add("Insert email correct ****@***.***");
            }
            return violations;
        }

        public boolean CheckOnRegistration() throws SQLException {
            UserDAO uDAO = new UserDAO();
            ArrayList<Users> temp = uDAO.getAll();
            for (Users users : temp) {
                if (users.getEmail().equals(this.email) && users.getPassword().equals(this.password)) {
                    return true;
                }
            }
            return false;
        }

    }

}
