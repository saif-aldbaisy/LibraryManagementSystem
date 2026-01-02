package library.sestem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    Scanner in = new Scanner(System.in);
    private static int nextItemId = 1;
    private static int nextMemberId = 101;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();
    protected ArrayList<UserAccount> userAccounts = new ArrayList<>();
    protected UserAccount currentUser = null;
    private static LibrarySestem librarySestem = new LibrarySestem();

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount user) {
        this.currentUser = user;
    }

    public ArrayList<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void initializeData() {
        items.add(new Book(nextItemId++, "Deep Learning", 2012, "Artificial Intelligence", "Ahmad", 451, "ACM"));
        items.add(new Book(nextItemId++, "Cyber Security", 2022, "Networks", "Nawar", 551, "FBI"));
        items.add(new Project(nextItemId++, "DataBase", 2025, "Software", "Third", "Kings"));

        members.add(new Member(nextMemberId++, "Saif"));
        members.add(new Member(nextMemberId++, "Rida"));
        members.add(new Member(nextMemberId++, "Aws"));

        userAccounts.add(new UserAccount("admin", "admin123", "manager"));

        for (Member member : members) {
            String username = member.getName().toLowerCase().replaceAll("\\s+", "_") + "_" + member.getMemberId();
            String password = member.getName().toLowerCase() + "123";
            userAccounts.add(new UserAccount(username, password, "user", member.getMemberId()));
        }
    }

    public boolean login() {
        System.out.println("\n===== Library System Login =====");
        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();

        for (UserAccount account : userAccounts) {
            if (account.authenticate(username, password)) {
                currentUser = account;
                System.out.println("Login successful! Welcome, " + username + " (" + account.getRole() + ")");
                return true;
            }
        }

        System.out.println("Invalid username or password!");
        return false;
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Logging out user: " + currentUser.getUsername());
            currentUser = null;
        }
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String getCurrentUserRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public int getCurrentUserMemberId() {
        return currentUser != null ? currentUser.getMemberId() : -1;
    }

    public void loginAsUser() {
        System.out.println("\n=== USER LOGIN ===");
        boolean loggedIn = false;

        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Username: ");
            String username = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();

            for (UserAccount account : getUserAccounts()) {
                if (account.authenticate(username, password) && account.getRole().equals("user")) {
                    currentUser = account;
                    loggedIn = true;
                    System.out.println("Login successful! Welcome, " + username);
                    librarySestem.showUserMenu();
                    break;
                }
            }

            if (loggedIn)
                break;

            System.out.println("Invalid username or password! Attempts left: " + (2 - attempts));
            if (attempts < 2) {
                System.out.print("Press 1 to try again or 0 to go back: ");
                int retry = in.nextInt();
                in.nextLine();
                if (retry == 0)
                    return;
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts. Returning to main menu.");
        }
    }

    public void loginAsManager() {
        System.out.println("\n=== MANAGER LOGIN ===");
        boolean loggedIn = false;

        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Username: ");
            String username = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();

            for (UserAccount account : getUserAccounts()) {
                if (account.authenticate(username, password) && account.getRole().equals("manager")) {
                    currentUser = account;
                    loggedIn = true;
                    System.out.println("Login successful! Welcome, " + username);
                    librarySestem.showManagerMenu();
                    break;
                }
            }

            if (loggedIn)
                break;

            System.out.println("Invalid username or password! Attempts left: " + (2 - attempts));
            if (attempts < 2) {
                System.out.print("Press 1 to try again or 0 to go back: ");
                int retry = in.nextInt();
                in.nextLine();
                if (retry == 0)
                    return;
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts. Returning to main menu.");
        }
    }

    public void addManagerAccount(String username, String password) {
        userAccounts.add(new UserAccount(username, password, "manager"));
    }

    public UserAccount getUserAccount(String username) {
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public void addBook() {
        System.out.println("\n===== Add a new book =====");
        System.out.print("Title: ");
        String title = in.nextLine();
        System.out.print("Year of publication: ");
        int year = in.nextInt();
        in.nextLine();
        System.out.print("Select specialization :"
                + "\n   1. Artificial Intelligence."
                + "\n   2. Networks."
                + "\n   3. Software."
                + "\n   4. Basic Sciences.\n");
        int choice = in.nextInt();
        in.nextLine();
        String spec = "";
        switch (choice) {
            case 1:
                spec = "Artificial Intelligence";
                break;
            case 2:
                spec = "Networks";
                break;
            case 3:
                spec = "Software";
                break;
            case 4:
                spec = "Basic Sciences";
                break;
            default:
                System.out.println("Incorrect selection!.");
        }
        System.out.print("Author: ");
        String author = in.nextLine();
        System.out.print("Page count: ");
        int pages = in.nextInt();
        in.nextLine();
        System.out.print("Publisher: ");
        String publisher = in.nextLine();
        Book book = new Book(nextItemId++, title, year, spec, author, pages, publisher);
        addItem(book);
        System.out.println("The book has been added successfully! (ID: " + book.getId() + ")");
    }

    public void addProject() {
        System.out.println("\n===== Add a new project =====");
        System.out.print("Title: ");
        String title = in.nextLine();
        System.out.print("Year of publication: ");
        int year = in.nextInt();
        in.nextLine();
        System.out.print("Select specialization :"
                + "\n   1. Artificial Intelligence."
                + "\n   2. Networks."
                + "\n   3. Software."
                + "\n   4. Basic Sciences.\n");
        int choice1 = in.nextInt();
        in.nextLine();
        String spec = "";
        switch (choice1) {
            case 1:
                spec = "Artificial Intelligence";
                break;
            case 2:
                spec = "Networks";
                break;
            case 3:
                spec = "Software";
                break;
            case 4:
                spec = "Basic Sciences";
                break;
            default:
                System.out.println("Incorrect selection, using default.");
        }
        System.out.print("Select year :"
                + "\n   1. Third."
                + "\n   2. Fourth."
                + "\n   3. graduation.\n");
        int choice2 = in.nextInt();
        String projectYear = "";
        switch (choice2) {
            case 1:
                projectYear = "Third";
                break;
            case 2:
                projectYear = "Fourth";
                break;
            case 3:
                projectYear = "Graduation";
                break;
            default:
                System.out.println("Incorrect selection, using default.");
        }
        in.nextLine();
        System.out.print("team: ");
        String team = in.nextLine();
        Project project = new Project(nextItemId++, title, year, spec, projectYear, team);
        addItem(project);
        System.out.println("The project has been added successfully.! (ID: " + project.getId() + ")");
    }

    public void addMember() {
        System.out.println("\n===== Add New Member =====");

        System.out.print("Member name: ");
        String name = in.nextLine();

        Member member = new Member(nextMemberId++, name);
        addMember(member);

        String username = name.toLowerCase().replaceAll("\\s+", "_") + "_" + member.getMemberId();

        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username)) {
                System.out.println("Username already exists! Please choose a different name.");
                return;
            }
        }

        System.out.print("Set password for " + username + ": ");
        String password = in.nextLine();

        UserAccount userAccount = new UserAccount(username, password, "user", member.getMemberId());
        userAccounts.add(userAccount);

        System.out.println("Member added successfully!");
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("\nNote: The member should use this username and password to login.");
    }

    public void borrowItem() {
        System.out.println("\n===== Borrow Item =====");

        int memberId;

        if (getCurrentUserRole().equals("user")) {
            memberId = getCurrentUserMemberId();
            if (memberId == -1) {
                System.out.println("Error: User account not properly linked to a member.");
                return;
            }
            System.out.println("Borrowing as member ID: " + memberId);
        } else {
            System.out.print("Enter member ID: ");
            memberId = in.nextInt();
        }

        System.out.print("Enter item ID: ");
        int itemId = in.nextInt();
        in.nextLine();

        Member member = findMember(memberId);
        Item item = findItem(itemId);

        if (member == null || item == null) {
            System.out.println("Member or item not found.");
            return;
        }

        if (!isItemAvailable(itemId)) {
            System.out.println("Item is not available for borrowing.");
            return;
        }

        if (member.getCurrentBorrowedItems().size() >= 3) {
            System.out.println("Member has reached the borrowing limit (3 items).");
            return;
        }

        BorrowRecord record = new BorrowRecord(item, member);
        borrowRecords.add(record);
        member.borrowItem(item);
        System.out.println("Borrowing process completed successfully!");
    }

    public void returnItem() {
        System.out.println("\n===== Return Item =====");

        int memberId;

        if (getCurrentUserRole().equals("user")) {
            memberId = getCurrentUserMemberId();
            if (memberId == -1) {
                System.out.println("Error: User account not properly linked to a member.");
                return;
            }
            System.out.println("Returning as member ID: " + memberId);
        } else {
            System.out.print("Enter member ID: ");
            memberId = in.nextInt();
        }

        System.out.print("Enter item ID: ");
        int itemId = in.nextInt();
        in.nextLine();

        for (BorrowRecord record : borrowRecords) {
            if (record.getMember().getMemberId() == memberId
                    && record.getItem().getId() == itemId
                    && record.getReturnDate() == null) {

                record.setReturnDate(java.time.LocalDate.now());
                record.getMember().returnItem(record.getItem());
                System.out.println("Return successful!");
                return;
            }
        }
        System.out.println("Return failed. Please verify the information.");
    }

    public void displayAllItems() {

        ArrayList<Item> books = new ArrayList<>();
        ArrayList<Item> projects = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Book) {
                books.add(item);
            } else if (item instanceof Project) {
                projects.add(item);
            }
        }
        System.out.print("Select Item :"
                + "\n   1. books."
                + "\n   2. projects.");
        int choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1:
                System.out.println("\n===== All books in the library =====");
                if (books.isEmpty()) {
                    System.out.println("There are no books in the library.");
                } else {
                    for (Item book : books) {
                        book.getDetails();
                        System.out.println();
                    }
                }
                break;

            case 2:
                System.out.println("\n===== All projects in the library =====");
                if (projects.isEmpty()) {
                    System.out.println("There are no projects in the library.");
                } else {
                    for (Item project : projects) {
                        project.getDetails();
                        System.out.println();
                    }
                }
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }

    public void displayAllMembers() {
        System.out.println("\n===== All members and their borrowed items =====");

        if (members.isEmpty()) {
            System.out.println("There are no members.");
        } else {
            for (Member member : members) {
                String username = "No username";
                for (UserAccount account : userAccounts) {
                    if (account.getMemberId() == member.getMemberId() && account.getRole().equals("user")) {
                        username = account.getUsername();
                        break;
                    }
                }

                System.out.println("\nMember: " + member.getName() + " [ID: " + member.getMemberId() + "] [Username: "
                        + username + "]");

                ArrayList<BorrowRecord> currentRecords = member.getCurrentBorrowedRecords();

                if (currentRecords.isEmpty()) {
                    System.out.println("    No borrowed items.");
                } else {
                    System.out.print("    Borrowed items:\n");
                    for (BorrowRecord record : currentRecords) {

                        System.out.print("          ");
                        record.getItem().getDetails();

                        System.out.println("\n          Borrow date: " + record.getBorrowDate());
                        System.out.println("          Due date: " + record.getDueDate());

                        if (record.isOverdue()) {
                            System.out.println("        ! Overdue !");
                        }
                    }
                }
            }
        }
    }

    public void searchItems() {
        ArrayList<Item> results = new ArrayList<>();
        System.out.println("\n===== Search for an item =====");
        System.out.print("Search by :"
                + "\n   1. ID."
                + "\n   2. Title."
                + "\n   3. specialization.");
        int choice = in.nextInt();
        in.nextLine();

        String keyWord = "";
        String searchWord = "";

        switch (choice) {
            case 1:
                keyWord = "ID";
                System.out.print("Enter ID: ");
                searchWord = in.nextLine();
                break;
            case 2:
                keyWord = "Title";
                System.out.print("Enter Title: ");
                searchWord = in.nextLine();
                break;
            case 3:
                keyWord = "specialization";
                System.out.print("Enter specialization: ");
                searchWord = in.nextLine();
                break;
            default:
                System.out.println("Incorrect selection.");
                return;
        }

        if (keyWord.equals("ID")) {
            int searchId = Integer.parseInt(searchWord);
            for (Item item : items) {
                if (item.getId() == searchId) {
                    results.add(item);
                }
            }
        } else if (keyWord.equals("Title")) {
            for (Item item : items) {
                if (item.getTitle().contains(searchWord)) {
                    results.add(item);
                }
            }
        } else if (keyWord.equals("specialization")) {
            for (Item item : items) {
                if (item.getSpecialization().contains(searchWord)) {
                    results.add(item);
                }
            }
        }
        if (results.isEmpty()) {
            System.out.println("No matching results found.");
        } else {
            System.out.println("===== The results =====");
            for (Item item : results) {
                item.getDetails();
                System.out.println();
            }
        }
    }

    public void displayBorrowedItems() {
        System.out.println("\n===== Items currently borrowed ====");

        ArrayList<BorrowRecord> currentRecords = new ArrayList<>();

        for (BorrowRecord record : borrowRecords) {
            if (record.getReturnDate() == null) {
                currentRecords.add(record);
            }
        }

        if (currentRecords.isEmpty()) {
            System.out.println("There are no items on loan at this time.");
        } else {
            System.out.println("Currently borrowed items:");
            for (BorrowRecord record : currentRecords) {
                record.getItem().getDetails();
                System.out.println("\n  Borrower: " + record.getMember().getName());
                System.out.println("  Borrow date: " + record.getBorrowDate());
                System.out.println("  Due date: " + record.getDueDate());
            }
        }
    }

    public void displayAvailableItems() {
        System.out.println("\n===== Items Currently Available =====");

        ArrayList<BorrowRecord> currentRecords = new ArrayList<>();
        ArrayList<Item> availableItems = new ArrayList<>();

        for (BorrowRecord record : borrowRecords) {
            if (record.getReturnDate() == null) {
                currentRecords.add(record);
            }
        }

        for (Item item : items) {
            boolean isBorrowed = false;

            for (BorrowRecord record : currentRecords) {
                if (item.getId() == record.getItem().getId()) {
                    isBorrowed = true;
                    break;
                }
            }

            if (!isBorrowed) {
                availableItems.add(item);
            }
        }

        if (availableItems.isEmpty()) {
            System.out.println("There are no available items at this time.");
        } else {
            System.out.println("Available items:");
            for (Item item : availableItems) {
                item.getDetails();
                System.out.println();
            }
        }
    }

    public void displayOverdueMembers() {
        System.out.println("\n=====Late Returning Members=====");

        ArrayList<Member> overdueMembers = new ArrayList<>();

        for (BorrowRecord record : borrowRecords) {
            if (record.isOverdue()) {
                Member member = record.getMember();
                overdueMembers.add(member);
            }
        }

        if (overdueMembers.isEmpty()) {
            System.out.println("There are no late members.");
        } else {
            System.out.println("Members with overdue items:");
            for (Member member : overdueMembers) {
                System.out.println("- " + member.getName() + " (ID: " + member.getMemberId() + ")");
            }
        }
    }

    public void displayProjectsByYear() {
        System.out.println("Choose the year:");
        System.out.println("1. Third");
        System.out.println("2. Fourth");
        System.out.println("3. Graduation");
        System.out.print("Enter your choice: ");

        int choice = in.nextInt();
        in.nextLine();

        String year = "";
        switch (choice) {
            case 1:
                year = "Third";
                break;
            case 2:
                year = "Fourth";
                break;
            case 3:
                year = "Graduation";
                break;
            default:
                System.out.println("Wrong choice");
                return;
        }

        ArrayList<Item> projects = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Project) {
                Project project = (Project) item;
                if (project.getYear().equals(year)) {
                    projects.add(item);
                }
            }
        }

        System.out.println("\n===== Results =====");
        if (projects.isEmpty()) {
            System.out.println("There are no projects.");
        } else {
            for (Item project : projects) {
                project.getDetails();
                System.out.println();
            }
        }
    }

    public void displayThirdYearProjects() {

        ArrayList<Item> projects = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Project) {
                Project project = (Project) item;
                if (project.getYear().equals("Third")) {
                    projects.add(item);
                }
            }
        }

        System.out.println("\n===== Results =====");
        if (projects.isEmpty()) {
            System.out.println("There are no projects.");
        } else {
            for (Item project : projects) {
                project.getDetails();
                System.out.println();
            }
        }
    }

    public void displayMembersWithAIBooks() {
        System.out.println("\n===== Display Members With AI Books =====");

        ArrayList<Member> aiMembers = new ArrayList<>();

        for (BorrowRecord record : borrowRecords) {

            if (record.getReturnDate() == null) {
                Item item = record.getItem();

                if (item instanceof Book
                        && item.getSpecialization().contains("Artificial Intelligence")) {

                    Member member = record.getMember();
                    aiMembers.add(member);

                }
            }
        }

        if (aiMembers.isEmpty()) {
            System.out.println("No members have borrowed AI books.");
        } else {
            System.out.println("The members how borrowed AI books:");
            for (Member member : aiMembers) {
                System.out.println("\nMember: " + member.getName() + " [ID: " + member.getMemberId() + "]");
            }
        }
    }

    public void displayAvailableProjectsBySpecialization() {
        System.out.println("\n===== display available projects by specialization=====");

        System.out.println("Select specialization :");
        System.out.println("1. Artificial Intelligence");
        System.out.println("2. Networks");
        System.out.println("3. Software");
        System.out.println("4. Basic Sciences");
        System.out.print("Enter your choice: ");
        int choice = in.nextInt();
        in.nextLine();

        String specialization = "";
        switch (choice) {
            case 1:
                specialization = "Artificial Intelligence";
                break;
            case 2:
                specialization = "Networks";
                break;
            case 3:
                specialization = "Software";
                break;
            case 4:
                specialization = "Basic Sciences";
                break;
            default:
                System.out.println("Incorrect selection!.");
                return;
        }
        boolean found = false;
        for (Item item : items) {
            if (item instanceof Project) {
                Project project = (Project) item;
                if (project.getSpecialization().equals(specialization)) {
                    if (isItemAvailable(project.getId())) {
                        project.getDetails();
                        System.out.println();
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("There are no projects available in this specialty.");
        }

    }

    public void displayMembersWhoBorrowedBookInPeriod() {

        System.out.println("\n===== Show members who have borrowed a book in a specific period=====");

        System.out.print("Enter the book ID: ");
        int bookId = in.nextInt();
        in.nextLine();

        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDateStr = in.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr);
        if (startDate == null) {
            System.out.println("Invalid start date!");
            return;
        }

        System.out.print("Enter end date(yyyy-mm-dd): ");
        String endDateStr = in.nextLine();
        LocalDate endDate = LocalDate.parse(endDateStr);
        if (endDate == null) {
            System.out.println("Invalid end date!");
            return;
        }

        System.out.println("\n===== Members who borrowed the book (" + bookId + ") between " + startDate + " and "
                + endDate + " =====");
        boolean found = false;

        for (BorrowRecord record : borrowRecords) {
            Item item = record.getItem();

            if (item instanceof Book && item.getId() == bookId) {
                LocalDate borrowDate = record.getBorrowDate();

                if (!borrowDate.isBefore(startDate) && !borrowDate.isAfter(endDate)) {
                    Member member = record.getMember();
                    System.out.println("- " + member.getName() + " (Member ID: " + member.getMemberId() + ")");
                    System.out.println("  borrow date: " + borrowDate);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No members have borrowed this book within the specified period.");
        }

    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == itemId) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public boolean removeMember(int memberId) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId() == memberId) {
                members.remove(i);
                return true;
            }
        }
        return false;
    }

    public Item findItem(int itemId) {
        for (Item item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public boolean isItemAvailable(int itemId) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getItem().getId() == itemId && record.getReturnDate() == null) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public void displayMyBorrowedItems() {
        System.out.println("\n===== My Borrowed Items =====");

        int memberId = getCurrentUserMemberId();
        if (memberId == -1) {
            System.out.println("Error: User account not properly linked to a member.");
            return;
        }

        Member member = findMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        java.util.ArrayList<BorrowRecord> currentRecords = member.getCurrentBorrowedRecords();

        if (currentRecords.isEmpty()) {
            System.out.println("You have no borrowed items.");
        } else {
            System.out.println("Your borrowed items:");
            for (BorrowRecord record : currentRecords) {
                System.out.print("    ");
                record.getItem().getDetails();
                System.out.println("\n    Borrow date: " + record.getBorrowDate());
                System.out.println("    Due date: " + record.getDueDate());

                if (record.isOverdue()) {
                    System.out.println("    [OVERDUE! Please return ASAP]");
                }
                System.out.println();
            }
        }
    }

    public void viewAllUserAccounts() {
        System.out.println("\n===== All User Accounts =====");
        System.out.println("Total accounts: " + getUserAccounts().size());
        System.out.println("+-------------------------+------------+---------+");
        System.out.println("| Username                | Role       | MemberID|");
        System.out.println("+-------------------------+------------+---------+");

        for (UserAccount account : getUserAccounts()) {
            System.out.printf("| %-23s | %-10s | %-7s |\n",
                    account.getUsername(),
                    account.getRole(),
                    account.getMemberId() == -1 ? "N/A" : account.getMemberId());
        }
        System.out.println("+-------------------------+------------+---------+");
    }

}
