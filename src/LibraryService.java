import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryService {
	public void showBooks(Connection connection)
	{
		String query="select * from management";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			ResultSet rs=preparedStatement.executeQuery();
			System.out.println("=".repeat(106));
			System.out.printf("| %-3s | %-30s | %-25s | %-15s | %-4s | %-10s |\n", 
			    "ID", "Title", "Author", "Genre", "Year", "Available");
			System.out.println("=".repeat(106));

			while (rs.next()) {
			    boolean available = rs.getBoolean("isAvailable");
			    System.out.printf("| %-3d | %-30s | %-25s | %-15s | %-4d | %-10s |\n",
			        rs.getInt("book_id"),
			        rs.getString("title").trim(),
			        rs.getString("author").trim(),
			        rs.getString("genre").trim(),
			        rs.getInt("published_year"),
			        available ? "Yes" : "No"
			    );
			}

			System.out.println("=".repeat(106));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createEntry(Connection connection ,Scanner kb)
	{
		System.out.println("Enter Title of the book: ");
		String title=kb.nextLine();
		System.out.println("Enter Author of the Book: ");
		String author=kb.nextLine();
		System.out.println("Enter genre of the book");
		String genre=kb.nextLine();
		System.out.println("Enter the published year");
		int year=kb.nextInt();
		System.out.println("Book is Available? true or false?");
		boolean isAvailable = kb.nextBoolean();
		
		String query="insert into management(title , author , genre, published_year , isAvailable) values(?,?,?,?,?);";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setString(1,title );
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, genre);
			preparedStatement.setInt(4, year);
			preparedStatement.setBoolean(5, isAvailable);
			int affected= preparedStatement.executeUpdate();
			if(affected>0)
			{
				System.out.println("Data inserted successfully");
			}
			else {
				System.out.println("Data not inserted");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void changeEntry(Connection connection, Scanner kb)
	{
		System.out.println("Enter 1 to change title , 2 to change author");
		int choice= kb.nextInt();
		kb.nextLine();
		if(choice==1)
		{
			System.out.println("Enter the id of the book");
			int id=kb.nextInt();
			kb.nextLine();
			System.out.println("Enter new title");
			String title=kb.nextLine();
			String query="update management set title=? where book_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1, title);
				preparedStatement.setInt(2, id);
				int affected= preparedStatement.executeUpdate();
				if(affected>0)
				{
					System.out.println("Title updated successfully");
				}
				else {
					System.out.println("Title not updated");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Enter the id of the book");
			int id=kb.nextInt();
			System.out.println("Enter new author");
			String author=kb.nextLine();
			String query="update management set author=? where book_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1, author);
				preparedStatement.setInt(2, id);
				int affected= preparedStatement.executeUpdate();
				if(affected>0)
				{
					System.out.println("Author updated successfully");
				}
				else {
					System.out.println("Author not updated");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void deleteEntry(Connection connection , Scanner kb)
	{
		System.out.println("Enter the id of the book which u want to be deleted");
		int id=kb.nextInt();
		String query="delete from management where book_id=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int affected=preparedStatement.executeUpdate();
			if(affected>0)
			{
				System.out.println("Deleted Successfully");
			}
			else {
				System.out.println("Error in deleting");
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void borrowBook(Connection connection, Scanner kb)
	{
		System.out.println("Which Book Do u want to borrow . Enter it's Id");
		int id=kb.nextInt();
		kb.nextLine();
		String query="select isAvailable from management where book_id=?;";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				boolean isAvailable=rs.getBoolean("isAvailable");
				if(isAvailable)
				{
					System.out.println("Book Borrowed Successfully");
					String query2="update management set isAvailable=false where book_id=?";
					PreparedStatement preparedStatement2= connection.prepareStatement(query2);
					preparedStatement2.setInt(1, id);
					preparedStatement2.executeUpdate();
				}
				else {
					System.out.println("Book Not Available");
				}
			}
			else {
				System.out.println("Book with Id: " +id +" Doesnt exist");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void returnBook(Connection connection , Scanner kb)
	{
		System.out.println("Which Book Do u want to return . Enter it's Id");
		int id=kb.nextInt();
		kb.nextLine();
		String query="update management set isAvailable=true where book_id=?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			System.out.println("Returned Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
