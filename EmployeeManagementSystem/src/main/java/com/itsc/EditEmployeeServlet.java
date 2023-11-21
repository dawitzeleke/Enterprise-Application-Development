package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editScreen")
public class EditEmployeeServlet extends HttpServlet {
	private static final String query = "select name, designation, salary, id from employees where id = ?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url = "jdbc:mysql://localhost:3306/bookregister";
		String username = "dave2";
		String password = "dave2";
		PrintWriter pw = resp.getWriter();
		try {
			resp.setContentType("text/html");

			Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", username, password);
			int id = Integer.parseInt(req.getParameter("id"));
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			

			rs.next();
			pw.println("<form action = 'editurl?id=" + id + "' method = 'post'>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type = 'text', name = 'Name', value = '" + rs.getString(1) + "'></td>");

			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Designation</td>");
			pw.println("<td><input type = 'text', name = 'Designation', value = '" + rs.getString(2) + "'></td>");

			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Salary</td>");
			pw.println("<td><input type = 'text', name = 'Salary', value = '" + rs.getFloat(3) + "'></td>");

			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type = 'submit' value = 'Edit'></td>");

			pw.println("<td><input type = 'reset' value = 'Cancel'></td>");

			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");

		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");

		}
		pw.println("<a href='index.html'>Home</a>");

	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		doGet(req, resp);
	}
}