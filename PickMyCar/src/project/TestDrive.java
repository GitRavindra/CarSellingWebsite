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

/**
 * Servlet implementation class TestDrive
 */
@WebServlet("/TestDrive")
public class TestDrive extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			HttpSession session=request.getSession();
			String user=(String)session.getAttribute("user");
			String valid=(String)session.getAttribute("valid");
			if(user==null)
			{
				pw.println("<html>");
				pw.println("<head><script>alert('Firstly login/signup befor Test Drive');</script>");
				pw.println("<body> ");
				pw.println("</body></html>");	
		        RequestDispatcher rd= request.getRequestDispatcher("Login.html");
			    rd.include(request, response);
			}
			else
			{
				Connection con=null;
				PreparedStatement st=null;
				ResultSet rs=null;
				try 
				{
					con=Connect.getConnect();
					String sql="select  brand,model_name from car";
					st=con.prepareStatement(sql);
					rs=st.executeQuery();

					pw.println("<html>");
					pw.println("<head>");
					pw.println("<title>Test Drive</title>");
					pw.println("<link rel=\"stylesheet\" href=\"testDrive.css\">");
					pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
					pw.println("</script>");
					pw.println("</head>");
					pw.println("<body> ");
				    pw.println("<a href=\"Front\"> <img src=\"images/icons8-home-24.png\" width=\"60px\"/> <br> &nbsp;Home</a>");
					if(valid!=null)
					{
						pw.println("<script>");
						pw.println("alert('Please select another date \\n Test drive is not available on this time slot on this day ');");
						pw.println("</script>");
						session.removeAttribute("valid");
					}
					pw.println( " <div class=\"form-wrap\">\r\n" + 
							"     \r\n" + 
							" <h1> Test Drive </h1><br>	");
					pw.println("<form id=\"form-control\" action='Address' onsubmit='return testDrive()' name='f1'>");
					pw.println("<input placeholder='Enter Car Name' list=\"carName\" name=\"list\" required>\r\n" + 
							"  <datalist id=\"carName\">");
					while(rs.next())
					{  
							pw.println("<option");
							pw.println("value='"+rs.getString(1)+" "+rs.getString(2)+"'");
							pw.println("/>");
					}
					pw.println("");
					pw.println(" </datalist>");
					pw.println("</br></br>\r\n" + 
			    		"                 <select name='time' >\r\n" + 
			    		"				 <option selected disabled hidden>Select Time Slot </option>\r\n" + 
			    		"                         <option value=\"8am - 2pm\">8am - 2pm</option>\r\n" + 
			    		"                         <option value=\"2pm - 6pm\">2pm - 6pm</option>\r\n" + 
			    		"               </select>\r\n" + 
			    		"        </br></br>");
					pw.println("<input type=\"text\" id='myDate' name='myDate'   onfocus=\"(this.type='date')\" placeholder=\"Choose Date\" required> <br>");
					pw.println("<input type= \"submit\" name='continue' value=\"Continue\" > <br>");
					pw.println("</form>");
					pw.println("</div>");
					pw.println("<script>");
					pw.println("let today = new Date(); today.setDate(today.getDate() + 1);\r\n" + 
			    		"    		let dd = today.getDate();\r\n" + 
			    		"    		let mm = today.getMonth()+1; //January is 0!\r\n" + 
			    		"    		let yyyy = today.getFullYear();\r\n" + 
			    		"    		if(dd<10){\r\n" + 
			    		"            			dd='0'+dd\r\n" + 
			    		"        			 } \r\n" + 
			    		"        	if(mm<10){\r\n" + 
			    		"            			mm='0'+mm\r\n" + 
			    		"        			 } \r\n" + 
			    		"            today = yyyy+'-'+mm+'-'+dd;\r\n" + 
			    		"          //  alert(today);\r\n" + 
			    		"            document.getElementById(\"myDate\").setAttribute(\"min\", today);\r\n" + 
			    		"        //    document.getElementById(\"myDate\").value=today;\r\n" +  
			    		"\r\n" + 
			    		"\r\n" + 
			    		"    	  today = new Date();\r\n" + 
			    		"    	  let t = new Date(); \r\n" + 
			    		"    	  t.setDate(today.getDate() + 30); \r\n" + 
			    		"    	  dd = t.getDate();\r\n" + 
			    		"    	  mm = t.getMonth()+1; //January is 0!\r\n" + 
			    		"    	  yyyy = t.getFullYear();\r\n" + 
			    		"     	  if(dd<10){\r\n" + 
			    		"           			 dd='0'+dd\r\n" + 
			    		"        		   } \r\n" + 
			    		"    	  if(mm<10){\r\n" + 
			    		"            		mm='0'+mm\r\n" + 
			    		"        		  } \r\n" + 
			    		"    	  t=yyyy+'-'+mm+'-'+dd;\r\n" + 
			    		"        //  alert(t);\r\n" + 
			    		"          document.getElementById(\"myDate\").setAttribute(\"max\",t );");
					pw.println("</script>");
					pw.println("</body></html>");
				}catch (ClassNotFoundException | SQLException e) 
				{
					e.printStackTrace();  pw.println(e);
				}
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
