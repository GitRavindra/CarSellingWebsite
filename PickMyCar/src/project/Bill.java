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
import javax.servlet.http.HttpSession;

@WebServlet("/Bill")
public class Bill extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 PrintWriter pw=response.getWriter();
			HttpSession session=request.getSession();
			  String brand=(String)session.getAttribute("brand");
			  String model=(String)session.getAttribute("model");
		    Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			int price=0;
			try {con=Connect.getConnect();
			response.setContentType("text/html");
			pw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"  <title>Bill</title>\r\n" + 
					"   <link rel=\"stylesheet\" href=\"bill.css\">");

		    pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
			pw.println("</script>");
		    pw.println("</head");
		    pw.println("<body>");
		    if(brand==null)
		    {
				String sql="select price from car where brand=? and model_name=?";
				st=con.prepareStatement(sql);
				String name=(String) session.getAttribute("carName"); 
				String time=(String) session.getAttribute("time");
				String date=(String) session.getAttribute("date"); 
				//name="Volksawagen Vento";
				String[] s =name.split(" ");
				st.setString(1,s[0]); 
				st.setString(2,s[1]); 
				rs=st.executeQuery();
				if(rs.next())
				{  
				 price=(int)rs.getFloat(1);
				}
				if(price>=10)
					price=3000;
				else
					price= 2000;
				session.setAttribute("pay",""+price+""); 
				pw.println("<form action='Invoice' class=\"form-wrap\">\r\n" + 
						"                  <h1>Order Summary</h1>\r\n" + 
						"                  Car Name &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp              :\r\n" + 
						"                   <strong><label id=\"label1\" >Label1</label></strong></br></br>\r\n" + 
						"                  Payment &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                 :\r\n" + 
						"                   <strong><label id=\"label2\" >Label3</label></strong></br></br>\r\n" + 
						"                    Time Slot &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  :\r\n" + 
						"                  <strong><label id=\"label3\">Label4</label></strong></br></br>\r\n" + 
						"                 Date &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:\r\n" + 
						"                  <strong><label id=\"label4\">Label5</label></strong></br></br>\r\n" + 
						" *Refundable<br>&nbsp&nbsp&nbsp Amount &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:\r\n" +
			            "      <strong><label id='label5'>Label5</label></strong></br>");
				pw.println("<br><input type=\"checkbox\" required>I accept the &nbsp;<a href='terms.html'>terms and conditon</a> <br><br> ");
				pw.println(" <input type=\"button\"  onclick=\"goBack()\" value=\"Cancel\" name=\"b1\" id=\"b1\">"
						+ "  <input type= \"submit\"  value=\"Proceed To Payment\" name=\"b2\" id=\"b2\"> </br>");
				pw.println("<script>");
				//pw.println("alert(document.getElementById('label2').value);");
				pw.println("document.getElementById('label1').innerHTML='"+name+"'");
				pw.println("document.getElementById('label2').innerHTML='Rs '+"+price+"+'*'");
				pw.println("document.getElementById('label3').innerHTML='"+time+"'");
				pw.println("document.getElementById('label4').innerHTML='"+date+"'");
				pw.println("document.getElementById('label5').innerHTML='Rs '+"+(int)(price*90/100)+"");
				pw.println("</script>");
				pw.println("</form>\r\n" + 
						" </body>\r\n" + 
						"</html>");
		      }
		    else
		     {
		    	pw.println("<form action='Invoice' class=\"form-wrap\" name='f1'>\r\n" + 
						"                  <h1>Order Summary</h1>\r\n" + 
						"                  Car Name &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp              :\r\n" + 
						"                   <strong><label id=\"label1\" >Label1</label></strong></br></br>\r\n" + 
						"                  Payment &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                 :\r\n" + 
						"                   <strong><label id=\"label2\" >Label2</label></strong></br></br>\r\n" + 
						"                  Delivery Date <br>(expected) &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  :\r\n" + 
						"                  <strong><label id=\"label3\" name='l3'>Label3</label></strong></br></br>");
				pw.println(" <input type=\"button\"  onclick=\"goBack()\" value=\"Cancel\" name=\"b1\" id=\"b1\">"
						+ "  <input type= \"submit\"  value=\"Make Payment\" name=\"b2\" id=\"b2\"> </br>");
				pw.println("<input type='hidden' name='hide'>");
				String sql="select price from car where brand=? and model_name=?";
				st=con.prepareStatement(sql);
				st.setString(1,brand); 
				st.setString(2,model); 
				rs=st.executeQuery();
				pw.println("<script>");
				if(rs.next())
				{ 
					pw.println("document.getElementById('label1').innerHTML='"+brand+" "+model+"'");
					pw.println("document.getElementById('label2').innerHTML='Rs '+"+rs.getString(1)+"+' lakh'");
				}
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
			    		"            today = yyyy+'-'+mm+'-'+dd;\r\n");
				pw.println("document.getElementById('label3').innerHTML=today;");
				pw.println("f1.hide.value=today;");
				pw.println("</script>");
				pw.println("</form>\r\n" + 
						" </body>\r\n" + 
						"</html>");
		     }
			}
			catch (Exception e) 
			  {
				e.printStackTrace();  pw.println(e);
			   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
