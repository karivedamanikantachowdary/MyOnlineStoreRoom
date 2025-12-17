import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
public class AuthenticateUser extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			//PrintWriter Object
			PrintWriter out = response.getWriter();

			//Extract data from 
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");

			//Develop Database Connectivity
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/mystore";
			String username = "root";
			String password = "root";

				//Step1-Register the Driver
				Class.forName(driver);

				//Step2-Establish or Get the Connection
				Connection con = DriverManager.getConnection(url, username, password);

				//Step3-Create the Statement Object
				String qry = "select * from users where email=?";
				PreparedStatement stmt = con.prepareStatement(qry);
				stmt.setString(1,email);

				//Step4-Execute the Query
				ResultSet res = stmt.executeQuery();
				if (res.next())
				{
					String dbpwd = res.getString("password");
					if (pwd.equals(dbpwd))
					{
						response.sendRedirect("./products.html");
					}
					else
					{
						out.println("<h1>!!!Incorrect Credentials!!!...</h1>");
					}
				}
				else
				{
					out.println("<h1>!!!!User Not Registered!!!!. Register for Login!...</h1>");
				}

				//Step5-Close the Connection
				con.close();

			//Send Response To User
			out.println("<a href=\"./RegisterForm.html\" />Click here for Registration!!!...</a>");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}