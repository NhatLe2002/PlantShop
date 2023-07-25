package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import sample.dao.AccountDAO;
import sample.dto.Account;
import sample.dto.Order;
import java.util.ArrayList;
import sample.dao.OrderDAO;

public final class personalPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/header_loginedUser.jsp");
    _jspx_dependants.add("/footer.jsp");
    _jspx_dependants.add("/header.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"mycss.css\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");

            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie cookie : c) {
                    if (cookie.getName().equals("selector")) {
                        token = cookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
            }

            if (login) {
        
      out.write("\n");
      out.write("\n");
      out.write("        <header>\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"mycss.css\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav>\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                <li><a href=\"mainController?action=changeProfile\">Change profile</a></li>\n");
      out.write("                <li><a href=\"mainController?action=completedOrders\">Completed orders</a></li>\n");
      out.write("                <li><a href=\"mainController?action=canceledOrder\">Canceled order</a></li>\n");
      out.write("                <li><a href=\"mainController?action=processingOrder\">Processing orders</a></li>\n");
      out.write("                <form action=\"mainController\" method=\"post\">\n");
      out.write("                    <li>from <input type=\"date\" name=\"from\"> to <input type=\"date\" name=\"to\" required=\"\">\n");
      out.write("                        <input type=\"submit\" value=\"searchOrderByDay\" name=\"action\" >\n");
      out.write("                    </li>\n");
      out.write("                </form>\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        </header>\n");
      out.write("        <section>\n");
      out.write("            <h3>Welcome ");
      out.print( name);
      out.write(" come back </h3>\n");
      out.write("            <h3><a href=\"mainController?action=logout\">Logout</a></h3>\n");
      out.write("        </section>\n");
      out.write("        <section id=\"tableOfOder\"> <!-- load all order of the user at here-->\n");
      out.write("            ");

                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                if (list != null && !list.isEmpty()) {
                    LocalDate dateFrom = LocalDate.parse("2021-09-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate dateTo = LocalDate.parse("2022-09-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate datecheck = null;
                    for (Order ord : list) {
                        if (dateFrom != null && dateTo != null) {
                            datecheck = LocalDate.parse(ord.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (datecheck.isAfter(dateFrom) && datecheck.isBefore(dateTo)) {
            
      out.write("\n");
      out.write("            <table class=\"order\" style=\"\">\n");
      out.write("                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>\n");
      out.write("                <tr><td>");
      out.print( ord.getOrderID());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( ord.getOrderDate());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( ord.getShipDate());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( status[ord.getStatus()]);
      out.write("\n");
      out.write("                        <br/>");
 if (ord.getStatus() == 1)
      out.write("<a href=\"#\">cancel order</a>\n");
      out.write("                    </td>\n");
      out.write("                    <td><a href=\"orderDetail.jsp?orderid=");
      out.print( ord.getOrderID() );
      out.write("\">detail</a></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>        \n");
      out.write("                            ");

                                                    } 
                            } else {
                            
      out.write("\n");
      out.write("            <table class=\"order\" style=\"\">\n");
      out.write("                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>\n");
      out.write("                <tr><td>");
      out.print( ord.getOrderID());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( ord.getOrderDate());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( ord.getShipDate());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( status[ord.getStatus()]);
      out.write("\n");
      out.write("                        <br/>");
 if (ord.getStatus() == 1)
      out.write("<a href=\"#\">cancel order</a>\n");
      out.write("                    </td>\n");
      out.write("                    <td><a href=\"orderDetail.jsp?orderid=");
      out.print( ord.getOrderID() );
      out.write("\">detail</a></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("            ");
}

                            }
            
                    }else{ 
      out.write("\n");
      out.write("            <p>You don't have any order</p>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </section>\n");
      out.write("        <footer>\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\"href=\"mycss.css\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <p style=\"color: grey; text-align: center;\">Copyright &copy;2021</p>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        </footer>\n");
      out.write("        ");
}else{
                
      out.write("\n");
      out.write("        <header>\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>TODO supply a title</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"wihth=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"mycss.css\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <nav>\n");
      out.write("                <li><a href=\"\"><img src=\"images/logo.jpg\" id=\"logo\"></a></li>\n");
      out.write("                <li><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                <li><a href=\"registration.jsp\">Register</a></li>\n");
      out.write("                <li><a href=\"login.jsp\">Login</a></li>\n");
      out.write("                <li><a href=\"mainController?action=viewcart\">View Cart</a></li>\n");
      out.write("                <li>\n");
      out.write("                    <form action=\"mainController\" method=\"post\" class=\"formsearch\">\n");
      out.write("                        <input type=\"text\" name=\"txtsearch\" value=\"");
      out.print( (request.getParameter("txtsearch")==null)?"": request.getParameter("txtsearch"));
      out.write("\">\n");
      out.write("                        <select name=\"searchby\"><option value=\"byname\">by name</option>\n");
      out.write("                            <option value=\"bycate\">by category</option>\n");
      out.write("                        </select>\n");
      out.write("                        <input type=\"submit\" value=\"search\" name=\"action\">\n");
      out.write("                    </form>\n");
      out.write("                </li>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        </header>\n");
      out.write("        <section>\n");
      out.write("            <P><font color=\"red\" >you must login to view personal page</font></P>\n");
      out.write("        </section>\n");
      out.write("        <footer>\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\"href=\"mycss.css\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <p style=\"color: grey; text-align: center;\">Copyright &copy;2021</p>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        </footer>\n");
      out.write("        ");

            }
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
