package com.Library_Management;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO{
    
	void add_Book(Book b);
    void update_Book(Book b) throws SQLException;
    Book searchBook(String isbn) throws SQLException;
    List<Book> display_all_books() throws SQLException;
    void delete_Book(String isbn) throws SQLException;
    void updateAvailability(String isbn, boolean isAvailable) throws SQLException;
}
