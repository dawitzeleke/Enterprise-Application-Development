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

@WebServlet("/viewEmployees")
public class ViewEmployeesServlet extends HttpServlet {
	private static final String query = "select id, name, designation, salary from employees";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url = "jdbc:mysql://localhost:3306/bookregister";
		String username = "dave2";
		String password = "dave2";
		PrintWriter pw = resp.getWriter();
		try {
			resp.setContentType("text/html");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", username, password);

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>Employee Id</th>");
			pw.println("<th>Employee Name</th>");
			pw.println("<th>Employee Designation</th>");
			pw.println("<th>Employee Salary</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while (rs.next()) {
				pw.println("<tr>");
				pw.println("<td>" + rs.getInt(1) + "</td>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				pw.println("<td>" + rs.getFloat(4) + "</td>");
				pw.println("<td><a href ='editScreen?id=" + rs.getInt(1) + "'>edit</a></td>");
				pw.println("<td><a href ='deleteurl?id=" + rs.getInt(1) + "'>delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
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