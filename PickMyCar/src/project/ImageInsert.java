package project;

import java.io.FileInputStream;
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

@WebServlet("/ImageInsert")
public class ImageInsert extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
	    Connection con=null;
		PreparedStatement st=null;
	//	ResultSet rs=null;
		try {
			      con=Connect.getConnect();
	               String sql="update car set image=? where carid='c001' ";
	               st=con.prepareStatement(sql);
	               FileInputStream fin=new FileInputStream("D:\\vp.jpg");
	               st.setBinaryStream(1,fin);
	               int i=st.executeUpdate();
	               pw.println(i+" rows");
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
