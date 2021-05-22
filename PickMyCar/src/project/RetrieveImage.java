package project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveImage {

	public static void main(String[] args) {
		    Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
		try {
		      con=Connect.getConnect();
             String sql="select image from cars where b='model1' ";
             st=con.prepareStatement(sql);
             rs=st.executeQuery();  
             if(rs.next()){
            	 Blob b=rs.getBlob(1); 
            	 byte barr[]=b.getBytes(1,(int)b.length());  
            	               
            	 FileOutputStream fout=new FileOutputStream("d:\\sonoo.jpg");  
            	 fout.write(barr);  
            	             	  
            	 fout.close();  
            	 }
             System.out.println("ok");  
		  }
		catch (ClassNotFoundException | SQLException | IOException e) 
		  {
			e.printStackTrace(); 
		   }
	}

}
