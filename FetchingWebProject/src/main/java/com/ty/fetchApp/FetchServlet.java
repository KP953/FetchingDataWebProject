package com.ty.fetchApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FetchServlet extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		String sid = arg0.getParameter("id");
		int id1 = Integer.parseInt(sid);

		String inqry = "select *from  employee.info where id=?";
		PrintWriter out = arg1.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=pvk@123");
			PreparedStatement psmt = con.prepareStatement(inqry);
			psmt.setInt(1, id1);
			ResultSet res = psmt.executeQuery();
			if (res.next()) {
				int id2 = res.getInt(1);
				String name = res.getString(2);
				String rol = res.getString(3);
				out.println("<html><body><h1>" + id2 + " " + name + " " + rol + "</h1></body></html>");

			} else {
				out.println("<html><body><h1>Invaild ID</h1></body></html>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
