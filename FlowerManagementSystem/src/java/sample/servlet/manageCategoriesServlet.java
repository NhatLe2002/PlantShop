/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.CategoriesDAO;
import sample.dto.Account;
import sample.dto.Categories;

/**
 *
 * @author HNC
 */
public class manageCategoriesServlet extends HttpServlet {

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
            ArrayList<Categories> cateList = CategoriesDAO.getAllCate();
            request.setAttribute("listCate", cateList);
            HttpSession session = request.getSession();
            String newCateName = request.getParameter("newCateName");
            String newCateNameUpdate = request.getParameter("newCateNameUpdate");
            int cateID = 0;
            if (request.getParameter("CateID") != null) {
                cateID = (int) Integer.parseInt(request.getParameter("CateID"));
            }
            Account acc = (Account) session.getAttribute("acc");
            if (acc.getRole() == 1) {
                if (newCateName != null) {
                    if (CategoriesDAO.insertCategories(newCateName)) {
                        request.setAttribute("message", "Create Successfull");
                    } else {
                        request.setAttribute("message", "Create failed");
                    }
                }
                if (newCateNameUpdate != null && request.getParameter("CateID") != null) {
                    if (CategoriesDAO.updateCategories(newCateNameUpdate, cateID)) {
                        request.setAttribute("message", "Update Successfull");
                    } else {
                        request.setAttribute("message", "Update failed");
                    }
                }
                request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "You must login admin page");

                request.getRequestDispatcher("manageCategories.jsp").forward(request, response);

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
