package library.sestem;

public abstract class Item {

    protected int id;
    protected String title;
    protected int publicationYear;
    protected String specialization;

    public Item(int id, String title, int publicationYear, String specialization) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getSpecialization() {
        return specialization;
    }

    public abstract void getDetails();
}