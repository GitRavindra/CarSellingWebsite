package project;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/View")
public class View extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			try {con=Connect.getConnect();
			String speci=request.getParameter("speci");
			String img=request.getParameter("img");
			String buy=request.getParameter("buy");
			HttpSession session=request.getSession();
			if(buy!=null)
			{

				session.setAttribute("brand",request.getParameter("s2"));
				session.setAttribute("model",request.getParameter("s"));
			}
		    
		    String log=(String) session.getAttribute("log");
	   if(speci!=null)
	   {	 
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
           // pw.println(session.getAttribute("but"));
			String brand=request.getParameter("s2");
			String model=request.getParameter("s");
		    // pw.println(brand+model);
		    String sql="select ENGINE_TYPE,ENGINE_DISPLACEMENT,FUEL_TYPE,POWER, TORQUE ,SPEED_TRANSMISSION ,NO_OF_CYLINDER ,SUSPENSION ,NO_OF_GEARS ,FUEL_CAPACITY  from car where brand=? and model_name=?";
		    st=con.prepareStatement(sql);
		    st.setString(1,brand);
		    st.setString(2,model);
	        rs=st.executeQuery(); 
		    pw.println("<html>");
		    pw.println("<head>");
		    pw.println("<title>Specification</title>");
		    pw.println("<link rel=\"stylesheet\" href=\"specification.css\">");
			pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
			pw.println("</script>");
		    pw.println("</head>");
		    pw.println("<body>");
		    pw.println(" <form class =\"form-wrap\" name='f1'>");
		    pw.println("<center> <h1>Specification</h1> </center>\r\n" + 
		    		"Engine Type                   &nbsp &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp &nbsp &nbsp             :  &nbsp &nbsp &nbsp;\n" + 
		    		"                 <strong><label id=\"label1\">label1</label></strong></br></br>\n" + 
		    		"Engine displacement     &nbsp&nbsp&nbsp  : &nbsp &nbsp &nbsp;\n" + 
		    		"                 <strong><label id=\"label2\">label2</label></strong></br></br>\n" + 
		    		"Fuel Type        &nbsp&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp&nbsp:   &nbsp&nbsp &ensp;\n" + 
		    		"                  <strong><label id=\"label3\">label3</label></strong></br></br>\r\n" + 
		    		"              Power          &nbsp &nbsp &nbsp &nbsp&nbsp&nbsp&nbsp&nbsp &nbsp &nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp:  &nbsp&nbsp &ensp;\r\n" + 
		    		"                   <strong><label id=\"label4\">label4</label></strong></br></br>\r\n" + 
		    		"            Torque   &nbsp&nbsp&nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  :&nbsp&nbsp&nbsp&nbsp &nbsp;\r\n" + 
		    		"                   <strong><label id=\"label5\">label5</label></strong></br></br>\r\n" + 
		    		"             Speed Transmission           &nbsp&nbsp &nbsp   :  &nbsp&nbsp &ensp;\r\n" + 
		    		"                  <strong><label id=\"label6\">label6</label></strong></br></br>\r\n" + 
		    		"             No. of Cylinders                &nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp     :   &nbsp &nbsp &nbsp;\r\n" + 
		    		"                    <strong><label id=\"label7\">label7</label></strong></br></br>\r\n" + 
		    		"             Suspension Front      &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp           :&nbsp &nbsp &ensp;\r\n" + 
		    		"                     <strong><label id=\"label8\">label8</label></strong></br></br>\r\n" + 
		    		"             Gear            &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp:&nbsp &nbsp &ensp;\r\n" + 
		    		"                      <strong><label id=\"label9\">label9</label></strong></br></br>\r\n" + 
		    		"             Fuel Capacity &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp &nbsp &nbsp: &nbsp &nbsp &nbsp;\r\n" + 
		    		"                       <strong><label id=\"label10\">label10</label></strong></br></br>");
		  //  pw.println("<div><input type=\"button\" onclick=\"goBack()\" value=\"Ok\" name=\"b1\" ></div>");
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
	  if(buy!=null || log!=null)
	  {
		   response.setContentType("text/html");
		   PrintWriter pw=response.getWriter();
		  String user=(String)session.getAttribute("user");
			if(user==null)
			{
				pw.println("<html>");
				pw.println("<head><script>alert('Firstly login/signup before Buying a car');</script>");
				pw.println("<body> ");
				pw.println("</body></html>");	
				session.setAttribute("log", "log");
		        RequestDispatcher rd= request.getRequestDispatcher("Login.html");
			    rd.include(request, response);
			}
			else 
			{
				if(log!=null)
					session.removeAttribute("log");
		        RequestDispatcher rd= request.getRequestDispatcher("Address");
			    rd.forward(request, response);
				
			}
	  }
	  if(img!=null)
	  {
		  Blob b=null;
		  ServletOutputStream sos = response.getOutputStream();
		  String brand=request.getParameter("s2");
		  String model=request.getParameter("s");
		  String sql="select image from car where brand='"+brand+"' and model_name='"+model+"'";
          st=con.prepareStatement(sql);
          rs=st.executeQuery();  
          response.setContentType("image/jpg");
 		  if(rs.next())
 		  {
 					b=rs.getBlob(1); 
 					InputStream in = b.getBinaryStream();
 					int length = (int) b.length();
 					int bufferSize = 1024;
 					byte[] buffer = new byte[bufferSize];
 					while ((length = in.read(buffer)) != -1) {
 					sos .write(buffer, 0, length);           }
 		  } 
	  }
			}
			catch (Exception e) 
			  {
				e.printStackTrace();  
			   }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
