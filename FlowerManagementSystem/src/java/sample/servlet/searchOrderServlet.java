/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.AccountDAO;
import sample.dao.OrderDAO;
import sample.dto.Account;
import sample.dto.Order;

/**
 *
 * @author HNC
 */
public class searchOrderServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String nameOrder = request.getParameter("txtsearch");

            String from = request.getParameter("from");
            String to = request.getParameter("to");

            LocalDate datecheck = null;
            ArrayList<Order> list = OrderDAO.getAllOrders();
            ArrayList<Order> listOrder = new ArrayList();
            ArrayList<Account> acc = AccountDAO.getAccountByPhone(nameOrder);

            if (!nameOrder.equals("")) {
                if (!from.equals("") || !to.equals("")) {
                    from = request.getParameter("from") == null ? "2021-01-01" : request.getParameter("from");
                    to = request.getParameter("to") == null ? "2030-01-01" : request.getParameter("to");
                    LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    for (Order ord : list) {

                        datecheck = LocalDate.parse(ord.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        for (Account account : acc) {
                            if (ord.getAccID() == account.getAccID() && datecheck.isAfter(dateFrom) && datecheck.isBefore(dateTo)) {
                                listOrder.add(ord);
                            }
                        }

                    }
                } else {
                    for (Order ord : list) {
                        for (Account account : acc) {
                            if (ord.getAccID() == account.getAccID()) {
                                listOrder.add(ord);
                            }
                        }

                    }
                }
            } else {
                if (from != null && to != null) {
                    for (Order ord : list) {
                        from = (request.getParameter("from") == null) ? "2021-01-01" : request.getParameter("from");
                        to = (request.getParameter("to") == null) ? "2030-01-01" : request.getParameter("to");
                        LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        datecheck = LocalDate.parse(ord.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        if (datecheck.isAfter(dateFrom) && datecheck.isBefore(dateTo)) {
                            listOrder.add(ord);
                        }

                    }
                } else {
                    for (Order ord : list) {
                        listOrder.add(ord);
                        
                    }
                }

            }
            request.setAttribute("txtsearch", nameOrder);
            request.setAttribute("list", listOrder);
            request.getRequestDispatcher("managerOrder.jsp").forward(request, response);
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
