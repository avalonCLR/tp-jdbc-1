package dev.jdbc;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		int id;
		String name;
		int author;
		int editor;
		
		Scanner sc = new Scanner(System.in);

		boolean end = false;
		
		while(!end) {
			
			menu();
			 
			int choice = Integer.parseInt(sc.nextLine());
			
			if(choice == 1) {
				DAOLivre.getAllBooks();
			}
			if (choice == 2) {
				System.out.println("Please input ID of book to modify");
				id = Integer.parseInt(sc.nextLine());
				System.out.println("Enter new book name");
				name = sc.nextLine();
				try {
					DAOLivre.updateBook(id, name);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
			if (choice == 3) {
				System.out.println("Select ID of book to delete");
				id = Integer.parseInt(sc.nextLine());
				try {
					DAOLivre.deleteBook(id);
				}catch (Exception e) {
					
				}
			}
			if(choice == 4) {
				System.out.println("Register new book");
				System.out.println("Please enter book name");
				name = sc.nextLine();
				System.out.println("Please enter author id");
				author = Integer.parseInt(sc.nextLine());
				System.out.println("Please enter editor id");
				editor = Integer.parseInt(sc.nextLine());
				try {
					DAOLivre.insertBook(name, author, editor);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if(choice == 5) {
				System.out.println("Exit");
				end = true;
			}
		
		}	
	}
	
	public static void menu() {
		System.out.println("Please make a choice: \r\n"
				+ "-1-list all books \r\n"
    			+ "-2-update a book \r\n"
    			+ "-3-delete a book \r\n"
    			+ "-4-create a book \r\n"
    			+ "-5-Exit");
	}

}
