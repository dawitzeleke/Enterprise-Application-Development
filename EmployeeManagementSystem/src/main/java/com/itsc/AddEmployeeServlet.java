package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
	private static final String query = "insert into employees(name, designation, salary)values(?, ?, ?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url = "jdbc:mysql://localhost:3306/bookregister";
		String username = "dave2";
		String password = "dave2";
		PrintWriter pw = resp.getWriter();
		try {
			resp.setContentType("text/html");
			String name = req.getParameter("Name");
			String designation = req.getParameter("Designation");
			float salary = Float.parseFloat(req.getParameter("Salary"));
			Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", username, password);

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, designation);
			ps.setFloat(3, salary);
			int count = ps.executeUpdate();
			if (count == 1) {
				pw.println("<h2> Employee registered successfully.</h2>");
			} else {
				pw.println("<h2> Employee Not registered successfully.</h2>");
			}
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");

		}
		pw.println("<a href='index.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='booklist'>Employee List</a>");

	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		doGet(req, resp);
	}
}