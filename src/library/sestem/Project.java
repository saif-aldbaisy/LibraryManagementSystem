package library.sestem;

public class Project extends Item {

    private String year;
    private String team;

    public Project(int id, String title, int publicationYear, String specialization,
            String year, String team) {
        super(id, title, publicationYear, specialization);
        this.year = year;
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public void getDetails() {
        System.out.print("Project [ID:" + id + "]");
        System.out.print(" , Title : " + title);
        System.out.print(" , publicationYear : " + publicationYear);
        System.out.print(" , specialization : " + specialization);
        System.out.print(" , ");
        System.out.print(year + " year , ");
        System.out.print("team : " + team);

    }
}