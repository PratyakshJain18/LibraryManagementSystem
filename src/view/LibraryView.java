package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import controller.adminAuth;
import auth.userAuthServices;
import dao.LibraryService;
public class LibraryView {
	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String userName = "root";
	private static final String password = "Jain@652004";
public static void main(String[] args) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	try {
		String authQuery="SELECT user_role FROM users WHERE email =?;";
		Connection connection = DriverManager.getConnection(url, userName, password);
		Scanner kb = new Scanner(System.in);
		adminAuth.adminOrNormal(connection, authQuery, userAuthServices.email);
		kb.close();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
}
	public static void adminView(LibraryService lService, Scanner kb , Connection connection)
	{
		while (true) {
			System.out.println(
					"1. Add Book \n"
					+ "2. Change Title or Author \n"
					+ "3. Borrow Book \n"		
					+ "4. Delete Book \n"
					+ "5. View All Books \n"
					+ "6. Return Book \n"
					+ "7. Exit \n");
			int choice = kb.nextInt();
			kb.nextLine();
			switch (choice) {
			case 1:
				lService.createEntry(connection, kb);
				break;
			case 2:
				lService.changeEntry(connection, kb);
				break;
			case 3:
				lService.borrowBook(connection, kb);
				break;
			case 4:
				lService.deleteEntry(connection, kb);
				break;
			case 5:
				lService.showBooks(connection);
				return;
			case 6:
				lService.returnBook(connection, kb);
				break;
			case 7:
				System.out.println("Thanks for Using the System");
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return;
				
			default:
				System.out.println("Invalid choice");
			}
		}
	}
	public static void normalView(LibraryService lService, Scanner kb , Connection connection)
	{
		while (true) {
			System.out.println(
					"1. Borrow Book \n"		
					+"2. View All Books \n"
					+ "3. Return Book \n"
					+ "4. Exit \n");
			int choice = kb.nextInt();
			kb.nextLine();
			switch (choice) {
			case 1:
				lService.borrowBook(connection, kb);
				break;
			case 2:
				lService.showBooks(connection);
				return;
			case 3:
				lService.returnBook(connection, kb);
				break;
			case 4:
				System.out.println("Thanks for Using the System");
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
	}
}
