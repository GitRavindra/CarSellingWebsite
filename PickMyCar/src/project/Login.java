package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;


@WebServlet("/Login")
public class Login extends HttpServlet {
	/*public boolean check(String i,String j,PrintWriter pw)
	{
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		String temp=null;
			try {con=Connect.getConnect();
			    String sql="select password  from customer where email=?";
			    st=con.prepareStatement(sql);
			    st.setString(1,""+i+"");
			    rs=st.executeQuery();
			    if(rs.next())
			    {    temp=rs.getString(1);}
			 
			   pw.print(j+temp+(j==temp));
			  /*  rs.close();
			    st.close();
			    con.close(); 
			    if(j.equals(temp))
			    	return true;
			    else
			       return false;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				pw.print(e);
		       return false;
			}
		
	}*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
	     String i=request.getParameter("textbox1")  ;
	     String j=request.getParameter("textbox2")  ;
	     response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");//HTTP 1.1
	     response.setHeader("Pragma", "no-cache");// HTTP 1.0
	     response.setHeader("Expires","0");//Proxy
			response.setContentType("text/html");
			Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			String temp=null;
				try {con=Connect.getConnect();
				    String sql="select cid  from customer where email=? and password=?";
				    st=con.prepareStatement(sql);
				    st.setString(1,""+i+""); st.setString(2,""+j+"");
				    rs=st.executeQuery();
				    if(rs.next())
				    {  temp=rs.getString(1);}
				    else
				    	throw new SQLException();
				    HttpSession session=request.getSession();  
			        session.setAttribute("user",i);  
			        session.setAttribute("cid",temp); 	
			        response.setContentType("text/html");
					pw.println("<html>");
					pw.println("<body> ");
					pw.println("<script>alert('Login Succesful!');</script>");
					pw.println("</body></html>");
				    String log=(String) session.getAttribute("log");
					if(log!=null)
					{
						RequestDispatcher rd= request.getRequestDispatcher("View");
						rd.forward(request, response);
					}
					else
					{	
				    RequestDispatcher rd= request.getRequestDispatcher("Front");
					rd.include(request, response);
					}
				}
			catch (ClassNotFoundException  e) {
				e.printStackTrace();
				pw.print(e);
			}
			catch(SQLException e)
			{
				 response.setContentType("text/html");
					pw.println("<html>");
					pw.println("<body> ");
					pw.println("<script>alert('data not exists');</script>");
					pw.println("</body></html>");	
			   RequestDispatcher rd= request.getRequestDispatcher("Login.html");
				rd.include(request, response); 
			}
			/*if(check(i,j,pw))
			{   HttpSession session=request.getSession();  
		        session.setAttribute("user",i);  
			    RequestDispatcher rd= request.getRequestDispatcher("Front");
				rd.forward(request, response);
			}
			else
			{
				   response.setContentType("text/html");
					pw.println("<html>");
					pw.println("<body> ");
					pw.println("<script>alert('data not exists');</script>");
					pw.println("</body></html>");	
			   RequestDispatcher rd= request.getRequestDispatcher("Login.html");
				//response.getWriter().println("already exists");
				rd.include(request, response); 
				//response.sendRedirect("webconnect.html");
			  }
				/*	String site = new String("webconnect.html");
				   response.setStatus(response.SC_MOVED_TEMPORARILY);
				    response.setHeader("Location", site); */
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
