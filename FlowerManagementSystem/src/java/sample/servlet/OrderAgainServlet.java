/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.OrderDAO;
import sample.dto.Order;
import sample.dto.OrderDetail;

/**
 *
 * @author HNC
 */
public class OrderAgainServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            HashMap<String, Integer> cart = new HashMap<String, Integer>();

            if (session != null) {
                String orderid = request.getParameter("orderID");
                String name = (String) session.getAttribute("name");
                String email = (String) session.getAttribute("email");
                ArrayList<Order> listOrder = OrderDAO.getOrders(email);
                ArrayList<Order> list = new ArrayList<>();

                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> listDetail = OrderDAO.getOrderDetail(orderID);
                    if (listDetail != null && !listDetail.isEmpty()) {
                        for (OrderDetail o : listDetail) {
                            cart.put(Integer.toString(o.getPlantID()), o.getQuantity());
                        }

                    }
                }
                if (name == null || name.equals("")) {
                    request.setAttribute("WARNING", "you mus login to finish the shopping");
                    for (Order ord : listOrder) {
                        if (ord.getStatus() == 3) {
                            list.add(ord);
                        }
                    }
                    request.setAttribute("email", email);
                    request.setAttribute("listCompleted", list);
                    request.getRequestDispatcher("orderStatusDetail.jsp").forward(request, response);
                } else {
                    boolean result = OrderDAO.insertOrder(email, cart);
                    if (result) {

                        session.setAttribute("cart", null);
                        request.setAttribute("WARNING", "Save your cart sucessfully");
                        for (Order ord : listOrder) {
                            if (ord.getStatus() == 3) {
                                list.add(ord);
                            }
                        }
                        request.setAttribute("email", email);
                        request.setAttribute("listCompleted", list);
                        request.getRequestDispatcher("orderStatusDetail.jsp").forward(request, response);;
                    } else {
                        request.setAttribute("WARNING", "These products are out of stock");
                        for (Order ord : listOrder) {
                        if (ord.getStatus() == 3) {
                            list.add(ord);
                        }
                    }
                    request.setAttribute("email", email);
                    request.setAttribute("listCompleted", list);
                        request.getRequestDispatcher("orderStatusDetail.jsp").forward(request, response);
                    }
                }
            } else {
                response.sendRedirect("index.isp");
            }

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
