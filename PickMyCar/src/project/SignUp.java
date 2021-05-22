package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		Statement st=null;
		PrintWriter pw=response.getWriter();
		try {con=Connect.getConnect();
		     String sql="insert into customer values('c'||cid.nextval,'"+request.getParameter("textbox1")+"',"+Double.parseDouble(request.getParameter("textbox2"))+",'"+request.getParameter("textbox3")+"','"+request.getParameter("textbox4")+"','"+request.getParameter("textbox6")+"')";
		     st=con.createStatement();
	        st.executeUpdate(sql);	
	        response.setContentType("text/html");
			pw.println("<html>");
			pw.println("<body> ");
			pw.println("<script>alert('You are successfully registered!');</script>");
			pw.println("</body></html>");
			RequestDispatcher rd= request.getRequestDispatcher("Login.html");
			rd.include(request, response);
			}
		catch (ClassNotFoundException  e) {
			e.printStackTrace();
			pw.print(e);
		}
		catch(SQLException e)
		{
			//pw.println(e);
			response.setContentType("text/html");
			pw.println("<html>");
			pw.println("<body> ");
			pw.println("<script>alert('Mobile No or Email Id already exists');</script>");
			pw.println("</body></html>");	
			RequestDispatcher rd= request.getRequestDispatcher("SignUp.html");
			rd.include(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
