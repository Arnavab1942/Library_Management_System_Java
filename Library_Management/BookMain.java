package com.Library_Management;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Scanner sc = new Scanner(System.in)){
			BookDAO dao = new BookDAOImpl();
            
			
            int ch; 
            
            do {
                System.out.println("\n#------Book Management------#");
                System.out.println("1. Add Book:");
                System.out.println("2. Update Book: ");
                System.out.println("3. Delete Book: ");
                System.out.println("4. Search Book by ISBN: ");
                System.out.println("5. Display All Books: ");
                System.out.println("6. Update Book Availability: ");
                System.out.println("7. Exit.");
                System.out.print("Choose an option: ");
                ch = sc.nextInt();
                sc.nextLine();
                switch(ch) {
                case 1:
                    Book b1 = new Book();
                    System.out.println("Enter Book ISBN: ");
                    b1.setIsbn(sc.nextLine());
                    
                    System.out.println("Enter Title: ");
                    b1.setTitle(sc.nextLine());
                    
                    System.out.println("Enter Author: ");
                    b1.setAuthor(sc.nextLine());
                    
                    System.out.println("Is Available (true/false): ");
                    b1.setAvailable(sc.nextBoolean());
                    sc.nextLine();
                    
                    dao.add_Book(b1);
                    break;
                    
                case 2:
                    Book b2 = new Book();
                    System.out.println("Enter ISBN to update:");
                    b2.setIsbn(sc.nextLine());

                    System.out.println("Enter New Title: ");
                    b2.setTitle(sc.nextLine());
                    
                    System.out.println("Enter New Author: ");
                    b2.setAuthor(sc.nextLine());
                    
                    System.out.println("Is Available (true/false): ");
                    b2.setAvailable(sc.nextBoolean());
                    sc.nextLine();
                    
                    try {
                        dao.update_Book(b2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case 3:
                    System.out.println("Enter ISBN to delete Book: ");
                    String deleteIsbn = sc.nextLine();
                    
                    dao.delete_Book(deleteIsbn);
                    break;
                
                case 4:
                    System.out.print("Enter ISBN: ");
                    String searchIsbn = sc.nextLine();

                    Book b4 = dao.searchBook(searchIsbn);

                    if (b4 != null) {
                        System.out.println("ISBN : " + b4.getIsbn());
                        System.out.println("Title : " + b4.getTitle());
                        System.out.println("Author : " + b4.getAuthor());
                        System.out.println("Available : " + b4.isAvailable());
                    } else {
                        System.out.println("Book Not Found.");
                    }
                    break;        
                    
                case 5:
                    List<Book> list = dao.display_all_books();

                    for (Book b5 : list) {
                        System.out.println("ISBN : " + b5.getIsbn());
                        System.out.println("Title : " + b5.getTitle());
                        System.out.println("Author : " + b5.getAuthor());
                        System.out.println("Available : " + b5.isAvailable());
                    }
                    break;
                    
                case 6:
                    System.out.println("Enter ISBN to change status: ");
                    String statusIsbn = sc.nextLine();
                    
                    System.out.println("Set new status (true for On Shelf / false for Borrowed): ");
                    boolean status = sc.nextBoolean();
                    sc.nextLine(); 
                    dao.updateAvailability(statusIsbn, status);
                    break;
                    
                case 7:
                    System.out.println("Thank you for using Book Management System.");
                    break;
                    
                default:
                    System.out.println("Invalid option!");
                    break;
                }
                
            } while(ch != 7);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
