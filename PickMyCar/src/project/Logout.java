package project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");//HTTP 1.1
	     response.setHeader("Pragma", "no-cache");// HTTP 1.0
        response.setHeader("Expires","0");//Proxy
		response.setContentType("text/html");  
        PrintWriter pw=response.getWriter();  
        HttpSession session=request.getSession();  
        session.removeAttribute("user");
        session.removeAttribute("cid"); 
        session.invalidate();  
        request.getRequestDispatcher("Front").include(request, response); 
        pw.println("<html>");
	    pw.println("<head>");
	    pw.println("<title>Logout</title>");
	  //  pw.println("<link rel=\"stylesheet\" href=\"specification.css\">");
		//pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
		//pw.println("</script>");
	    pw.println("</head>");
	    pw.println("<body>");  
        pw.print("<script>alert('You are successfully logged out!')</script>");  
		pw.println("</body>");
		pw.println("</html>");
        pw.close();  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
