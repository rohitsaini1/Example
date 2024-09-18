package in.com.Backend;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;


//import com.mysql.jdbc.Connection;
//import java.sql.Connection;
import in.com.Backend.myDBClass;


@WebServlet("/loginForm")
public class Login extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html");
		 PrintWriter out = resp.getWriter();
	     
		 String myemail = req.getParameter("email1");
	     String mypass = req.getParameter("pass1");
	     
	     try {
	    	 //Class.forName("com.mysql.jdbc.Driver");
			 //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "");
	    	 myDBClass db=new myDBClass();
			 Connection con= db.getMyConnection();
	    	 
			 PreparedStatement ps = con.prepareStatement("Select*From register where email=? and password=?");
			 ps.setString(1, myemail);
			 ps.setString(2, mypass);
			 
			 ResultSet rs= ps.executeQuery(); 
			 if(rs.next()) {
				 HttpSession session = req.getSession();
				 session.setAttribute("session_name", rs.getString("name"));
				 
				 RequestDispatcher rd = req.getRequestDispatcher("/Profile.jsp");
				 rd.include(req, resp);
				 
			 }else {
				 //resp.setContentType("text/html");
				 out.print("<h3 style='color:red'> Email and Password Incorrect </h3>");
				 
				 RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				 rd.include(req, resp);
			 }
	     }catch(Exception e){
	    	 
	    	 //resp.setContentType("text/html");
			 out.print("<h3 style='color:red'> "+e.getMessage() +"</h3>");
			 
			 RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			 rd.include(req, resp);
	    	 e.printStackTrace();
	    	 
	     }
	}

}
