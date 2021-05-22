package project;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddAddress
 */
@WebServlet("/AddAddress")
public class AddAddress extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button=request.getParameter("change");
		response.setContentType("text/html");
	    PrintWriter pw=response.getWriter();
	     response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");//HTTP 1.1
	     response.setHeader("Pragma", "no-cache");// HTTP 1.0
	    response.setHeader("Expires","0");//Proxy
	    pw.println("<!DOCTYPE html>\r\n" + 
	    		"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"<title>Add Address</title>\r\n" + 
	    		" <link rel=\"stylesheet\" href=\"addAddress.css\" />\r\n" + 
	    		" <script type=\"text/javascript\" src=\"Login.js\"> </script>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"<div class=\"contact-head\">\r\n" + 
	    		"<h1>Enter Your address</h1>\r\n" + 
	    		"</div>\r\n" + 
	    		"<div class=\"contact-form\">\r\n" + 
	    		"<form  id=\"contact-form\"  action=\"Address\" name=\"f1\" onsubmit=\"return addAddress()\">\r\n" + 
	    		" <input name=\"State\" type=\"text\" class=\"form-control\" onkeyup=\"inputsp(this)\" placeholder=\"State\"  required>\r\n" + 
	    		"<br>\r\n" + 
	    		"<input name=\"City\" type=\"text\" class=\"form-control\" onkeyup=\"inputs(this)\" placeholder=\"City\"  required>\r\n" + 
	    		"<br>\r\n" + 
	    		"<input name=\"Zipcode\" type=\"number\" class=\"form-control\" placeholder=\"Pincode\"  required>\r\n" + 
	    		"<br>\r\n" + 
	    		"<input name=\"House\" type=\"text\" class=\"form-control\" placeholder=\"flat/Building/House No\"  required>\r\n" + 
	    		"<br>\r\n" + 
	    		"<input name=\"Street\" type=\"text\" class=\"form-control\" onkeyup=\"inputsp(this)\" placeholder=\"Street/Colony\"  required>\r\n" + 
	    		"<br>\r\n" + 
	    		"<input name=\"Landmark\" type=\"text\" class=\"form-control\" onkeyup=\"inputsp(this)\" placeholder=\"Landmark\"  required>\r\n" + 
	    		"<br>\r\n" + 
	    		"<input name=\"Country\" type=\"text\" class=\"form-control\" placeholder=\"Country\" value=\"India\"  disabled>\r\n" + 
	    		"<br>\r\n" + 
	    		"<br>\r\n" + 
	    		"</form>");
	    if(button==null)
		   pw.println("<button type=\"submit\" form=\"contact-form\" name=\"ok\" value=\"ok\">Ok</button>");
	    else
		   pw.println("<button type=\"submit\" form=\"contact-form\" name=\"update\" value=\"update\">Update</button>");
		pw.println("&ensp;&ensp;&ensp;&ensp;");
		pw.println("<button type=\"button\" onclick=\"goBack()\" form=\"contact-form\" >Cancel</button> ");
		pw.println("</div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
