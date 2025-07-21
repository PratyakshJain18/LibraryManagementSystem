import java.sql.*;
import java.util.Scanner;
public class userAuth {
	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String userName = "root";
	private static final String password = "Jain@652004";
	public static void main(String[] args) {
		Scanner kb=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection;
		
		
		
		try {
			connection = DriverManager.getConnection(url,userName,password);
			System.out.println("1. Register a New User \n"
					+ "2.Login as a Existing User");
			int choice=kb.nextInt();
			kb.nextLine();
			switch (choice) {
			case 1: 
				userAuthServices.Register(connection, kb);
			    break;
			case 2:
				userAuthServices.login(connection, kb);
				break;
			
			default:
				System.out.println("Wrong Choice Entered");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
