/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.OrderDAO;
import sample.dao.PlantDAO;
import sample.dto.Account;
import sample.dto.Order;
import sample.dto.Plant;
import sample.utils.DBUtils;

/**
 *
 * @author HNC
 */
public class createPlantServlet extends HttpServlet {

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
             String namePlant = request.getParameter("name");
             int price = Integer.parseInt(request.getParameter("price"));
             String imgpath = request.getParameter("image");
             String description = request.getParameter("description");
             String catename = request.getParameter("category");
             int cateid = OrderDAO.getCateId(catename);
             int id = 10;
             HttpSession session = request.getSession();
             Account acc = (Account) session.getAttribute("acc");
             if (acc!=null&&acc.getRole()==1) {
                Plant plant = new Plant(id, namePlant, price, imgpath, description, 1, cateid, catename);
                 if (PlantDAO.insertPlant(plant)) {
                     request.setAttribute("message", "Create successfull");
                     request.getRequestDispatcher("createPlant.jsp").forward(request, response);
                 } else {
                     request.setAttribute("message", "Create failed");
                     request.getRequestDispatcher("createPlant.jsp").forward(request, response);
                
                 }
            } else {
                 request.setAttribute("message", "you must login admin page befor create new plant");
                 request.getRequestDispatcher("createPlant.jsp").forward(request, response);
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
