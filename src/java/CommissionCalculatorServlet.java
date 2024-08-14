/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns={"/NewServlet11"})
public class CommissionCalculatorServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommissionCalculatorServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommissionCalculatorServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeType = request.getParameter("employeeType");
        String itemType = request.getParameter("itemType");
        String customerType = request.getParameter("customerType");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
        double commission = 0;
if (itemType.equals("Unknown")) {
                commission=0;
            }else if (employeeType.equals("Non-salaried")) {
            if (itemType.equals("")) {
                commission=0;
            }
            if (itemType.equals("Standard")) {
                commission = 0;
            } else if (itemType.equals("General")) {
                if (itemPrice > 10000) {
                    commission = 0.05 * itemPrice;
                } else {
                    commission = 0.1 * itemPrice;
                }
            } else if (itemType.equals("Bonus")) {
                if (customerType.equals("Regular")) {
                    commission = 0;
                } else if (itemPrice > 1000) {
                    commission = 75;
                } else {
                    commission = 0.1 * itemPrice;
                }
            }
        } else if (employeeType.equals("Salaried")) {
            if (itemType.equals("Standard") || customerType.equals("Regular")) {
                commission = 0;
            } else if (itemType.equals("General")) {
                commission = 0;
            } else if (itemType.equals("Bonus")) {
                if (itemPrice > 1000) {
                    commission = 25;
                } else {
                    commission = 0.05 * itemPrice;
                }
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Commission Result</h2>");
        response.getWriter().println("Commission: $" + commission);
        response.getWriter().println("</body></html>");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}