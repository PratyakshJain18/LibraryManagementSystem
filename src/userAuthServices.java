import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userAuthServices {
	public static void login(Connection connection, Scanner kb) {
		String query = "SELECT pass FROM users WHERE email =?;";
		System.out.println("Enter your email");
		String email = kb.next();
		System.out.println("Enter your password");
		String passEntered = kb.next();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet rSet = preparedStatement.executeQuery();
			rSet.next();
			String passReal = rSet.getString("pass");
			passwordAuth(passReal, passEntered);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Email is not Registered");
		}

	}
	
	public static void passwordAuth(String passReal, String passEntered) {
		if (passEntered.equals(passReal)) {
			System.out.println("Welcome to the Library Services");
			Main.main(null);
		} else {
			System.out.println("Password is wrong");
		}
	}
	public static void Register(Connection connection , Scanner kb)
	{
		System.out.println("Enter your UserName");
		String userName=kb.next();
		System.out.println("Enter Your Email");
		String email=kb.next();
		System.out.println("Enter your Password");
		String pass=kb.next();
		System.out.println("Enter your Phone Number");
		String phone=kb.next();
		String query="insert into users(user_name, email, pass , phone) values(?,?,?,?);";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, pass);
			preparedStatement.setString(4, phone);
			int affected = preparedStatement.executeUpdate();
			if(affected>0)
			{
				System.out.println("Registered Successfully");
			}
			else {
				System.out.println("Registration Unsuccessfull");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
