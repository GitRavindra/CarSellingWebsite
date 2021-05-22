package project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			//PrintWriter pw=response.getWriter();
			 Blob b=null;
		      ServletOutputStream pw = response.getOutputStream();
		try {
		      con=Connect.getConnect();
             String sql="select image from cars where b='model1' ";
             st=con.prepareStatement(sql);
             rs=st.executeQuery();  
             
        	//response.setContentType("text/html");
     		//pw.println("<html>");
    		//pw.println("<body> ");
    		if(rs.next()){
    		 b=rs.getBlob(1); 
           	/* byte barr[]=b.getBytes(1,(int)b.length());  
           	 os=response.getOutputStream();
           	 os.write(barr);
           	 os.flush();*/
           	InputStream in = b.getBinaryStream();
            int length = (int) b.length();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
    		//pw.println("<img src="+b+" height='100' width='50' />");

            response.setContentType("image/jpg");
            while ((length = in.read(buffer)) != -1) {
            pw.write(buffer, 0, length);}
    		//pw.println(barr);
          //  pw.println(rs.getBlob(1));   
    		}
    	//	pw.println("</body></html>");
		  }
		catch (ClassNotFoundException | SQLException e) 
		  {
			e.printStackTrace(); 
		   }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
