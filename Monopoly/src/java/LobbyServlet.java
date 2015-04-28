/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robertmoon
 */
@WebServlet(urlPatterns = {"/LobbyServlet"})
public class LobbyServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LobbyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LobbyServlet at " + request.getContextPath() + "</h1>");
            out.println("establishing socket connection with server!");
            try (Socket s = new Socket("localhost", 8189))
            {
                InputStream inStream = s.getInputStream();
                OutputStream outStream = s.getOutputStream();
                PrintWriter newOut = new PrintWriter(outStream,true);
                newOut.println("Hello from Client!");
                
              BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
                    String line = in.readLine();

                    System.out.println(line);
                
                    inStream.close();
                    outStream.close();
                    newOut.close();
                    s.close();
                    /*    System.out.println("Server has connected!\n");
              InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream();
            BufferedReader inB = new BufferedReader(new InputStreamReader(inStream));
                   //Scanner in = new Scanner(inStream);
                   String line = inB.readLine();
                   System.out.print(line);
                 PrintWriter out = new PrintWriter(outStream, true);
                 out.println("Hello from Server!");
                 incoming.close();
                 outStream.close();
                 inStream.close();
                 inB.close();*/
            }
            out.println("</body>");
            out.println("</html>");
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
