public class User {
    String username, password;
    double balance;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 1000.0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        return;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "\n" + "username: " + username + "\nbalance: " + balance + "\n";
    }
    //username, password, balance
    //get\set username, chekPassword, get\set balance, вывод баланса через String
}