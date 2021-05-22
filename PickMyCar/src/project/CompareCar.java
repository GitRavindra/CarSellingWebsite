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
 * Servlet implementation class CompareCar
 */
@WebServlet("/CompareCar")
public class CompareCar extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			try {
				    con=Connect.getConnect();
				    HttpSession session=request.getSession();
					String cid=(String)session.getAttribute("cid");
				    String sql="select  brand,model_name from car";
					st=con.prepareStatement(sql);
					rs=st.executeQuery();
				    pw.println("<html>\r\n" + 
				    		"<head>\r\n" + 
				    		"   <title>Compare car</title>");
				    pw.println("<link rel=\"stylesheet\" href=\"compareCar.css\">");
				    pw.println("<script type=\"text/javascript\" src=\"Login.js\">");
					pw.println("</script>");
				    pw.println("<script type=\"text/javascript\" >");
				    pw.println("var whichPressed;");
				    pw.println(" function compare()\r\n" + 
				    		"     {               \r\n" + 
				    		"         if(whichPressed==\"s1\" || whichPressed==\"buy1\" )\r\n" + 
				    		"         {\r\n" + 
				    		"             if(compare1()){ return true; }else{return false;}\r\n" + 
				    		"         }");
				    pw.println("         if(whichPressed==\"s2\" || whichPressed==\"buy2\")\r\n" + 
				    		"         {\r\n" + 
				    		"           if(compare2()){ return true; }else{return false;}\r\n" + 
				    		"         }");
				    pw.println("     }");
					pw.println("</script>");
				    pw.println("</head>\r\n" + 
				    		"<body>");
				    pw.println("<div class=\"comp\">\r\n" + 
				    		"    <h1><u>Compare Cars</u></h1>\r\n" + 
				    		"      <form action='CompareCar' name='f1' onsubmit='return compare()'>");
				    pw.println("<input id=\"list1\" placeholder=\"Enter Car Name\" list=\"car1\"  name=\"list1\">\r\n" + 
				    		"  <datalist id=\"car1\">");
				    while(rs.next())
					{  
							pw.println("<option");
							pw.println("value='"+rs.getString(1)+" "+rs.getString(2)+"'");
							pw.println("/>");
					}
				    rs.close();
				    rs=st.executeQuery();
				    pw.println("</datalist>");
				    pw.println("  <input id=\"list2\" placeholder=\"Enter Car Name\" list=\"car2\" name=\"list2\">\r\n" + 
				    		"  <datalist id=\"car2\">");
				    while(rs.next())
					{  
							pw.println("<option");
							pw.println("value='"+rs.getString(1)+" "+rs.getString(2)+"'");
							pw.println("/>");
					}
				    pw.println("</datalist><br>");
				    pw.println(" <input id=\"s1\" type=\"submit\" onclick=\"whichPressed='s1'\" name='s1' value=\"search\">\r\n" + 
				    		" <input id=\"s2\" type=\"submit\" name='s2' onclick=\"whichPressed='s2'\" value=\"search\">");
				    pw.println("<table border = \"1\" cellpadding = \"5\" cellspacing = \"1\">\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\"  >Price(in lakh)</th>\r\n" + 
				    		"\r\n" + 
				    		"  <td  ><label id=\"l1\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l2\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >Engine Type</th>\r\n" + 
				    		"\r\n" + 
				    		"  <td ><label id=\"l3\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l4\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >Fuel Capacity</th>\r\n" + 
				    		"\r\n" + 
				    		"  <td ><label id=\"l5\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l6\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\">Engine Displacement</th>\r\n" + 
				    		"\r\n" + 
				    		"  <td ><label id=\"l7\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l8\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >Fuel Type</th>\r\n" + 
				    		"\r\n" + 
				    		"  <td ><label id=\"l9\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l10\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\">Power </th>\r\n" + 
				    		"\r\n" + 
				    		" <td ><label id=\"l11\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l12\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >Torque </th>\r\n" + 
				    		"\r\n" + 
				    		"<td ><label id=\"l13\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l14\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >No. of Cylinder </th>\r\n" + 
				    		"\r\n" + 
				    		" <td ><label id=\"l15\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l16\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >Speed Transmission </th>\r\n" + 
				    		"\r\n" + 
				    		"  <td ><label id=\"l17\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l18\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\">Suspension Front </th>\r\n" + 
				    		"\r\n" + 
				    		" <td ><label id=\"l19\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l20\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"\r\n" + 
				    		"<tr>\r\n" + 
				    		"<th rowspan=\"1\" scope=\"row\" >No. of Gear </th>\r\n" + 
				    		"\r\n" + 
				    		"  <td ><label id=\"l21\"></label></td>\r\n" + 
				    		"   <td ><label id=\"l22\"></label></td>\r\n" + 
				    		"</tr>\r\n" + 
				    		"</table>");
				    pw.println(" <input id=\"s3\" type=\"submit\" onclick=\"whichPressed='buy1'\" value=\"Buy Now\" name='buy1'>\r\n" + 
				    		 "<input id=\"s4\" type=\"submit\" onclick=\"whichPressed='buy2'\" value=\"Buy Now\" name='buy2'>");
				    pw.println("</form>\r\n" + 
				    		"</div>");
				    String s1=request.getParameter("s1");
				    String s2=request.getParameter("s2");
				  if(s1!=null||s2!=null) 
				  {
				    pw.println("<script>");
				    String list1=request.getParameter("list1");
				    if(list1!="")
				    {
					    pw.println("f1.list1.value='"+list1+"'");
					    //pw.println(" alert(f1.list1.value);");
				    	String sql1="select Price,ENGINE_TYPE,FUEL_CAPACITY,ENGINE_DISPLACEMENT,FUEL_TYPE,POWER, TORQUE,NO_OF_CYLINDER ,SPEED_TRANSMISSION  ,SUSPENSION ,NO_OF_GEARS   from car where brand=? and model_name=?";
				    	st=con.prepareStatement(sql1);
				    	String[] s =list1.split(" ");
				    	st.setString(1,s[0]); 
				    	st.setString(2,s[1]); 
				    	rs=st.executeQuery();
				    	if(rs.next())
				    	{  
				    		for(int i=1, j=1;j<12;i=i+2,j++)
				    			pw.println("document.getElementById(\"l"+i+"\").innerHTML='"+rs.getString(j)+"'");   
				    	} 
				    	rs.close();
				        st.close();
				       // pw.println("alert('hii');");
				    }
				    String list2=request.getParameter("list2");
				    //pw.println("alert(document.getElementById('list2').value)");
				    if(list2!="")
				    {
					    pw.println("f1.list2.value='"+list2+"'");
					   // pw.println(" alert(f1.list2.value);");
					    
				    	sql="select Price,ENGINE_TYPE,FUEL_CAPACITY,ENGINE_DISPLACEMENT,FUEL_TYPE,POWER, TORQUE,NO_OF_CYLINDER ,SPEED_TRANSMISSION  ,SUSPENSION ,NO_OF_GEARS   from car where brand=? and model_name=?";
				    	st=con.prepareStatement(sql);
				    	String[] s =list2.split(" ");
				    	st.setString(1,s[0]); 
				    	st.setString(2,s[1]); 
				    	rs=st.executeQuery();
				    	if(rs.next())
				    	{  
				    		for(int i=2,j=1;j<12;i=i+2,j++)
				    			pw.println("document.getElementById(\"l"+i+"\").innerHTML='"+rs.getString(j)+"'");   
				    	} 
				    	rs.close();
				    	st.close();
					   // pw.println("alert('hii')");
				    }
				    pw.println("</script>");
				  }
				  String buy1=request.getParameter("buy1");
				  String buy2=request.getParameter("buy2");
				  if(buy1!=null || buy2!=null)  
				  {
					  String s=null;
					  if(buy1!=null)
						  s=request.getParameter("list1");
					  else
						  s=request.getParameter("list2"); 
				    String[] ss =s.split(" ");	
					session.setAttribute("log", "log");
					session.setAttribute("brand",ss[0]);
					session.setAttribute("model",ss[1]);
					RequestDispatcher rd= request.getRequestDispatcher("View");
				    rd.forward(request, response);
				  }
				    pw.println("  </body>\r\n" + 
				    		"  </html>");
			    }
			catch (ClassNotFoundException | SQLException e) 
			  {
					e.printStackTrace();  pw.println(e);
			  }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
