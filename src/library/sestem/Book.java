/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.sestem;

public class Book extends Item {

    private String author;
    private int pageCount;
    private String publisher;

    public Book(int id, String title, int publicationYear, String specialization,
            String author, int pageCount, String publisher) {
        super(id, title, publicationYear, specialization);
        this.author = author;
        this.pageCount = pageCount;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public void getDetails() {
        System.out.print("Book [ID:" + id + "]");
        System.out.print(" , Title : " + title);
        System.out.print(" , publicationYear : " + publicationYear);
        System.out.print(" , specialization : " + specialization);
        System.out.print(" , author : " + author);
        System.out.print(" , pageCount : " + pageCount);
        System.out.print(" , publisher : " + publisher);

    }
}
