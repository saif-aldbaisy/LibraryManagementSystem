package library.sestem;

import java.time.LocalDate;

public class BorrowRecord {

    private Item item;
    private Member member;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord(Item item, Member member) {
        this.item = item;
        this.member = member;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(7);
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && returnDate == null;
    }

    public int getOverdueDays() {
        if (returnDate != null && returnDate.isAfter(dueDate)) {
            return (int) (returnDate.toEpochDay() - dueDate.toEpochDay());
        }
        return 0;
    }

    public Item getItem() {
        return item;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
