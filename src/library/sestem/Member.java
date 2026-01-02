package library.sestem;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {

    private int memberId;
    private String name;
    private ArrayList<BorrowRecord> borrowHistory = new ArrayList<>();

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public boolean borrowItem(Item item) {
        if (getCurrentBorrowedItems().size() < 3) {
            BorrowRecord record = new BorrowRecord(item, this);
            borrowHistory.add(record);
            return true;
        }
        return false;
    }

    public boolean returnItem(Item item) {
        for (BorrowRecord record : borrowHistory) {
            if (record.getItem().equals(item) && record.getReturnDate() == null) {
                record.setReturnDate(java.time.LocalDate.now());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getCurrentBorrowedItems() {
        ArrayList<Item> currentItems = new ArrayList<>();
        for (BorrowRecord record : borrowHistory) {
            if (record.getReturnDate() == null) {
                currentItems.add(record.getItem());
            }
        }
        return currentItems;
    }

    public ArrayList<BorrowRecord> getOverdueRecords() {
        ArrayList<BorrowRecord> overdue = new ArrayList<>();
        for (BorrowRecord record : borrowHistory) {
            if (record.isOverdue()) {
                overdue.add(record);
            }
        }

        return overdue;
    }

    public ArrayList<BorrowRecord> getCurrentBorrowedRecords() {
        ArrayList<BorrowRecord> currentRecords = new ArrayList<>();
        for (BorrowRecord record : borrowHistory) {
            if (record.getReturnDate() == null) {
                currentRecords.add(record);
            }
        }
        return currentRecords;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }
}
