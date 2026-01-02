package library.sestem;

import java.util.Scanner;

public class LibrarySestem {

    static Scanner in = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        library.initializeData();

        mainMenu: while (true) {
            System.out.println("\n=== WELCOME TO LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Login as User");
            System.out.println("2. Login as Manager");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    library.loginAsUser();
                    break;
                case 2:
                    library.loginAsManager();
                    break;
                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    break mainMenu;
                default:
                    System.out.println("Invalid selection! Please try again.");
            }
        }
    }

    public void showManagerMenu() {
        System.out.println("\n=== MANAGER DASHBOARD ===");

        while (true) {
            System.out.println("\n Manager Menu ");
            System.out.println("=========================");
            System.out.println("1. Add a new book");
            System.out.println("2. Add a new project");
            System.out.println("3. Add a new member");
            System.out.println("4. Display all items");
            System.out.println("5. Display all members");
            System.out.println("6. Search items");
            System.out.println("7. Display borrowed items");
            System.out.println("8. Display Available items");
            System.out.println("9. Display overdue members");
            System.out.println("10. Display members who borrowed book in period");
            System.out.println("11. View all user accounts");
            System.out.println("12. Logout");
            System.out.print("Enter your choice: ");

            int choice = in.nextInt();
            in.nextLine();

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
                    library.displayAllItems();
                    break;
                case 5:
                    library.displayAllMembers();
                    break;
                case 6:
                    library.searchItems();
                    break;
                case 7:
                    library.displayBorrowedItems();
                    break;
                case 8:
                    library.displayAvailableItems();
                    break;
                case 9:
                    library.displayOverdueMembers();
                    break;
                case 10:
                    library.displayMembersWhoBorrowedBookInPeriod();
                    break;
                case 11:
                    library.viewAllUserAccounts();
                    break;
                case 12:
                    if (library.currentUser != null) {
                        System.out.println("Logging out manager: " + library.currentUser.getUsername());
                    }
                    library.currentUser = null;
                    return;
                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }
    }

    public void showUserMenu() {
        System.out.println("\n=== USER DASHBOARD ===");

        while (true) {
            System.out.println("\n User Menu ");
            System.out.println("=====================");
            System.out.println("1. Borrow item");
            System.out.println("2. Return item");
            System.out.println("3. searchItems");
            System.out.println("4. Display all items");
            System.out.println("5. Display my borrowed items");
            System.out.println("6. Display Available Items");
            System.out.println("7. Display Projects By Year");
            System.out.println("8. Display Third Year Projects");
            System.out.println("9. Display Available Projects By Specialization");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    library.borrowItem();
                    break;
                case 2:
                    library.returnItem();
                    break;
                case 3:
                    library.searchItems();
                    break;
                case 4:
                    library.displayAllItems();
                    break;
                case 5:
                    library.displayMyBorrowedItems();
                    break;
                case 6:
                    library.displayAvailableItems();
                    break;
                case 7:
                    library.displayProjectsByYear();
                    break;
                case 8:
                    library.displayThirdYearProjects();
                    break;
                case 9:
                    library.displayAvailableProjectsBySpecialization();
                    break;
                case 10:
                    if (library.currentUser != null) {
                        System.out.println("Logging out user: " + library.currentUser.getUsername());
                    }
                    library.currentUser = null;
                    return;
                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }
    }

}