/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.OrderDAO;
import sample.dao.PlantDAO;
import sample.dto.Account;
import sample.dto.Plant;

/**
 *
 * @author HNC
 */
public class updatePlantServlet extends HttpServlet {

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
            String namePlant = request.getParameter("newName");
             int price = Integer.parseInt(request.getParameter("newPrice"));
             String imgpath = request.getParameter("imgpath");
             String description = request.getParameter("newDescription:");
             String catename = request.getParameter("newategory");
             int status = Integer.parseInt(request.getParameter("newStatus"));
             int cateid = OrderDAO.getCateId(catename);
             int id = Integer.parseInt(request.getParameter("PID"));
             HttpSession session = request.getSession();
             Account acc = (Account) session.getAttribute("acc");
             if (acc!=null&&acc.getRole()==1) {
                Plant plant = new Plant(id, namePlant, price, imgpath, description, status, cateid, catename);
                 if (PlantDAO.updatePlant(plant)) {
                     request.setAttribute("message", "Update successfull");
                     request.getRequestDispatcher("managePlant.jsp").forward(request, response);
                 } else {
                     request.setAttribute("message", "Update failed");
                     request.getRequestDispatcher("managePlant.jsp").forward(request, response);
                
                 }
            } else {
                 request.setAttribute("message", "you must login admin page befor Update new plant");
                 request.getRequestDispatcher("managePlant.jsp").forward(request, response);
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
