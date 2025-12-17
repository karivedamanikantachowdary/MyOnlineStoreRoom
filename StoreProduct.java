/*set classpath=%classpath%;C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar\;C:\Program Files\Apache Software Foundation
\Tomcat 9.0\lib\mysql-connector-java-8.0.21.jar\;*/

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;

public class StoreProduct extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.setContentType("text/html");

			//Extract the data and save from input into variables.

			String ProductName = request.getParameter("pname");
			String ProductID = request.getParameter("pid");
			String ProductCategory = request.getParameter("cat");
			int ProductQuantity = Integer.parseInt(request.getParameter("qty"));

			//Database Connectivity

			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/mystore";
			String username = "root";
			String password = "root";

			//Step1-Register the Driver

			Class.forName(driver);

			//Step2-Establish/Get the Connection

			Connection con = DriverManager.getConnection(url, username, password);

			//Step3-Create Statement Object

			String qry = "insert into products values(?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(qry);
			stmt.setString(1, ProductName);
			stmt.setString(2, ProductID);
			stmt.setString(3, ProductCategory);
			stmt.setInt(4, ProductQuantity);

			//Step4-Execute the Query

			stmt.executeUpdate();

			//Step5-Close the Connection

			con.close();

			//PrintWriter Object

			PrintWriter out = response.getWriter();

			//Response Object

			out.println("Product Name: "+ProductName);
			out.println("<br/>Product Id: "+ProductID);
			out.println("<br/>Category: "+ProductCategory);
			out.println("<br/>Quantity: "+ProductQuantity);
			out.println("<a href=\"./products.html\"><h1>Add one more product..</h1></a>");

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}