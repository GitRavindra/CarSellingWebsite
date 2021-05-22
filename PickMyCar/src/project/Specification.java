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

@WebServlet("/Specification")
public class Specification extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 PrintWriter pw=response.getWriter();
			/*response.setContentType("text/html");
		    pw.println("<html>");
			pw.println("<body>");
		    pw.println("<script type='text\\javascript'>");
		    pw.println("document.getElementById('l2').innerHTML="+model+";");
			pw.println("document.getElementById('t1').value='hellos';");
			pw.println("</script>");
			pw.println(" <form name='f1'>");
	        pw.println("<input type=\"text\" id=\"t1\">");
			pw.println("<label id=\"l2\">kk</label>");
			pw.println("</form");
			pw.println("</body>");
			pw.println("</html>");*/
		  Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			try {con=Connect.getConnect();

			 String model=request.getParameter("t1");
		     String brand=request.getParameter("s") ;
		     pw.println(model+brand);
		    String sql="select ENGINE_TYPE,ENGINE_DISPLACEMENT,FUEL_TYPE,POWER, TORQUE ,SPEED_TRANSMISSION ,NO_OF_CYLINDER ,SUSPENSION ,NO_OF_GEARS ,FUEL_CAPACITY  from car where brand=? and model_name=?";
		    st=con.prepareStatement(sql);
		    st.setString(1,brand);
		    st.setString(2,model);
	        rs=st.executeQuery(); response.setContentType("text/html");
		    pw.println("<html>");
		    pw.println("<head>");
		    pw.println("<title>Specification</title>");
		    pw.println("<link rel=\"stylesheet\" href=\"specification.css\">");
			pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
			pw.println("</script>");
		    pw.println("</head>");
		    pw.println("<body>");
		    pw.println(" <form class =\"form-wrap\" name='f1'>");
		    pw.println("<center> <h1>Specification </h1> </center>\r\n" + 
		    		"                Engine type: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp; &ensp;\r\n" + 
		    		"                 <input type=\"text\" id=\"textbox1\" disabled><br>\r\n" + 
		    		"                 Engine displacement: &nbsp;\r\n" + 
		    		"                 <input type=\"text\" id=\"textbox2\" disabled><br>\r\n" + 
		    		"               Fuel Type: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &ensp;\r\n" + 
		    		"                  <input type=\"text\" id=\"textbox3\" disabled><br>\r\n" + 
		    		"              Power: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &ensp;\r\n" + 
		    		"                   <input type=\"text\" id=\"textbox4\" disabled><br>\r\n" + 
		    		"            Torque: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp;\r\n" + 
		    		"                   <input type=\"text\" id=\"textbox5\" disabled><br>\r\n" + 
		    		"             Speed Transmission: &ensp;\r\n" + 
		    		"                    <input type=\"text\" id=\"textbox6\" disabled></br>\r\n" + 
		    		"             No. of Cylinders: &nbsp &nbsp &nbsp &nbsp;\r\n" + 
		    		"                    <input type=\"text\" id=\"textbox7\" disabled></br>\r\n" + 
		    		"             Suspension Front: &nbsp &nbsp &ensp;\r\n" + 
		    		"                     <input type=\"text\" id=\"textbox8\" disabled></br>\r\n" + 
		    		"             No. of Gear: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp;\r\n" + 
		    		"                      <input type=\"text\" id=\"textbox9\" disabled></br>\r\n" + 
		    		"             Fuel Capacity: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp;\r\n" + 
		    		"                       <input type=\"text\" id=\"textbox10\" disabled><br>");
		    pw.println("<input type=\"buttton\" onclick=\"goBack()\" value=\"Back\" name=\"b1\" >\r\n" + 
		    		"                <input type=\"submit\"  value=\"Buy Now\" name=\"b2\"> <br>");
		    pw.println("<select name=\"s\"> ");
	        if(rs.next())
	        {
	  			for(int i=1;i<=10;i++)
	    		{  
	    			pw.println("<option");
	    			pw.println("value='"+rs.getString(i)+"'");
        			pw.println(">");
        			pw.println(rs.getString(i));
        			pw.println("</option>");
       			}
	        }
	        pw.println("</select> <br>");
	        pw.println("<script>");
			pw.println("specification();");
			pw.println("</script>");
			pw.println("</form");
			pw.println("</body>");
			pw.println("</html>");
			}
			catch (Exception e) 
			  {
				e.printStackTrace();  pw.println(e);
			   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
