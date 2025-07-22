package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.LibraryService;
import view.LibraryView;

public class adminAuth {
	public static void adminOrNormal(Connection connection, String authQuery , String email)
	{
		LibraryService lService = new LibraryService();
		Scanner kb=new Scanner(System.in);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(authQuery);
			preparedStatement.setString(1, email);
			ResultSet rSet=preparedStatement.executeQuery();
			String role="";
			if (rSet.next()) {
				    role = rSet.getString("user_role");
				} else {
				   System.out.println("User not found.");
				}
			if(role.toLowerCase().equals("admin"))
			{
				 LibraryView.adminView(lService, kb, connection);
			}
			else {
				LibraryView.normalView(lService, kb, connection);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}	
