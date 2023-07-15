/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.AdminDAO;
import database.PaymentDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import util.MaHoa;

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "User", urlPatterns = {"/user"})
public class User extends HttpServlet {

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
        if (action.equals("view-profile")) {
            viewProfile(request, response);
        } else if (action.equals("change-password")) {
            changePassword(request, response);
        } else if (action.equals("change-user-password")) {
            changeUserPassword(request, response);
        } else if (action.equals("view-payment")) {
            viewPayment(request, response);
        }else if (action.equals("confirm-payment")) {
            confirmPayment(request, response);
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

    protected void viewProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-profile.jsp").forward(request, response);
    }

    protected void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-change-password.jsp").forward(request, response);
    }

    protected void changeUserPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usersId_raw = request.getParameter("usersId");
        int usersId = Integer.parseInt(usersId_raw);
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String renewpassword = request.getParameter("renewpassword");
        oldpassword = MaHoa.toSHA1(oldpassword);

        model.Users users = new model.Users();
        users.setUsername(username);
        users.setPassword(oldpassword);
        UserDAO tkad = new UserDAO();
        model.Users xacThucMK = tkad.selectByUserAndPassword(users);

        String error = "";
        String url = "";
        if (xacThucMK == null) {
            error += "Old Password is incorect!<br>";
        }
        if (!newpassword.equals(renewpassword)) {
            error += "New Password does not match!<br>";
        } else {
            renewpassword = MaHoa.toSHA1(renewpassword);
        }
        if (error.length() > 0) {

            url = "/user-change-password.jsp";
        } else {

            model.Users users1 = new model.Users(usersId, renewpassword);
            tkad.changePassword(users1);
            error += "Change password successfully!<br>";
            url = "/user-change-password.jsp";
        }
        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void viewPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userAuth");
        Users users = (Users) obj;
        int userId = users.getUsersId();

        PaymentDAO paymentDAO = new PaymentDAO();
        ArrayList<model.Payment> list = paymentDAO.getListByUser(userId);
        request.setAttribute("data", list);
        request.getRequestDispatcher("user-payment.jsp").forward(request, response);
    }
    protected void confirmPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId_raw = request.getParameter("paymentId");
        int paymentId = Integer.parseInt(paymentId_raw);
        PaymentDAO paymentDAO = new PaymentDAO();
        model.Payment paymentDetail = paymentDAO.getPaymentDetail(paymentId);
        
        request.setAttribute("data", paymentDetail);
        request.getRequestDispatcher("user-confirm-payment.jsp").forward(request, response);
    }
}
