package com.online.plant.servlets;

import com.online.plant.constants.UIConstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "Home", urlPatterns = "/home")
@ServletSecurity(
        @HttpConstraint(rolesAllowed
                = {"administrator", "saller", "buyer"}))
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getRemoteUser();
        if (name == null) {
            out.println("You seem to be null. You must have arrived here without logging in.");
        } else if (request.isUserInRole("saller")) {
            out.println(UIConstants.HTML_START);
            out.println(UIConstants.ELEMENT_HEADER.replace("/assets", request.getContextPath() + "/assets"));
            out.println(UIConstants.BODY_START);
            out.println(String.format(UIConstants.ELEMENT_TOP_BAR.replace("/assets", request.getContextPath() + "/assets"), request.getContextPath() + "/home", name));
            out.println(String.format(UIConstants.CONTENT_INDEX_FORMATTED.replace("/assets", request.getContextPath() + "/assets"), "webresources/transactions/" + name, "webresources/transactions/" + name));
            out.println("</div>");
            out.println(UIConstants.ELEMENT_FOOTER);
            out.println(UIConstants.ELEMENT_JAVASCRIPT.replace("/assets", request.getContextPath() + "/assets"));
            out.println(UIConstants.BODY_END);
            out.println(UIConstants.HTML_END);
        } else if (request.isUserInRole("buyer")) {
            out.println(UIConstants.HTML_START);
            out.println(UIConstants.ELEMENT_HEADER.replace("/assets", request.getContextPath() + "/assets"));
            out.println(UIConstants.BODY_START);
            out.println(String.format(UIConstants.ELEMENT_TOP_BAR.replace("/assets", request.getContextPath() + "/assets"), request.getContextPath() + "/home", name));
            out.println(String.format(UIConstants.CONTENT_INDEX_FORMATTED.replace("/assets", request.getContextPath() + "/assets"), "webresources/transactions/" + name, "webresources/transactions/" + name));
            out.println("</div>");
            out.println(UIConstants.ELEMENT_FOOTER);
            out.println(UIConstants.ELEMENT_JAVASCRIPT.replace("/assets", request.getContextPath() + "/assets"));
            out.println(UIConstants.BODY_END);
            out.println(UIConstants.HTML_END);
        } else if (request.isUserInRole("administrator")) {//customised content for users in role employee
            out.println("<h2>Greetings " + name + "!</h2>");
            out.println("<p>only client can access te web page</p>");
        }

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
