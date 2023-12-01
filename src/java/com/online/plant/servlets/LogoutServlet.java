package com.online.plant.servlets;

/* 
 * This servlet clears a user's stored credentials by logging them out.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tm352
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    /**
     * This method processes requests for both HTTP <code>GET</code> and
     * <code>POST</code> methods.
     *
     * Its main purpose is to invalidate the client's session and so log them
     * out.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "    <head>\n"
                    + "        <meta charset=\"utf-8\">\n"
                    + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                    + "        <meta name=\"description\" content=\"\">\n"
                    + "        <meta name=\"author\" content=\"\">\n"
                    + "        <title>Online Plant</title>\n"
                    + "        <link href=\"assets/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                    + "        <link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\">\n"
                    + "        <link href=\"assets/css/sb-admin-2.min.css\" rel=\"stylesheet\">\n"
                    + "    </head>\n"
                    + "\n"
                    + "    <body id=\"page-top\">\n"
                    + "\n"
                    + "        <!-- Page Wrapper -->\n"
                    + "        <div id=\"wrapper\">\n"
                    + "\n"
                    + "            <!-- Content Wrapper -->\n"
                    + "            <div id=\"content-wrapper\" class=\"d-flex flex-column\">\n"
                    + "\n"
                    + "                <!-- Main Content -->\n"
                    + "                <div id=\"content\">\n"
                    + "\n"
                    + "                    <!-- Topbar -->\n"
                    + "                    <nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">\n"
                    + "                        <ul class=\"navbar-nav ml-auto\">\n"
                    + "                            <div class=\"topbar-divider d-none d-sm-block\"></div>\n"
                    + "\n"
                    + "                            <!-- Nav Item - User Information -->\n"
                    + "                            <li class=\"nav-item dropdown no-arrow\">\n"
                    + "                                <a href=\"home\" class=\"d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm\">\n"
                    + "                                    <i class=\"fas fa-lock fa-sm text-white-50\"></i>&ensp;Login</a>\n"
                    + "                            </li>\n"
                    + "                        </ul>\n"
                    + "                    </nav>\n"
                    + "                    <!-- End of Topbar -->\n"
                    + "\n"
                    + "                    <!-- Begin Page Content -->\n"
                    + "                    <div class=\"container-fluid\">\n"
                    + "                        <div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n"
                    + "                            <h1 class=\"h3 mb-0 text-gray-800\">Logout Successfully</h1>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <div class=\"col-lg-12 mb-4\">\n"
                    + "\n"
                    + "                                <!-- Illustrations -->\n"
                    + "                                <div class=\"card shadow mb-4\">\n"
                    + "                                    <div class=\"card-header py-3\">\n"
                    + "                                        <h6 class=\"m-0 font-weight-bold text-primary\">Welcome</h6>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <div class=\"text-center\">\n"
                    + "                                            <img class=\"img-fluid px-3 px-sm-4 mt-3 mb-4\" style=\"width: 25rem;\"\n"
                    + "                                                 src=\"assets/img/undraw_rocket.svg\" alt=\"...\">\n"
                    + "                                        </div>\n"
                    + "                                        <p>Welcome in Online Plant </p>\n"
                    + "                                        Click here to <a href=\"home\">Login &rarr;</a>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <!-- End of Main Content -->\n"
                    + "\n"
                    + "                <!-- Footer -->\n"
                    + "                <footer class=\"sticky-footer bg-white\">\n"
                    + "                    <div class=\"container my-auto\">\n"
                    + "                        <div class=\"copyright text-center my-auto\">\n"
                    + "                            <span>Copyright &copy; Online Plant 2023</span>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </footer>\n"
                    + "                <!-- End of Footer -->\n"
                    + "\n"
                    + "            </div>\n"
                    + "            <!-- End of Content Wrapper -->\n"
                    + "\n"
                    + "        </div>\n"
                    + "        <!-- End of Page Wrapper -->\n"
                    + "\n"
                    + "        <!-- Scroll to Top Button-->\n"
                    + "        <a class=\"scroll-to-top rounded\" href=\"#page-top\">\n"
                    + "            <i class=\"fas fa-angle-up\"></i>\n"
                    + "        </a>\n"
                    + "\n"
                    + "        <!-- Logout Modal-->\n"
                    + "        <div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"\n"
                    + "             aria-hidden=\"true\">\n"
                    + "            <div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>\n"
                    + "                        <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "                            <span aria-hidden=\"true\">×</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>\n"
                    + "                    <div class=\"modal-footer\">\n"
                    + "                        <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>\n"
                    + "                        <a class=\"btn btn-primary\" href=\"login.html\">Login</a>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "\n"
                    + "        <script src=\"assets/vendor/jquery/jquery.min.js\"></script>\n"
                    + "        <script src=\"assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n"
                    + "        <script src=\"assets/vendor/jquery-easing/jquery.easing.min.js\"></script>\n"
                    + "        <script src=\"assets/js/sb-admin-2.min.js\"></script>\n"
                    + "    </body>\n"
                    + "</html>");

            request.logout();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
