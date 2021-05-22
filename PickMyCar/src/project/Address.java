package project;

import java.sql.Statement;
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
 * Servlet implementation class Address
 */
@WebServlet("/Address")
public class Address extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");//HTTP 1.1
	     response.setHeader("Pragma", "no-cache");// HTTP 1.0
       response.setHeader("Expires","0");//Proxy
		HttpSession session=request.getSession();
		String cid=(String)session.getAttribute("cid");
	    String add=request.getParameter("ok");
	    String update=request.getParameter("update");
	    //cid="c5";
		PrintWriter pw=response.getWriter(); 
		if(cid!=null)
		{
			Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			String sql1=null;
			try {
				con=Connect.getConnect();
				response.setContentType("text/html");
			   pw.println("<html>");
			    pw.println("<head>");
			    pw.println("<title>address</title>");
			    pw.println("<link rel=\"stylesheet\" href=\"address.css\">");
				//pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
				//pw.println("</script>");
			     response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");//HTTP 1.1
			     response.setHeader("Pragma", "no-cache");// HTTP 1.0
			    response.setHeader("Expires","0");//Proxy
			    pw.println("</head>");
			    pw.println("<body>");
				String td=request.getParameter("continue");
				/* code for testdrive */
			    String city=null;
			    String state=null;
				  if(td!=null )
				  {	 
					    String sql=null;
					    pw.println("<script>");
					    String carid=null;
					    int count=0;
					    sql="select carid  from car  where brand=? and model_name=?";
					    //sql="select count(*) from testDrive where brand=? and model_name=? and ";
					    st=con.prepareStatement(sql);
						String name=(String)request.getParameter("list"); 
						//name="Volksawagen Vento";
						String[] s =name.split(" ");
						st.setString(1,s[0]); 
						st.setString(2,s[1]); 
						rs=st.executeQuery();
						if(rs.next())
						{  
						 carid=rs.getString(1);
						}
						rs.close();
						sql="select count(*) from testDrive where carid=? and timeSlot=? and tDate=?";
					    st=con.prepareStatement(sql);
					    st.setString(1,carid); 
						st.setString(2,request.getParameter("time")); 
						st.setString(3,request.getParameter("myDate")); 
						rs=st.executeQuery();
						if(rs.next())
						{  
						 count=rs.getInt(1);
						}
					    rs.close();
						if(count==0)
						     pw.println("alert('Test drive is available ');");
						else
						{	 
							session.setAttribute("valid", "valid");
							RequestDispatcher rd= request.getRequestDispatcher("TestDrive");
							rd.forward(request, response);
						}
					    pw.println("</script>");
				        session.setAttribute("carName",request.getParameter("list"));  
				        session.setAttribute("time",request.getParameter("time")); 
				        session.setAttribute("date",request.getParameter("myDate")); 
				        session.setAttribute("carid",carid); 
				  } 
				 
				/* code for insert address */  
				if(add!=null)
				{	sql1="insert into address values('"+cid+"','"+request.getParameter("State")+"','"+request.getParameter("City") +"',"+Integer.parseInt(request.getParameter("Zipcode"))+",'"+request.getParameter("House")+"','"+request.getParameter("Street")+"','"+request.getParameter("Landmark")+"')";
				   pw.println("<script>alert('data inserted');</script>");
				}   
				/* code for update address */ 
				if(update!=null)
			    {	sql1="update address set state='"+request.getParameter("State")+"',city='"+request.getParameter("City") +"',zipcode="+Integer.parseInt(request.getParameter("Zipcode"))+",house='"+request.getParameter("House")+"',street='"+request.getParameter("Street")+"',landmark='"+request.getParameter("Landmark")+"' where cid='"+cid+"'";  
			        pw.println("<script>alert('data updated');</script>");
			    }   
				/* code for execution of insert/update address query */ 
			    if(add!=null || update!=null)
				{
					Statement st1=con.createStatement();
				    st1.executeUpdate(sql1);
				    st1.close();
				   // pw.println("Done");
				}   
				/* code for checking address is present or not */ 
			    String sql="select * from address where cid=?";
			    st=con.prepareStatement(sql);
			    st.setString(1,cid);
			    rs=st.executeQuery();
			    if(!rs.next())
			    {
			    	throw new SQLException();
			    }
			    rs.close();
			    rs=st.executeQuery();
			    pw.println("<div id=\"address\">\r\n" + 
			    		"	<h1> Your Address </h1>");
			    pw.println("<form action='AddAddress'>");
			    pw.println("<textarea id='l' rows='7' readonly></textarea>");
			    pw.println("<script>");
			     if(rs.next())
			     { 
			        pw.println("document.getElementById('l').innerHTML='"+rs.getString(5)+"'+'\\n'+ '"+rs.getString(6)+"'+'\\n'+ '"+rs.getString(7)+"'+'\\n'+  '"+rs.getString(4)+"'+'\\n'+  '"+rs.getString(3)+"'+'\\n'+  '"+rs.getString(2)+"'");
			        city=rs.getString(3);
			        state=rs.getString(2);
			      }
				  pw.println("</script>");
				  pw.println(" <input type= \"submit\"  value=\"Change Address\"  name='change'> ");
				  String s2=(String)session.getAttribute("carid");
				  if(s2!=null )
				  {	   
					  pw.println("<a href='Bill'>Continue</a>");
				  } 
				  /* code for buying car */ 
				  String s3=(String)session.getAttribute("brand");
				  //pw.println(s3);
				  if(s3!=null )
				  {	   
					  pw.println("<a href='Bill'>Continue</a>");
				  }
				  pw.println("</form");
				  pw.println("</div>");
				  pw.println("</body>");
				  pw.println("</html>");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(SQLException e)
			{	   
				/* code if address is not present corresponding to user */ 
				RequestDispatcher rd= request.getRequestDispatcher("AddAddress");
				rd.include(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
