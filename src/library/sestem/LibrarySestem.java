package library.sestem;

import java.util.Scanner;

public class LibrarySestem {

    static Scanner in = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        library.initializeData();
        system:
        while (true) {
            

            System.out.println("\n Library Management System ");
            System.out.println("1. Add a new book");
            System.out.println("2. Add a new porjet");
            System.out.println("3. Add a new member");
            System.out.println("4. Borrow item");
            System.out.println("5. Return item");
            System.out.println("6. Display all items");
            System.out.println("7. Display all members");
            System.out.println("8. Search items");
            System.out.println("9. Display borrowed items");
            System.out.println("10. Display overdue members");
            System.out.println("11. Display projects by year");
            System.out.println("12. Display third year projects");
            System.out.println("13. Display members with AI  books");
            System.out.println("14. Display available projects by specialization");
            System.out.println("15. Display members who borrowed book in period");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    library.addBook();
                    break;
                case 2:
                    library.addProject();
                    break;
                case 3:
                    library.addMember();
                    break;
                case 4:
                    library.borrowItem();
                    break;
                case 5:
                    library.returnItem();
                    break;
                case 6:
                    library.displayAllItems();
                    break;
                case 7:
                    library.displayAllMembers();
                    break;
                case 8:
                    library.searchItems();
                    break;
                case 9:
                    library.displayBorrowedItems();
                    break;
                case 10:
                    library.displayOverdueMembers();
                    break;
                case 11:
                    library.displayProjectsByYear();
                    break;
                case 12:
                    library.displayThirdYearProjects();
                    break;
                case 13:
                    library.displayMembersWithAIBooks();
                    break;
                case 14:
                    library.displayAvailableProjectsBySpecialization();
                    break;
                case 15:
                    library.displayMembersWhoBorrowedBookInPeriod();
                    break;
                case 0:
                    System.out.println("Thank you for using the system. Goodbye.!");
                    break system;
                default:
                    System.out.println("Incorrect selection, please try again.");
            }
        }
    }

}

