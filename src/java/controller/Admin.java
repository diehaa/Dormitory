/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.AdminDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.MaHoa;

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        } else if (action.equals("view-account")) {
            viewAccount(request, response);
        } else if (action.equals("view-account-detail")) {
            viewAccountDetail(request, response);
        } else if (action.equals("add-account")) {
            addAccount(request, response);
        } else if (action.equals("edit-account")) {
//            editAccount(request, response);
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

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //password = MaHoa.toSHA1(password);

        final model.Admin admin = new model.Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        final AdminDAO tkad = new AdminDAO();
        final model.Admin adminAuth = tkad.selectByUserAndPassword(admin);

        final model.Users user = new model.Users();
        user.setUsername(username);
        user.setPassword(password);
        final UserDAO tkuser = new UserDAO();
        final model.Users userAuth = tkuser.selectByUserAndPassword(user);

        String url = "";
        if (adminAuth != null) {
            final HttpSession session = request.getSession();
            session.setAttribute("adminAuth", (Object) adminAuth);
            url = "/admin-dashboard.jsp";
        } else if (userAuth != null) {
            final HttpSession session = request.getSession();
            session.setAttribute("userAuth", (Object) userAuth);
            url = "/user-dashboard.jsp";
        } else {
            request.setAttribute("error", (Object) "T\u00ean \u0111\u0103ng nh\u1eadp ho\u1eb7c m\u1eadt kh\u1ea9u kh\u00f4ng \u0111\u00fang!");
            url = "/admin-login.jsp";
        }


        final RequestDispatcher rd = this.getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //huỷ bỏ ssession
        session.invalidate();
        response.sendRedirect("admin-login.jsp");
    }

    protected void viewAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminDAO admin = new AdminDAO();
        ArrayList<model.Admin> list = admin.getListTaiKhoanAdmin();
        request.setAttribute("data", list);
        request.getRequestDispatcher("admin-account.jsp").forward(request, response);
    }

    protected void addAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");

        request.setAttribute("username", username);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("role", role);
        request.setAttribute("phone", phone);

        String url = "";
        String error = "";
        AdminDAO adminDAO = new AdminDAO();
        if (adminDAO.kiemTraTenDangNhap(username)) {
            error += "Username already exists, please choose another username! <br>";
        }

        if (!password.equals(repassword)) {
            error += "Password does not match!<br>";
        } else {
            password = MaHoa.toSHA1(password);
        }

        request.setAttribute("error", error);

        if (error.length() > 0) {
            url = "/admin?action=view-account";
        } else {
            Random rd = new Random();
            Calendar instance = Calendar.getInstance();
            int year = instance.get(Calendar.YEAR);
            int adminId = year + rd.nextInt(100000);
            model.Admin admin = new model.Admin(adminId, username, password, name, email, role, phone);
            adminDAO.insert(admin);
            url = "/admin?action=view-account";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void viewAccountDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String adminId = request.getParameter("adminId");
        AdminDAO admin = new AdminDAO();
        model.Admin list = admin.getListTaiKhoanAdminByIdString(adminId);
        request.setAttribute("data", list);
        request.getRequestDispatcher("admin-account-edit.jsp").forward(request, response);
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
