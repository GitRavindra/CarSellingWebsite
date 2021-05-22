package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/View2")
public class View2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
	    String brand=request.getParameter("brand");
	     int count=0;
	     float []a;
	  try 
	  { 
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		response.setContentType("text/html");
		con=Connect.getConnect();
	    String sql="select model_name,price from car where brand=?  order by price asc";
	    String sql1="select count(*)  from car where brand=? ";
	    st=con.prepareStatement(sql1);
	    st.setString(1,brand); 
	    rs=st.executeQuery();
	    if(rs.next())
	    {  
	    	count=rs.getInt(1);  
	    }
	    st=con.prepareStatement(sql);
	    st.setString(1,brand); 
	    rs=st.executeQuery();
	    a=new float[count];
	   // pw.println(count+r1);
	    pw.println("<html >\r\n" + 
	    		"   <head>\r\n" + 
	    		"     <meta charset=\"utf-8\">\r\n" + 
	    		"      <title>Car Model</title>\r\n" + 
	    		"       <link rel=\"stylesheet\" href=\"view2.css\">");
		pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
		pw.println("</script>");
	    pw.println("</head");
	    pw.println("<body>");
	    pw.println("<a href=\"Front\"> <img src=\"images/icons8-home-24.png\" width=\"60px\"/> <br> &nbsp; Home</a>");
		pw.println(" <div class= \"form-wrap\">\r\n" + 
	    		"        <form action='View' name='f1' method='post' target='_blank'>");
		pw.println(	"<p1>Select Model: </p1> &ensp;"); 
		pw.println("<strong><label id='l' style='float:right; '></label></strong> <br> "); 
	    pw.println("<select name=\"s\">");
	  for(int i=0;rs.next();i++)
	    {  
	       a[i]=rs.getFloat(2);
	    pw.println("<option");
	    pw.println("value='"+rs.getString(1)+"'");
        pw.println(">");
        pw.println(rs.getString(1));
        pw.println("</option>");
       }
	  pw.println("</select> <br>");
	  pw.println("<input type=\"button\"  style='margin-left:0' onclick=\"view2()\"  value=\"Okk\"  ><br>\r\n" + 
	  		"			    <p>---------------------------------------------------or-----------------------------------------------------</p>\r\n" + 
	  		"			   <p>Car Model: </p>\r\n" + 
	  		"		       <input type=\"text\" name=\"t1\" placeholder=\"Enter Car Model\" disabled>\r\n" + 
	  	    "			<input type=\"submit\" value=\"View Specification\" name=\"speci\" >\r\n" + 
	  		"			   <p>Price: </p>\r\n" + 
	  		"		       <input type=\"text\" name=\"t2\" placeholder=\"price\" disabled>\r\n" + 
	  		"            \r\n" + 
	  		"             <input type=\"submit\"  value=\"View Image\" name=\"img\" ><br>\r\n" + 
	  		//"        <a href=\"ShowImage\">   <input type=\"button\"  value='View Image'> </a> <br>\r\n"  +
	  		"             <input type=\"button\"  onclick=\"back()\" value=\"<<\" id=\"b4\" >\r\n" + 
	  		"             <input type=\"submit\"  value=\"Buy Now\" name=\"buy\" >\r\n" + 
	  		"             <input type=\"button\"  onclick=\"next()\" value=\">>\" id=\"b6\" >");
	  pw.println("<label  id=\"l1\">hii</label>");
	  pw.println("<select name=\"s1\">");
	  for(int i=0;i<count;i++)
	  {	  
		  pw.println("<option");
		    pw.println("value="+a[i]+"");
	        pw.println(">");
	        pw.println(a[i]);
	        pw.println("</option>");
	  }
   	  pw.println("</select>");
      pw.println("<select name=\"s2\">");
	  pw.println("<option value='"+brand+"'>");
	  pw.println(brand);
	  pw.println("</select>");
		pw.println("</form>");
		/*pw.println("<form action=\"Specification\" onclick='return a()' name='f2'><input type=\"submit\" value=\"View Specification\" > " );
		pw.println("<input type=\"text\" name=\"t1\">");
		pw.println("<select name=\"s\">");
		pw.println("<option value='"+r1+"'>");
		pw.println(r1);
		pw.println("</select>");
		pw.println("</form");*/
		pw.println("</div>");
	    pw.println("<script>");
	    pw.println("f1.s1.style.display = 'none'");
	    pw.println("f1.s2.style.display = 'none'");
	   /* pw.println("f2.s.style.display = 'none'");
	    pw.println("f2.t1.style.display = 'none'");*/
	    pw.println("alert(f1.s.options.length+' models found');");
		pw.println("view2();");
		pw.println("</script>");

		pw.println("</body>");
		pw.println("</html>");
		}
		catch (ClassNotFoundException | SQLException e) 
		  {
			e.printStackTrace();  pw.println(e);
		   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
