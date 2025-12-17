import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
public class RegisterUser extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			response.setContentType("text/html");

			//Extract the data and save from input into variables

			String uname = request.getParameter("uname");
			String contact = request.getParameter("contact");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String pwd = request.getParameter("pwd");

			//Establish the Database Connectivity

				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/mystore";
				String username = "root";
				String password = "root";

				//Step1- Register the Driver

				Class.forName(driver);

				//Step2- Establish or Get the Connection

				Connection con = DriverManager.getConnection(url, username, password);

				//Step3- Create the Statement Object

				String qry = "insert into users(username, contact, email, address, password) values(?,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(qry);
				stmt.setString(1,uname);
				stmt.setString(2,contact);
				stmt.setString(3,email);
				stmt.setString(4,address);
				stmt.setString(5,pwd);

				//step4- Execute the Query

				stmt.executeUpdate();

				//step5- close the connection

				con.close();

			//

			//PrintWriter Object
			PrintWriter out = response.getWriter();

			//Response Object
			out.println("<h1>Registered Successfully!...</h1><br/><br/>");
			out.println("<a href=\"./LoginForm.html\">Now Login Again!..</a><br/>");
			out.println("<h5>for security reasons..</h5>");

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}