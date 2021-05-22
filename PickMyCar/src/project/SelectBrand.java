package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SelectBrand")
public class SelectBrand extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 PrintWriter pw=response.getWriter();
	     Float r1=Float.parseFloat(request.getParameter("min")) ;
	     Float r2=Float.parseFloat(request.getParameter("max")) ;

	     HttpSession session=request.getSession();  
	     session.setAttribute("but",r1);  
	   // pw.println(r1+""+r2);
		 Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			try {con=Connect.getConnect();
		    String sql="select distinct brand from car where price between ? and ? ";
		    st=con.prepareStatement(sql);
		    st.setFloat(1,r1); st.setFloat(2,r2);
		    rs=st.executeQuery();
		    if(!rs.next())
		    {
		    	throw new SQLException();
		    }
		    rs.close(); 
		    rs=st.executeQuery();
		    response.setContentType("text/html");
		    pw.println("<html>\r\n" + 
		    		"  <head>\r\n" + 
		    		"     <title>Select Brand</title>\r\n" + 
		    		"     <link rel=\"stylesheet\" href=\"selectBrand.css\">");
		    pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
			pw.println("</script>");
		    pw.println("</head");
		    pw.println("<body>");
		    pw.println("<a href=\"Front\"> <img src=\"images/icons8-home-24.png\" width=\"60px\"/> <br> &nbsp;Home</a>");
		    pw.println("<h1>Select Car Brands</h1>");
		    pw.println("<div id=\"first\" class=\"form-wrap\">\r\n" + 
		    		"         <form action='View2' name='f1'>\r\n" + 
		    		"           <tr>\r\n" + 
		    		"              <td>");
		    pw.println("<select name='brand'>");
		   while(rs.next())
		    {  
		    pw.println("<option");
		    pw.println("value='"+rs.getString(1)+"'");
	        pw.println(">");
	        pw.println(rs.getString(1));
	        pw.println("</option>");
	       }
		    pw.println("");
		    pw.println(" </select>\r\n" + 
		    		"             </td>\r\n" + 
		    		"           </tr>\r\n" + 
		    		"           <br><br>");
		    
		    pw.println("<input type=\"button\" onclick=\"goBack()\" value=\"Back\"  >\r\n" + 
		    		"              <input type= \"submit\"  value=\"Next\" > <br>");
		    pw.println("</form>");
		    pw.println("</body>");
			pw.println("</html>");
			}
			catch (ClassNotFoundException e) 
			  {
				e.printStackTrace();  pw.println(e);
			   }
			catch(SQLException e) 
			  {
				response.setContentType("text/html");
				//pw.print(e);
				pw.println("<html>");
				pw.println("<head><script>alert('car in this range not exists');</script>");
				pw.println("<body> ");
				pw.println("</body></html>");	
		   RequestDispatcher rd= request.getRequestDispatcher("view1.html");
			rd.include(request, response);
			   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
