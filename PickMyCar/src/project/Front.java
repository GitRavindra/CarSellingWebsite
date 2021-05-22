package project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Front")
public class Front extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String user=(String)session.getAttribute("user");
		String carid=(String)session.getAttribute("carid");
		String brand=(String)session.getAttribute("brand");
		String log=(String)session.getAttribute("log");
		if(carid!=null)
		{
			session.removeAttribute("carName"); 
			session.removeAttribute("time");
			session.removeAttribute("date");
			session.removeAttribute("pay");
			session.removeAttribute("carid");
		}
		if(brand!=null)
		{
			session.removeAttribute("brand");
			session.removeAttribute("model");
		}
		if(log!=null)
		{
		 session.removeAttribute("log");
		}
		response.setContentType("text/html");
	    PrintWriter pw=response.getWriter();
	    //pw.println(user);
			pw.println("<!doctype html>\r\n" + 
					"			 <html>\r\n" + 
					"			  <head>");
			pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"front.css\" >");
			pw.println("</head>\r\n" + 
					"			  <body> \r\n" + 
					"			  <div> \r\n" + 
					"			  <ul>\r\n" + 
					"			    <li class=\"c1\">");
		    pw.println("<a href=\"#\">\r\n" + 
		    		"     <img src=\"images/hamburger_menu_158917.png\" width=\"60px\"/> <br>\r\n" + 
		    		"        Menu</a>");		
			pw.println(" <ul class=\"menu\">\r\n" + 
					"					   <li> <a href=\"view1.html\">View Car</a> </li>\r\n" + 
					"					   <li><a href=\"CompareCar\">Compare Car </a> </li>\r\n" + 
					"					   <li><a href=\"TestDrive\">Test Drive </a> </li>");
			if(user==null)
			pw.println(" <li> <a href=\"Login.html\">Login/Sign Up </a> </li>");
			if(user!=null)
			    pw.println("<li><a href=\"Address\">Your Address </a> </li>");
			pw.println(" <li><a href=\"help.html\">Help </a> </li>"+
					"					   <li> <a href=\"aboutUs.html\">About Us</a> </li>");
			if(user!=null)
				pw.println("<li><a href=\"Logout\">Logout </a> </li>");
			pw.println("");
			pw.println("</ul>\r\n" + 
					"					</li>");
			pw.println("<li><a href=\"#\"><img src=\"images/icons8-home-24.png\" width=\"60px\"/></br>Home </a></li>");
			pw.println("  </ul>	"); 
			pw.println(" </div>");
			pw.println("<div class=\"w\"> \r\n" + 
					"			     Welcome to <b> PickMyCar </b> - Purchase a new car with discount\r\n" + 
					"			    </div> ");
			pw.println("  </body> \r\n" + 
					"			 <html>");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
