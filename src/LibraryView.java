import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryView {
	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String userName = "root";
	private static final String password = "Jain@652004";
public static void main(String[] args) {
	LibraryService lService = new LibraryService();

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	try {
		Connection connection = DriverManager.getConnection(url, userName, password);
		Scanner kb = new Scanner(System.in);
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
				System.exit(0);
			case 6:
				lService.returnBook(connection, kb);
				break;
			case 7:
				System.out.println("Thanks for Using the System");
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
		}

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
}
}
