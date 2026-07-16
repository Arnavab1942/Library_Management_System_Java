package com.Library_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class BookDAOImpl implements BookDAO{

	Connection c;
    PreparedStatement ps;
    ResultSet rs;
	
    @Override
    public void add_Book(Book b) {

        try {
            c = DBConnection.getConnection();
            String sql = "INSERT INTO Book VALUES (?,?,?,?)";
            ps = c.prepareStatement(sql);

            ps.setString(1, b.getIsbn());
            ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor());
            ps.setBoolean(4, b.isAvailable());

            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("Book Added Successfully.");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: A book with ISBN " + b.getIsbn() + " already exists.");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
	@Override
	public void update_Book(Book b) throws SQLException {
		// TODO Auto-generated method stub
		c = DBConnection.getConnection();
        String sql = "update Book set title=?, author=?, isAvailable=? where isbn=?;";
        ps = c.prepareStatement(sql);
        ps.setString(1, b.getTitle());
        ps.setString(2, b.getAuthor());
        ps.setBoolean(3, b.isAvailable());
        ps.setString(4, b.getIsbn());
        
        int i = ps.executeUpdate();
        
        if (i > 0) {
            System.out.println("Book updated.");
        } else {
            System.out.println("Book not updated.");
        }	
	}

	@Override
	public Book searchBook(String isbn) throws SQLException {
		// TODO Auto-generated method stub
		c = DBConnection.getConnection();
        String sql = "select * from Book where isbn=?;";
        ps = c.prepareStatement(sql);
        ps.setString(1, isbn);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            Book b1 = new Book();
            b1.setIsbn(rs.getString("isbn"));
            b1.setTitle(rs.getString("title"));
            b1.setAuthor(rs.getString("author"));
            b1.setAvailable(rs.getBoolean("isAvailable"));
            return b1;
        }
        return null;	
	}

	@Override
	public List<Book> display_all_books() throws SQLException {
		// TODO Auto-generated method stub
		List<Book> list = new ArrayList<>();
        
        c = DBConnection.getConnection();
        String sql = "select * from Book";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Book b = new Book();
            b.setIsbn(rs.getString("isbn"));
            b.setTitle(rs.getString("title"));
            b.setAuthor(rs.getString("author"));
            b.setAvailable(rs.getBoolean("isAvailable"));
            
            list.add(b);
        }
		return list;
	}

	@Override
	public void delete_Book(String isbn) throws SQLException {
		// TODO Auto-generated method stub
		c = DBConnection.getConnection();
        String sql = "DELETE FROM Book WHERE isbn=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, isbn);
        
        int i = ps.executeUpdate();
        
        if (i > 0) {
            System.out.println("Deleted Successfully.");
        } else {
        	System.out.println();
        }
	}

	@Override
	public void updateAvailability(String isbn, boolean isAvailable) throws SQLException {
		// TODO Auto-generated method stub
		c = DBConnection.getConnection();
        String sql = "update Book set isAvailable=? where isbn=?;";
        ps = c.prepareStatement(sql);
        ps.setBoolean(1, isAvailable);
        ps.setString(2, isbn);
        
        int i = ps.executeUpdate();
        
        if (i > 0) {
            System.out.println("Availability updated.");
        } else {
            System.out.println("Book Not Found.");
        }
	}


}
