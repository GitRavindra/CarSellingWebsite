package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Invoice
 */
@WebServlet("/Invoice")
public class Invoice extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			 LocalDateTime now = LocalDateTime.now();
			 String dt=dtf.format(now);
			 try 
			  { 
				Connection con=null;
				PreparedStatement st=null;
				ResultSet rs=null;
				response.setContentType("text/html");
				con=Connect.getConnect();
				HttpSession session=request.getSession();
				  String brand=(String)session.getAttribute("brand");
				  String model=(String)session.getAttribute("model");
				String cid=(String)session.getAttribute("cid");
			    String sql="select email,mobile from customer where cid=?";
			    st=con.prepareStatement(sql);
			    st.setString(1,cid); 
			    rs=st.executeQuery();
			    pw.println("<html>");
			    pw.println("<head>");
			    pw.println("<title>invoice</title>");
			    pw.println("<link rel=\"stylesheet\" href=\"invoice.css\">");
				//pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
				//pw.println("</script>");
			    pw.println("</head>");
			    pw.println("<body>");
			    pw.println("<a href=\"Front\"> <img src=\"images/icons8-home-24.png\" width=\"60px\"/> <br> &nbsp;Home</a>");
			   
				pw.println("<script>alert('Congratulation your order is confirmed and payment is done ');</script>");
			if(brand==null)
			{	
			    pw.println("<div class=\"Invoicebox\">\r\n" + 
			    		"	<h2><u>Invoice</u></h2>\r\n" + 
			    		"	<form onsubmit=\"myFunction()\">\r\n" + 
			    		"	<b>Car Name &ensp;&ensp;&ensp;&ensp;&nbsp;</b>&nbsp;  &ensp; <label id=\"l1\">&ensp; </label><br><br> \r\n" + 
			    		"	  <b>Email &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</b>&nbsp;<label id=\"l2\">&ensp; </label><br><br>\r\n" +  
			    		"	  <b>Address</b><br> &ensp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><textarea id='l3' rows='7' readonly></textarea></label><br>\r\n" + 
			    		"	  <b>Contact no.&ensp; &nbsp;&nbsp;&nbsp;&nbsp;&ensp;&ensp;</b>   <label id=\"l4\">&ensp; </label><br><br>\r\n" + 
			    		"	  <b>TestDrive Date </b> &ensp; &ensp; <label id=\"l5\">&ensp; </label><br><br>\r\n" +
			    		"	  <b>Time Slot &nbsp;&nbsp;&nbsp;&nbsp;</b>&ensp; &ensp; &ensp;&nbsp;&nbsp; <label id=\"l7\">&ensp; </label><br><br>\r\n" + 
			    		"	  <b>Booking Date &nbsp;&nbsp;</b> &ensp;  &ensp;<label id=\"l6\"> </label><br><br>\r\n" + 
			    		"	  <b>Amount Paid &nbsp;&nbsp;&nbsp;&nbsp;</b>&ensp; &ensp; <label id=\"l8\">&ensp; </label><br>\r\n" + 
			    		"		<input type=\"submit\" name=\"\" value=\"Print\">\r\n" + 
			    		"		\r\n" + 
			    		"	</form>\r\n" + 
			    		"</div>");
				pw.println("<script>");
			    pw.println("function myFunction() {\r\n" + 
			    		"  window.print();\r\n" + 
			    		"}");
			    if(rs.next())
			    {
			    	pw.println("document.getElementById('l2').innerHTML='"+rs.getString(1)+"'");
			    	pw.println("document.getElementById('l4').innerHTML='"+rs.getString(2)+"'");
			    }
			    rs.close();
			    sql="select * from address where cid=?";
			    st=con.prepareStatement(sql);
			    st.setString(1,cid); 
			    rs=st.executeQuery();
			    if(rs.next())
			     { 
			        pw.println("document.getElementById('l3').innerHTML='"+rs.getString(5)+"'+'\\n'+ '"+rs.getString(6)+"'+'\\n'+ '"+rs.getString(7)+"'+'\\n'+  '"+rs.getString(4)+"'+'\\n'+  '"+rs.getString(3)+"'+'\\n'+  '"+rs.getString(2)+"'");

			      }

				String name=(String) session.getAttribute("carName"); 
				String time=(String) session.getAttribute("time");
				String date=(String) session.getAttribute("date"); 
				String pay=(String) session.getAttribute("pay"); 
				String carid=(String) session.getAttribute("carid"); 
			    pw.println("document.getElementById('l1').innerHTML='"+name+"'");
			    pw.println("document.getElementById('l5').innerHTML='"+date+"'");
			    pw.println("document.getElementById('l8').innerHTML='Rs "+pay+"'");
			    pw.println("document.getElementById('l6').innerHTML='"+dt+"'");
			    pw.println("document.getElementById('l7').innerHTML='"+time+"'");
			    pw.println("</script>");
			    pw.println("</body>");
				pw.println("</html>");
				Statement st1=null;
				sql="insert into testDrive values(tid.nextval,'"+date+"','"+dt+"','"+time+"','"+pay+"','"+cid+"','"+carid+"')";
			    st1=con.createStatement();
		        st1.executeUpdate(sql);
				session.removeAttribute("carName"); 
				session.removeAttribute("time");
				session.removeAttribute("date");
				session.removeAttribute("pay");
				session.removeAttribute("carid");
			}
			else
			{
				pw.println("<div class=\"Invoicebox\">\r\n" + 
			    		"	<h2><u>Invoice</u></h2>\r\n" + 
			    		"	<form onsubmit=\"myFunction()\">\r\n" + 
			    		"	<b>Car Name &ensp;&ensp;&ensp;&ensp;&nbsp;</b>&nbsp;  &ensp; <label id=\"l1\">&ensp; </label><br><br> \r\n" + 
			    		"	  <b>Email &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</b>&nbsp;<label id=\"l2\">&ensp; </label><br><br>\r\n" +  
			    		"	  <b>Address</b><br> &ensp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><textarea id='l3' rows='7' readonly></textarea></label><br>\r\n" + 
			    		"	  <b>Contact no.&ensp; &nbsp;&nbsp;&nbsp;&nbsp;&ensp;&ensp;</b>   <label id=\"l4\">&ensp; </label><br><br>\r\n" + 
			    		"	  <b>Delivery Date </b> &ensp;&ensp; &ensp; <label id=\"l5\">&ensp; </label><br><br>\r\n" +
			    		"	  <b>Booking Date &nbsp;&nbsp;</b> &ensp;  &ensp;<label id=\"l6\"> </label><br><br>\r\n" + 
			    		"	  <b>Amount Paid &nbsp;&nbsp;&nbsp;&nbsp;</b>&ensp; &ensp; <label id=\"l8\">&ensp; </label><br>\r\n" + 
			    		"		<input type=\"submit\" name=\"\" value=\"Print\">\r\n" + 
			    		"		\r\n" + 
			    		"	</form>\r\n" + 
			    		"</div>");
				 pw.println("<script>");	
				 pw.println("function myFunction() {\r\n" + 
				    		"  window.print();\r\n" + 
				    		"}");
				    if(rs.next())
				    {
				    	pw.println("document.getElementById('l2').innerHTML='"+rs.getString(1)+"'");
				    	pw.println("document.getElementById('l4').innerHTML='"+rs.getString(2)+"'");
				    }
				    pw.println("document.getElementById('l1').innerHTML='"+brand+" "+model+"'");
				    pw.println("document.getElementById('l6').innerHTML='"+dt+"'");
				    pw.println("let today = new Date(); today.setDate(today.getDate() + 15);\r\n" + 
				    		"    		let dd = today.getDate();\r\n" + 
				    		"    		let mm = today.getMonth()+1; //January is 0!\r\n" + 
				    		"    		let yyyy = today.getFullYear();\r\n" + 
				    		"    		if(dd<10){\r\n" + 
				    		"            			dd='0'+dd\r\n" + 
				    		"        			 } \r\n" + 
				    		"        	if(mm<10){\r\n" + 
				    		"            			mm='0'+mm\r\n" + 
				    		"        			 } \r\n" + 
				    		"            today = yyyy+'/'+mm+'/'+dd;\r\n");
					pw.println("document.getElementById('l5').innerHTML=today ;");
				    rs.close();
				    st.close();
				    sql="select carid,price from car where brand=? and model_name=?";
					st=con.prepareStatement(sql);
					st.setString(1,brand); 
					st.setString(2,model); 
					String carid=null;
					rs=st.executeQuery();
					if(rs.next())
				    {
				    	pw.println("document.getElementById('l8').innerHTML='Rs "+rs.getString(2)+" lakh'");
				    	carid=rs.getString(1);
				    }
					 rs.close();
					 st.close();
				    sql="select * from address where cid=?";
				    st=con.prepareStatement(sql);
				    st.setString(1,cid); 
				    rs=st.executeQuery();
				    if(rs.next())
				     { 
				        pw.println("document.getElementById('l3').innerHTML='"+rs.getString(5)+"'+'\\n'+ '"+rs.getString(6)+"'+'\\n'+ '"+rs.getString(7)+"'+'\\n'+  '"+rs.getString(4)+"'+'\\n'+  '"+rs.getString(3)+"'+'\\n'+  '"+rs.getString(2)+"'");

				      }
				    rs.close();
				    Statement st1=null;
					sql="insert into payment values(payid.nextval,'"+cid+"','"+carid+"','"+dt+"','"+request.getParameter("hide")+"')";
				    st1=con.createStatement();
			        st1.executeUpdate(sql);
					session.removeAttribute("brand"); 
					session.removeAttribute("model");
				    pw.println("</script>");
				    pw.println("</body>");
					pw.println("</html>");
			}
			  }
			  catch (ClassNotFoundException | SQLException e) 
			  {
					e.printStackTrace();  //pw.println(e);
			  }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
