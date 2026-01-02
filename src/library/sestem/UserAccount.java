package library.sestem;

public class UserAccount {
    private String username;
    private String password;
    private String role;
    private int memberId;

    public UserAccount(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.memberId = -1;
    }

    public UserAccount(String username, String password, String role, int memberId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.memberId = memberId;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public int getMemberId() { return memberId; }
    
    public void setPassword(String password) { this.password = password; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}