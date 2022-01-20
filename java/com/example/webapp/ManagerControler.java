package com.example.webapp;

import com.example.webapp.GenerateDAO.UserDAO;
import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Users;
import com.example.webapp.validators.EmailValidator;
import com.example.webapp.validators.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManagerControler", urlPatterns = "/manager")
public class ManagerControler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ManagerControler.RequestCustomer customer = ManagerControler.RequestCustomer.fromRequestParameters(request);
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
            return "/WEB-INF/views/customerinfo.jsp";
        }
    }

    private static class RequestCustomer {

        private final String event;
        private final String data;

        public ArrayList<Event> allEvents;
        public int role_id;

        private RequestCustomer(String event, String data) {
            this.event = event;
            this.data = data;
            this.allEvents = new ArrayList<>();
        }

        public static ManagerControler.RequestCustomer fromRequestParameters(
                HttpServletRequest request) {
            return new ManagerControler.RequestCustomer(
                    request.getParameter("event"),
                    request.getParameter("data"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) {
            request.setAttribute("event", event);
            request.setAttribute("data", data);

        }

        public List validate(){
            List violations = new ArrayList<>();
            if (!StringValidator.validate(event)) {
                violations.add("Insert your event title");
            }
            if (!EmailValidator.validate(data)) {
                violations.add("Insert your data");
            }
            return violations;
        }
    }
}
